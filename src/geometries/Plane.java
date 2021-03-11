package geometries;

import primitives.*;
import static primitives.Util.*;

public class Plane implements Geometry {

	private Point3D q0;
	private Vector normal;
		
	/**
	 * @return the q0
	 */
	public Point3D getQ0() {
		return q0;
	}

	/**
	 * @return the normal
	 */
	public Vector getNormal() {
		return normal;
	}

	public Plane() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	/*************** Admin *****************/

	@Override
	public String toString() {
		return "q0: " + q0.toString() + " normal: " + normal.toString();
	}
}
