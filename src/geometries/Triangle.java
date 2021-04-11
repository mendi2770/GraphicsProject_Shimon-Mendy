package geometries;

import primitives.*;

import static org.junit.Assert.assertNotNull;
import static primitives.Util.*;

import java.util.LinkedList;
import java.util.List;

public class Triangle extends Polygon {

	public Triangle(Point3D a, Point3D b, Point3D c) {

		super(a, b, c);
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {

		List<Point3D> resultPoint = plane.findIntersections(ray);
		if (resultPoint == null) // In case there is no intersection with the plane return null
			return null;
		Vector v1 = vertices.get(0).subtract(ray.getP0());
		Vector v2 = vertices.get(1).subtract(ray.getP0());
		Vector v3 = vertices.get(2).subtract(ray.getP0());
		Vector n1 = (v1.crossProduct(v2)).normalize();
		Vector n2 = (v2.crossProduct(v3)).normalize();
		Vector n3 = (v3.crossProduct(v1)).normalize();
		double t1 = alignZero(n1.dotProduct(ray.getDir()));
		double t2 = alignZero(n2.dotProduct(ray.getDir()));
		double t3 = alignZero(n3.dotProduct(ray.getDir()));

		if (t1 == 0 || t2 == 0 || t3 == 0) // In case one or more of the scalars equals zero
			return null; // that mean the point is not inside the triangle

		if (t1 > 0 && t2 > 0 && t3 > 0) { // In case the all scalars are in the same sign, the point is in the triangle
			LinkedList<Point3D> result = new LinkedList<Point3D>();
			result.add(resultPoint.get(0));
			return result;
		} else if (t1 < 0 && t2 < 0 && t3 < 0) { // In case the all scalars are in the same sign, the point is in the triangle
			LinkedList<Point3D> result = new LinkedList<Point3D>();
			result.add(resultPoint.get(0));
			return result;
		} else
			return null;	//If the scalars are in a different sign
	}
}
