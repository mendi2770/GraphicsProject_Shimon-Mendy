/**
 * 
 */
package renderer;

import java.util.List;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;
import static primitives.Util.*;

/**
 * @author 97253 RayTracerBasic class inheritance from RayTracerBase class
 */
public class RayTracerBasic extends RayTracerBase {

	/**
	 * Head of rays movement const
	 */
	private static final double DELTA = 0.1;

	private static final double INITIAL_K = 1.0;

	/**
	 * Consts for stop condition
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * Checks if there is no shade between a point and a light source
	 * 
	 * @param l
	 * @param n
	 * @param gp
	 * @return Boolean value if the unshaded check was successful
	 */
	private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		Point3D point = gp.point.add(delta);
		Ray lightRay = new Ray(lightDirection, point);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)
			return true;
		double lightDistance = light.getDistance(gp.point);
		for (GeoPoint geop : intersections) {
			if (alignZero(geop.point.distance(gp.point) - lightDistance) <= 0)
				return false;
		}
		return true;
	}

	/**
	 * @param sc Ctor using super class constructor
	 */
	public RayTracerBasic(Scene sc) {
		super(sc);
	}

	/**
	 * Implementation for the abstract method traceRay
	 */
	@Override
	public Color traceRay(Ray ray) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? Color.BLACK : calcColor(closestPoint, ray);
//		List<GeoPoint> intersectionsPoints = scene.geometries.findGeoIntersections(ray);
//		if (intersectionsPoints == null)
//			return scene.background;
//		else
//			return calcColor(ray.findClosestGeoPoint(intersectionsPoints), ray);
	}

	/**
	 * Help method to original "calcColor" function
	 * @param geopoint
	 * @param ray
	 * @return The color from the original calcColor function
	 */
	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	/**
	 * Calculate the color of a certain point
	 * @param point
	 * @return The color of the point (calculated with local effects)
	 */
	public Color calcColor(GeoPoint point, Ray ray, int level, double k) {
		Color color = point.geometry.getEmission();
		color = color.add(calcLocalEffects(point, ray));
		return 1 == level ? color : color.add(calcGlobalEffects(point, ray, level, k));
		// return
		// scene.ambientLight.getIntensity().add(point.geometry.getEmission()).add(calcLocalEffects(point,
		// ray));
	}

	/**
	 * 
	 * @param geopoint
	 * @param ray
	 * @param level
	 * @param k
	 * @return The color with the global effects
	 */
	private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
		Color color = Color.BLACK;
		Material material = geopoint.geometry.getMaterial();
		double kr = material.getkR(), kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
		}
		double kt = material.getkT(), kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(n, geopoint.point, inRay);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
		}
		return color;
	}
	
	private Ray constructReflectedRay(Vector n, Point3D point,Ray inRay){
		Vector v = inRay.getDir();
		Vector r = v.subtract(n.scale(2 * (n.dotProduct(v)))); 
		return new Ray(point ,r.normalize(),n);
	}
	
	private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
		return new Ray(inRay.getDir(), point , n);
	}
	
	
	/** 
	 * @param ray
	 * @return The closest intersection point
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if(intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);
	}

	/**
	 * Calculate the effects of lights
	 * 
	 * @param intersection
	 * @param ray
	 * @return The color resulted by local effects calculation
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		int nShininess = intersection.geometry.getnShininess();

		double kd = intersection.geometry.getkD(), ks = intersection.geometry.getkS();
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // checks if nl == nv
				if (unshaded(lightSource, l, n, intersection)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		return color;
	}

	/**
	 * Calculates diffusive light
	 * 
	 * @param kd
	 * @param l
	 * @param n
	 * @param lightIntensity
	 * @return The color of diffusive effects
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double ln = alignZero(l.dotProduct(n));
		if (ln < 0)
			ln = ln * -1;
		return lightIntensity.scale(kd * ln);
	}

	/**
	 * Calculate specular light
	 * 
	 * @param ks
	 * @param l
	 * @param n
	 * @param v
	 * @param nShininess
	 * @param lightIntensity
	 * @return The color of specular reflection
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
		double vr = alignZero(v.scale(-1).dotProduct(r));
		if (vr < 0)
			vr = 0;
		vr = Math.pow(vr, nShininess);
		return lightIntensity.scale(ks * vr);
	}

}
