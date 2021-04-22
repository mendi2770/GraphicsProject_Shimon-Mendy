/**
 * 
 */
package renderer;

import java.util.LinkedList;
import java.util.List;

import primitives.Color;
import primitives.Point3D;
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
		LinkedList<Point3D> intersectionsPoints = scene.geometries.findIntersections(ray);
		if (intersectionsPoints == null)
			return scene.background;
		else 
			return calcColor(ray.findClosestPoint(intersectionsPoints));
	}
	
	/**
	 * 
	 * @param point
	 * @return
	 */
	public Color calcColor(Point3D point)
	{
		return scene.ambientLight.getIntensity();
	}

}
