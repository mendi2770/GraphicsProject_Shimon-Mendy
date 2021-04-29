package geometries;

import primitives.*;
import static primitives.Util.*;

import java.util.LinkedList;
import java.util.List;

public class Sphere extends Geometry {

	private Point3D center;
	private double radius;

	/**
	 * @param center
	 * @param radius
	 */
	public Sphere(Point3D center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	/**
	 * @return the center
	 */
	public Point3D getCenter() {
		return center;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	@Override
	public Vector getNormal(Point3D point) {

		return (point.subtract(center)).normalize();
	}

	/*************** Admin *****************/

	@Override
	public String toString() {
		return "center: " + center.toString() + " radius: " + String.valueOf(this.radius);
	}

	@Override
	public LinkedList<Point3D> findIntersections(Ray ray) {

		// We used "alignZero" in this function to make the calculation accurate
		double r = this.radius;
		// Special case: if point q0 == center, that mean that all we need to calculate
		// is the radios mult scalar with the direction, and add p0
		if (center.equals(ray.getP0())) {
			Point3D p1 = ray.getPoint(r);
			LinkedList<Point3D> result = new LinkedList<Point3D>();
			result.add(p1);
			return result;
		}
		
		Vector u = center.subtract(ray.getP0());
		double tm = u.dotProduct(ray.getDir());
		double d = Math.sqrt(alignZero(u.lengthSquared() - tm * tm));
		if (d >= r)
			return null;
		double th = Math.sqrt(r * r - d * d);
		double t1 = tm + th;
		double t2 = tm - th;
		
		if (alignZero(t1) > 0 && alignZero(t2) > 0) { // In case there are two intersection points
			Point3D p1 = ray.getPoint(t1);
			Point3D p2 = ray.getPoint(t2);
			LinkedList<Point3D> result = new LinkedList<Point3D>();
			result.add(p1);
			result.add(p2);
			return result;
		} 
		else if (alignZero(t1) > 0 && alignZero(t2) <= 0) { // In case there is only one intersection point
			Point3D p1 = ray.getPoint(t1);
			LinkedList<Point3D> result = new LinkedList<Point3D>();
			result.add(p1);
			return result;
		} 
		else if (alignZero(t1) <= 0 && alignZero(t2) > 0) { // In case there is only one intersection point
			Point3D p2 = ray.getPoint(t2);
			LinkedList<Point3D> result = new LinkedList<Point3D>();
			result.add(p2);
			return result;
		}
		return null; // In case there are no intersections points
	}
}
