/**
 * 
 */
package renderer;

import java.util.LinkedList;
import java.util.List;


import elements.LightSource;
import geometries.Geometries;
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

	private static final double INITIAL_K = 1.0;

	/**
	 * Consts for stop condition
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

	
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
	}

	/**
	 * Help method to original "calcColor" function
	 * 
	 * @param geopoint
	 * @param ray
	 * @return The color from the original calcColor function
	 */
	private Color calcColor(GeoPoint geopoint, Ray ray) {
		return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	/**
	 * Calculate the color of a certain point
	 * 
	 * @param point
	 * @return The color of the point (calculated with local effects)
	 */
	public Color calcColor(GeoPoint point, Ray ray, int level, double k) {
		Color color = point.geometry.getEmission();
		color = color.add(calcLocalEffects(point, ray));
		return 1 == level ? color : color.add(calcGlobalEffects(point, ray, level, k));
	}

	/**
	 * Calculate the effects of lights
	 * 
	 * @param intersection
	 * @param ray
	 * @return The color resulted by local effects calculation
	 */
	private Color calcLocalEffects(GeoPoint gp, Ray ray) {
		Vector v = ray.getDir();
		Vector n = gp.geometry.getNormal(gp.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		int nShininess = gp.geometry.getnShininess();

		double kd = gp.geometry.getkD(), ks = gp.geometry.getkS();
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // checks if nl == nv
				double ktr = transparency(lightSource, l, n, gp);
				if (ktr * INITIAL_K > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		return color;
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
			Ray reflectedRay = constructReflectedRay(geopoint.geometry.getNormal(geopoint.point), geopoint.point, ray);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
		}
		double kt = material.getkT(), kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(geopoint.geometry.getNormal(ray.getP0()), geopoint.point, ray);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null)
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
		}
		return color;
	}

	/**
	 * Checks if there is no shade between a point and a light source
	 * 
	 * @param ls
	 * @param l
	 * @param n
	 * @param geoPoint
	 * @return Double value if the transparency check was successful
	 */
	private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
		Vector lightDirection = l.scale(-1); // from point to light source

		Ray lightRay = new Ray(lightDirection, geoPoint.point, n);

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null)
			return 1.0;

		double lightDistance = ls.getDistance(geoPoint.point);
		double ktr = 1.0;
		for (GeoPoint geop : intersections) {
			if (alignZero(geop.point.distance(geoPoint.point) - lightDistance) <= 0) {
				ktr *= geop.geometry.getMaterial().getkT();
				if (ktr < MIN_CALC_COLOR_K)
					return 0.0;
			}
		}
		return ktr;
	}

	
	/**
	 * Calculate the reflection ray
	 * 
	 * @param n
	 * @param point
	 * @param inRay
	 * @return The new ray after the reflection calculate
	 */
	private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
		Vector v = inRay.getDir();
		Vector r = v.subtract(n.scale(alignZero(2 * (n.dotProduct(v)))));
		return new Ray(r.normalize(), point, n);
	}

	/**
	 * Calculate the refracted ray
	 * 
	 * @param n
	 * @param point
	 * @param inRay
	 * @return The new ray refracted ray
	 */
	private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
		return new Ray(inRay.getDir(), point, n);
	}

	/**
	 * @param ray
	 * @return The closest intersection point
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);
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
		Vector r = l.subtract(n.scale(alignZero(l.dotProduct(n) * 2)));
		double vr = alignZero(v.scale(-1).dotProduct(r));
		if (vr < 0)
			vr = 0;
		vr = Math.pow(vr, nShininess);
		return lightIntensity.scale(ks * vr);
	}

	/**
	 * @param rays
	 * @return The average color of the rays
	 */
	public Color calcAverageColor(LinkedList<Ray> rays) {
		Color totalColor = Color.BLACK;
		for (Ray ray : rays) {
			totalColor = totalColor.add(traceRay(ray));
		}
		return totalColor.scale((1 / (Double.valueOf(rays.size())))); // Calculates the average color
	}

	public boolean findBoxIntersectionOfScene(Ray ray) {
		return false;
	}

}