package geometries;

import primitives.*;
import static primitives.Util.*;

public class Tube implements Geometry {

	protected Ray axisRay;
	protected double radius;
	
	
	
	/**
	 * @return the axisRay=
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	public Tube() {
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
		return "axisRay: " +  axisRay.toString() + " radius: " + String.valueOf(this.radius);
	}
}
