/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * @author 97253
 * Abstract class for trace ray
 */
public abstract class RayTracerBase {

	protected Scene scene;

	/**
	 * Ctor with scene parameter
	 * @param sc
	 */
	public RayTracerBase(Scene sc) {
		scene = sc;
	}
	
	/**
	 * Traces the ray and returns the Color
	 * @param ray
	 * @return Color
	 */
	public abstract Color traceRay(Ray ray);	//abstract method for trace ray
}
