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
	private Scene scene;
	private Camera camera;
	private RayTracerBasic rayTracerBasic;

	/**
	 * @param imageWriter the imageWriter to set
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * @param scene the scene to set
	 */
	public Render setScene(Scene scene) {
		this.scene = scene;
		return this;
	}

	/**
	 * @param camera the camera to set
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	/**
	 * @param rayTracerBasic the rayTracerBasic to set
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
		if (imageWriter == null || scene == null || camera == null || rayTracerBasic == null)
			throw new MissingResourceException("Missing", "resource", "exception");

		// The nested loop finds and creates a ray for each pixels, finds its color and
		// writes it to the image pixles
		int nY = this.imageWriter.getNy();
		int nX = this.imageWriter.getNx();
		Ray ray;
		for (int j = 0; j < nY; j++) {
			for (int i = 0; i < nX; i++) {
				ray = (camera.constructRayThroughPixel(nX, nY, j, i)); // For each pixel calls
																		// "constructRayThroughPixel" function
				imageWriter.writePixel(i, j, rayTracerBasic.traceRay(ray)); // Traces the color of the ray and writes it
																			// to the image
			}
		}

	}

	/**
	 * \ Method for coloring only the grid lines
	 * 
	 * @param interval
	 * @param color
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null) // In case the image writer is empty
			throw new MissingResourceException("Missing", "resource", "for an imageWriter");
		for (int i = 0; i < imageWriter.getNx(); i++)	//loop go through all the pixels
			for (int j = 0; j < imageWriter.getNy(); j++)
				if (i % interval == 0 && i != 0 || j % interval == 0 && j != 0) // In case we are in a grid lines
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
