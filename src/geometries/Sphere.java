package geometries;

import primitives.*;
import static primitives.Util.*;
import primitives.Vector;

public class Sphere implements Geometry {

	private Point3D center;
	private double radius;
	
	
	
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

	public Sphere() {
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
		return "center: " +  center.toString() + " radius: " + String.valueOf(this.radius);
	}

}
