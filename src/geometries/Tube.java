package geometries;

import primitives.*;
import static primitives.Util.*;

public class Tube implements Geometry {

	protected Ray axisRay;
	protected double radius;
	
	
	
	/**
	 * @param axisRay
	 * @param radius
	 */
	public Tube(Ray axisRay, double radius) {
		this.axisRay = axisRay;
		this.radius = radius;
	}

	/**
	 * @return the axisRay
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

	@Override
	public Vector getNormal(Point3D point) {
		
		return null;
	}

	/*************** Admin *****************/

	@Override
	public String toString() {
		return "axisRay: " +  axisRay.toString() + " radius: " + String.valueOf(this.radius);
	}
}
