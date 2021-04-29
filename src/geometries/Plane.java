package geometries;

import primitives.*;
import static primitives.Util.*;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;

public class Plane extends Geometry {

	private Point3D q0;
	private Vector normal;

	/**
	 * Constructor of the plane by three points
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	
	public Plane(Point3D x, Point3D y, Point3D z) {

		if (x.equals(y) || y.equals(z) || z.equals(x))
			throw new IllegalArgumentException("Two of the points are the same point");

		Vector v1 = x.subtract(y);
		Vector v2 = x.subtract(z);
		Vector cross = v1.crossProduct(v2);

		if (cross.getHead().equals(Point3D.ZERO))
			throw new IllegalArgumentException("The points are on the same line");

		normal = cross.normalize();
		q0 = x;
	}

	/**
	 * Constructor of the plane by a point and a normal
	 * 
	 * @param q0
	 * @param normal
	 */
	public Plane(Point3D q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal;
	}

	/**
	 * @return the q0
	 */
	public Point3D getQ0() {
		return q0;

	}

	/**
	 * @return getter for the normal of the shape
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public Vector getNormal(Point3D point) {

		return normal;
	}

	/*************** Admin *****************/

	@Override
	public String toString() {
		return "q0: " + q0.toString() + " normal: " + normal.toString();
	}

	@Override
	public  LinkedList<Point3D> findIntersections(Ray ray) {
		// In case there are zeroes in denominator and numerator
		if (ray.getP0().equals(q0) || isZero(this.normal.dotProduct(ray.getDir()))
				|| isZero(this.normal.dotProduct(q0.subtract(ray.getP0()))))
			return null;
		double t = (this.normal.dotProduct(q0.subtract(ray.getP0()))) / (this.normal.dotProduct(ray.getDir()));
		if (t < 0) // In case there is no intersection with the plane return null
			return null;
		Point3D p = ray.getPoint(t);
		LinkedList<Point3D> result = new LinkedList<Point3D>();
		result.add(p);
		return result;
	}
}
