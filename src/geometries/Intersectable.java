/**
 * 
 */
package geometries;
import primitives.*;
import static primitives.Util.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author shimo
 *
 */
public interface Intersectable {

	
	/**
	 * The class GeoPoint contains Geometry and Point3D
	 * @author shimo
	 *
	 */
	public static class GeoPoint {
		public Geometry geometry; 
		public Point3D point;
		
		/**
		 * Constructor of GeoPoint
		 * @param geometry
		 * @param point
		 */
		public GeoPoint(Geometry geometry, Point3D point) 
		{
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return other.point.equals(this.point) && other.geometry == this.geometry; // Checking geometry by reference
		} 
	}

	/**
	 * Finds intersections of a ray with geometric object and returns them as list of 3d points
	 * @param ray
	 * @return List<Point3D> - list of intersections
	 */
	default List<Point3D> findIntersections(Ray ray) { 
		List<GeoPoint> geoList = findGeoIntersections(ray); 
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).collect(Collectors.toList()); 
		}
	
	List<GeoPoint> findGeoIntersections(Ray ray);	
}
