/**
 * 
 */
package geometries;
import primitives.*;
import static primitives.Util.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shimo
 *
 */
public interface Intersectable {
	public static class GeoPoint { public Geometry geometry; public Point3D point; }
	
	/**
	 * Finds intersections of a ray with geometric object and returns them as list of 3d points
	 * @param ray
	 * @return List<Point3D> - list of intersections
	 */
	LinkedList<Point3D> findIntersections(Ray ray);
}
