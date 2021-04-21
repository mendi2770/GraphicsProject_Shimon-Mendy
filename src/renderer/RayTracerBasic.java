/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * @author 97253
 *	RayTracerBasic class inheritance from RayTracerBase class 
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * @param sc
	 * Ctor using super class constructor
	 */
	public RayTracerBasic(Scene sc) {
		super(sc);
	}

	@Override
	public Color traceRay(Ray ray) {      //Implementation for the abstract method trayceRay
		return null;
	}

}
