package geometries;

import primitives.*;

import static org.junit.Assert.assertThat;
import static primitives.Util.*;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

import geometries.Intersectable.GeoPoint;

public class Tube extends Geometry {


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

		double t = (axisRay.getDir()).dotProduct(point.subtract(axisRay.getP0()));
		if (t != 0) //in case the point is not across the ray point
		{
			Point3D center = axisRay.getP0().add(axisRay.getDir().scale(t));
			return point.subtract(center).normalize();
		} 
		else // in case the point is across the ray point
			return point.subtract(axisRay.getP0()).normalize();
	}
	

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Tube setBox() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*************** Admin *****************/

	@Override
	public String toString() {
		return "axisRay: " + axisRay.toString() + " radius: " + String.valueOf(this.radius);
	}
}
