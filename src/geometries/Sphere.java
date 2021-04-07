package geometries;

import primitives.*;
import static primitives.Util.*;

import java.util.LinkedList;
import java.util.List;

public class Sphere implements Geometry {

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
	public List<Point3D> findIntersections(Ray ray) {

		double r = this.radius;
		Vector u = center.subtract(ray.getP0());
		double tm = u.dotProduct(ray.getDir());
		double d = Math.sqrt(alignZero(u.lengthSquared() - tm * tm));
		if (d >= r)
			return null;
		double th = Math.sqrt(r * r - d * d);
		double t1 = tm + th;
		double t2 = tm - th;
		if (alignZero(t1) > 0 && alignZero(t2) > 0) {
			Point3D p1 = ray.getP0().add(ray.getDir().scale(t1));
			Point3D p2 = ray.getP0().add(ray.getDir().scale(t2));
			LinkedList<Point3D> result = new LinkedList<Point3D>();
			result.add(p1);
			result.add(p2);
			return result;
		} else if (alignZero(t1) > 0 && alignZero(t2) <= 0) {
			Point3D p1 = ray.getP0().add(ray.getDir().scale(t1));
			LinkedList<Point3D> result = new LinkedList<Point3D>();
			result.add(p1);
			return result;
		} else if (alignZero(t1) <= 0 && alignZero(t2) > 0) {
			Point3D p2 = ray.getP0().add(ray.getDir().scale(t2));
			LinkedList<Point3D> result = new LinkedList<Point3D>();
			result.add(p2);
			return result;
		}
		return null;
	}
}
