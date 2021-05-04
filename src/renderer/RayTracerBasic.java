/**
 * 
 */
package renderer;

import java.util.List;

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
		List<GeoPoint> intersectionsPoints = scene.geometries.findGeoIntersections(ray);
		if (intersectionsPoints == null)
			return scene.background;
		else
			return calcColor(ray.findClosestGeoPoint(intersectionsPoints), ray);
	}

	/**
	 * Calculate the color of a certain point
	 * 
	 * @param point
	 * @return The color of the point (calculated with local effects)
	 */
	public Color calcColor(GeoPoint point, Ray ray) {
		return scene.ambientLight.getIntensity().add(point.geometry.getEmission()).add(calcLocalEffects(point, ray));
	}

	/**
	 * Calculate the effects of lights
	 * 
	 * @param intersection
	 * @param ray
	 * @return The color resulted by local effecrs calculation
	 */
	private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return Color.BLACK;
		int nShininess = intersection.geometry.getMaterial().getnShininess();

		double kd = intersection.geometry.getMaterial().getkD(), ks = intersection.geometry.getMaterial().getkS();
		Color color = Color.BLACK;
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // checks if nl == nv
				Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd, l, n, lightIntensity),
						calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		return color;
	}
	

	/**
	 * Calculates diffusive light
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
