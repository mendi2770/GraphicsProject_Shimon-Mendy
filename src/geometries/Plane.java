package geometries;

import primitives.*;
import static primitives.Util.*;

public class Plane implements Geometry {

	private Point3D q0;
	private Vector normal;

	/**
	 * Constructor of the plane by three points
	 * @param x
	 * @param y
	 * @param z
	 */
	public Plane(Point3D x, Point3D y, Point3D z) {
		normal = null;
		q0 = x;
	}

	/**
	 * Constructor of the plane by a point and a normal
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
		
		return null;
	}

	/*************** Admin *****************/

	@Override
	public String toString() {
		return "q0: " + q0.toString() + " normal: " + normal.toString();
	}
}
