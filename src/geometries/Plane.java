package geometries;

import primitives.*;
import static primitives.Util.*;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;

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

	double pInfiniteDouble = Double.POSITIVE_INFINITY;

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		if (box != null && !box.IsRayHitBox(ray))
			return null;
		// In case there are zeroes in denominator and numerator
		if (ray.getP0().equals(q0) || isZero(this.normal.dotProduct(ray.getDir()))
				|| isZero(this.normal.dotProduct(q0.subtract(ray.getP0()))))
			return null;
		double t = (this.normal.dotProduct(q0.subtract(ray.getP0()))) / (this.normal.dotProduct(ray.getDir()));
		if (t < 0) // In case there is no intersection with the plane return null
			return null;
		GeoPoint p = new GeoPoint(this, ray.getPoint(t));
		LinkedList<GeoPoint> result = new LinkedList<GeoPoint>();
		result.add(p);
		return result;
	}

	@Override
	public void setBox() {
		double pInfinite = Double.POSITIVE_INFINITY;
		double nInfinite = Double.NEGATIVE_INFINITY;
		Vector nX = new Vector(new Point3D(1, 0, 0));
		Vector nY = new Vector(new Point3D(0, 1, 0));
		Vector nZ = new Vector(new Point3D(0, 0, 1));

		if (nX.equals(normal) || nX.scale(-1).equals(normal)) {
			box = new Box(q0.getX(), pInfinite, pInfinite, q0.getX(), nInfinite, nInfinite);
		}
		else if (nY.equals(normal) || nY.scale(-1).equals(normal)) {
			box = new Box(pInfinite, q0.getY(), pInfinite, nInfinite, q0.getY(), nInfinite);
		}

		else if (nZ.equals(normal) || nZ.scale(-1).equals(normal)) {
			box = new Box(pInfinite, pInfinite, q0.getZ(), nInfinite, nInfinite, q0.getZ());
		}
		else 
			box = new Box(pInfinite, pInfinite, pInfinite, nInfinite, nInfinite, nInfinite);
	}
}
