/**
 * 
 */
package renderer;

import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;
import scene.Scene;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author 97253
 *  Render class that create from scene the color matrix  of the image
 */
public class Render {

	private ImageWriter imageWriter;
	private Scene scene;
	private Camera camera;
	private RayTracerBasic rayTracerBasic;

	/**
	 * @return the imageWriter
	 */
	public ImageWriter getImageWriter() {
		return imageWriter;
	}

	/**
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * @return the rayTracerBasic
	 */
	public RayTracerBasic getRayTracerBasic() {
		return rayTracerBasic;
	}

	// Method for creating rays and foe every rey gets the color
	void renderImage() {
		//In case that not all of the fields are filled 
		if (imageWriter == null || scene == null || camera == null || rayTracerBasic == null)
			throw new MissingResourceException("Missing", "resource", "exception");
		throw new UnsupportedOperationException();
	}

	// Method for coloring only the grid lines
	void printGrid(int interval, Color color) {	
		if (imageWriter == null)		//In case the image writer is empty
			throw new MissingResourceException("Missing", "resource", "for an imageWriter");
		for (int i = 0; i < imageWriter.getNx(); i++)
			for (int j = 0; j < imageWriter.getNy(); j++)
				if (i % interval != 0 && i == 0 || j % interval != 0 && j == 0) 	//In case we are in a grid lines
					imageWriter.writePixel(i, j, color);
	}
	
	// Method for start the "writeToImage()" in imageWriter object
	void writeToImage() {		 
		if (imageWriter == null)
			throw new MissingResourceException("Missing", "resource", "for an imageWriter");
		imageWriter.writeToImage();
	}
	

}
