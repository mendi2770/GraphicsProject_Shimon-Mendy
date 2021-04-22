/**
 * 
 */
package renderer;

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

	/**
	 * Implementation for the abstract method trayceRay
	 */
	@Override
	public Color traceRay(Ray ray) {   
		List<Point3D> intersectionsPoints = scene.geometries.findIntersections(ray);
		if (intersectionsPoints == null)
			return scene.background;
		else 
			return calcColor(ray.findClosestPoint(intersectionsPoints));
	}
	
	/**
	 * Calculate the color of a certain point
	 * @param point
	 * @return The ambient light of the scene 
	 */
	public Color calcColor(Point3D point)
	{
		return scene.ambientLight.getIntensity();
	}

}
