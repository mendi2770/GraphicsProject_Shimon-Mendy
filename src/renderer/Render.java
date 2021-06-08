/**
 * 
 */
package renderer;

import java.util.*;
import java.util.LinkedList;
import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * @author 97253 Render class that create from scene the color matrix of the
 *         image
 */
public class Render {

	private ImageWriter imageWriter;
	private Camera camera;
	private RayTracerBasic rayTracerBasic;

	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;
	
	/**
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}

		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++_counter;
			if (col < _maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) {
				col = 0;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}
	
	/**
	 * @param imageWriter the imageWriter to set
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * @param The camera to set
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	/**
	 * @param The rayTracerBasic to set
	 */
	public Render setRayTracerBasic(RayTracerBasic rayTracerBasic) {
		this.rayTracerBasic = rayTracerBasic;
		return this;
	}

	/**
	 * Method for creating rays and for every ray gets the color for the pixel
	 */
	public void renderImage() {
		// In case that not all of the fields are filled
		if (imageWriter == null)
			throw new MissingResourceException("Missing", "imageWriter resource", "exception");
		else if (camera == null)
			throw new MissingResourceException("Missing", "camera resource", "exception");
		else if (rayTracerBasic == null)
			throw new MissingResourceException("Missing", "rayTracerBasic resource", "exception");

		// The nested loop finds and creates a ray for each pixels, finds its color and
		// writes it to the image pixles
		int nY = this.imageWriter.getNy();
		int nX = this.imageWriter.getNx();

		final Pixel thePixel = new Pixel(nY, nX);

		// Generate threads
		Thread[] threads = new Thread[_threads];
		

		for (int i = _threads - 1; i >= 0; --i) {
			threads[i] = new Thread(() -> {
				Pixel pixel = new Pixel();
				while (thePixel.nextPixel(pixel)) {
					// The CBR condition - Checks if the basic ray hits any box in the geometries.
					// If true, only then it will construct super samples
					// Else, it will write the pixel as black
					if (rayTracerBasic.scene.geometries.isRayIntersectsAnyBox(camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row))) 
					{
						LinkedList<Ray> rays = camera.constructSampledRays(nX, nY, pixel.col, pixel.row);
						imageWriter.writePixel(pixel.col, pixel.row, rayTracerBasic.calcAverageColor(rays));
					}
					else 
						imageWriter.writePixel(pixel.col, pixel.row, Color.BLACK);			
				}
			});
		}

		// Start threads
		for (Thread thread : threads) thread.start();

		// Wait for all threads to finish
		for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
		if (_print) System.out.printf("\r100%%\n");
	}
	
	/**
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}

	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		_print = true;
		return this;
	}
	
	/**
	 * Method for coloring only the grid lines
	 * 
	 * @param interval
	 * @param color
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null) // In case the image writer is empty
			throw new MissingResourceException("Missing", "resource", "for an imageWriter");
		for (int i = 0; i < imageWriter.getNx(); i++) // The loop goes through all the pixels
			for (int j = 0; j < imageWriter.getNy(); j++)
				if (i % interval == 0 && i != 0 || j % interval == 0 && j != 0) // In case we are in the grid lines
					imageWriter.writePixel(i, j, color);
	}

	/**
	 * Method for start the "writeToImage()" in imageWriter object
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("Missing", "resource", "for an imageWriter");
		imageWriter.writeToImage();
	}

}
