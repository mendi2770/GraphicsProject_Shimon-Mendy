/**
 * 
 */
package renderer;

import java.util.LinkedList;
import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author 97253 Render class that create from scene the color matrix of the
 *         image
 */
public class Render {

	private ImageWriter imageWriter;
	private Camera camera;
	private RayTracerBasic rayTracerBasic;
	private static final String RESOURCE_ERROR = "Renderer resource not set";
	private static final String RENDER_CLASS = "Render";
	private static final String IMAGE_WRITER_COMPONENT = "Image writer";
	private static final String CAMERA_COMPONENT = "Camera";
	private static final String RAY_TRACER_COMPONENT = "Ray tracer";

	private int threadsCount = 0;
	private static final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
	private boolean print = true; // printing progress percentage
	

	/**
	 * Set multi-threading <br>
	 * - if the parameter is 0 - number of cores less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading parameter must be 0 or higher");
		if (threads != 0)
			this.threadsCount = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			this.threadsCount = cores <= 2 ? 1 : cores;
		}
		return this;
	}
	
	/**
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		print = true;
		return this;
	}


	/**
	 * Pixel is an internal helper class whose objects are associated with a Render
	 * object that they are generated in scope of. It is used for multithreading in
	 * the Renderer and for follow up its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each
	 * thread.
	 * 
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long maxRows = 0;
		private long maxCols = 0;
		private long pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long counter = 0;
		private int percents = 0;
		private long nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * 
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			this.maxRows = maxRows;
			this.maxCols = maxCols;
			this.pixels = (long) maxRows * maxCols;
			this.nextCounter = this.pixels / 100;
			if (Render.this.print)
				System.out.printf("\r %02d%%", this.percents);
		}

		/**
		 * Default constructor for secondary Pixel objects
		 */
		public Pixel() {
		}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object
		 * - this function is critical section for all the threads, and main Pixel
		 * object data is the shared data of this critical section.<br/>
		 * The function provides next pixel number each call.
		 * 
		 * @param target target secondary Pixel object to copy the row/column of the
		 *               next pixel
		 * @return the progress percentage for follow up: if it is 0 - nothing to print,
		 *         if it is -1 - the task is finished, any other value - the progress
		 *         percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++this.counter;
			if (col < this.maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (Render.this.print && this.counter == this.nextCounter) {
					++this.percents;
					this.nextCounter = this.pixels * (this.percents + 1) / 100;
					return this.percents;
				}
				return 0;
			}
			++row;
			if (row < this.maxRows) {
				col = 0;
				target.row = this.row;
				target.col = this.col;
				if (Render.this.print && this.counter == this.nextCounter) {
					++this.percents;
					this.nextCounter = this.pixels * (this.percents + 1) / 100;
					return this.percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * 
		 * @param target target secondary Pixel object to copy the row/column of the
		 *               next pixel
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percent = nextP(target);
			if (Render.this.print && percent > 0)
				synchronized (this) {
					notifyAll();
				}
			if (percent >= 0)
				return true;
			if (Render.this.print)
				synchronized (this) {
					notifyAll();
				}
			return false;
		}

		/**
		 * Debug print of progress percentage - must be run from the main thread
		 */
		public void print() {
			if (Render.this.print)
				while (this.percents < 100)
					try {
						synchronized (this) {
							wait();
						}
						System.out.printf("\r %02d%%", this.percents);
						System.out.flush();
					} catch (Exception e) {
					}
		}
	}
	

	/**
	 * @param The camera to set
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}
	
	/**
	 * @param imageWriter the imageWriter to set
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
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
	 * Method for start the "writeToImage()" in imageWriter object
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("Missing", "resource", "for an imageWriter");
		imageWriter.writeToImage();
	}

	/**
	 * Cast ray from camera in order to color a pixel
	 * @param nX resolution on X axis (number of pixels in row)
	 * @param nY resolution on Y axis (number of pixels in column)
	 * @param col pixel's column number (pixel index in row)
	 * @param row pixel's row number (pixel index in column)
	 */
	private void castRay(int nX, int nY, int j, int i) {
		Ray basicRay;
		//In case the amount of rays go through pixel are only one ray (the basic ray)
		if (camera.getAmountOfSampledRays() == 0) {
			for (j = 0; j < nY; j++) {
				for (i = 0; i < nX; i++) {
					basicRay = camera.constructRayThroughPixel(nX, nY, j, i); // For each pixel calls
																				// "constructRayThroughPixel" function
					imageWriter.writePixel(j, i, rayTracerBasic.traceRay(basicRay)); // Traces the color of the ray and
																						// writes itto the image
				}
			}
		}		
		//In case the amount of rays go through pixel are more than one ray (sampled rays)
		else {
			LinkedList<Ray> rays = new LinkedList<>();
			for (j = 0; j < nY; j++) {
				for (i = 0; i < nX; i++) {
					rays = camera.constructSampledRays(nX, nY, j, i);
					imageWriter.writePixel(j, i, rayTracerBasic.calcAverageColor(rays)); // Traces the color of the 
																						//rays and writes it to the image
				}
			}
		}
	}
	
	/**
	 * This function renders image's pixel color map from the scene included with
	 * the Renderer object - with multi-threading
	 */
	private void renderImageThreaded() {
		final int nX = imageWriter.getNx();
		final int nY = imageWriter.getNy();
		final Pixel thePixel = new Pixel(nY, nX);
		// Generate threads
		Thread[] threads = new Thread[threadsCount];
		for (int i = threadsCount - 1; i >= 0; --i) {
			threads[i] = new Thread(() -> {
				Pixel pixel = new Pixel();
				while (thePixel.nextPixel(pixel))
					castRay(nX, nY, pixel.col, pixel.row);
			});
		}
		
		// Start threads
		for (Thread thread : threads)
			thread.start();

		// Print percents on the console
		thePixel.print();

		// Ensure all threads have finished
		for (Thread thread : threads)
			try {
				thread.join();
			} catch (Exception e) {
			}

		if (print)
			System.out.print("\r100%");
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
		final int nX = imageWriter.getNx();
		final int nY = imageWriter.getNy();
		
		// Calls the multi thread to start:
		if (threadsCount == 0)
			for (int i = 0; i < nY; ++i)
				for (int j = 0; j < nX; ++j)
					castRay(nX, nY, j, i);
		else
			renderImageThreaded();
	
	}

	/**
	 * Method for coloring only the grid lines..
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

}
