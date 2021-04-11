package geometries;

import primitives.*;
import static primitives.Util.*;

import java.util.LinkedList;

public class Cylinder extends Tube implements Geometry {

	private double height;
	
	
	/**
	 * @param axisRay
	 * @param radius
	 * @param height
	 */
	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	
	@Override
	public Vector getNormal(Point3D point) 
	{	
		return null;
	}
	
	@Override
	public  LinkedList<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	/*************** Admin *****************/

	@Override
	public String toString() {
		return "Height: " + String.valueOf(this.height);
	}
}
