package geometries;

import primitives.*;
import static primitives.Util.*;

import java.util.LinkedList;
import java.util.List;

public class Triangle extends Polygon {

	public Triangle(Point3D a, Point3D b, Point3D c) {

		super(a, b, c);
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		;
		List<Point3D> resultPoint = plane.findIntersections(ray);


		return null;
	}
}
