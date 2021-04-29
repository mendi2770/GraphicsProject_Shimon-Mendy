/**
 * 
 */
package renderer;

import java.util.List;
import geometries.Intersectable.GeoPoint;
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
	 * Implementation for the abstract method traceRay
	 */
	@Override
	public Color traceRay(Ray ray) {   
		List<GeoPoint> intersectionsPoints = scene.geometries.findGeoIntersections(ray);
		if (intersectionsPoints == null)
			return scene.background;
		else 
			return calcColor(ray.findClosestGeoPoint(intersectionsPoints));
	}
	
	/**
	 * Calculate the color of a certain point
	 * @param point
	 * @return The ambient light of the scene 
	 */
	public Color calcColor(GeoPoint point)
	{
		return scene.ambientLight.getIntensity().add(point.geometry.getEmission());
	}

}
