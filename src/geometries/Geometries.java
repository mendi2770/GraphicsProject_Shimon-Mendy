/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;
/**
 * @author shimo
 *
 */
public class Geometries implements Intersectable {

	private List<Intersectable> intersectables;
	
	/**
	 * Empty ctor
	 */
	public Geometries() {
		intersectables = new LinkedList<>();
	}
	/**
	 * Ctor with list of geometries
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries)
	{
		intersectables = List.of(geometries);
	}
	/**
	 * Adds list of geometries to the current list
	 * @param geometries
	 */
	public void add(Intersectable... geometries)
	{
		intersectables.addAll(List.of(geometries));
	}
	
	@Override
	public LinkedList<Point3D> findIntersections(Ray ray) {
		if (intersectables.isEmpty())		// In case the collection is empty
			return null;
		LinkedList<Point3D> result = null,  points;
		
		for (Intersectable geom : intersectables)	// The loop checks intersections for each shape
		{
			points = geom.findIntersections(ray);
			
			if (points != null) 				// In case there are intersections
			{
				if (result == null)  		    // If we only now start to add shape intersections - assigns the points to result
				{
					result = points;					
				}
				else
					result.addAll(points);
			}
				
		}
		
		if (result == null)				  // In case there are no intersections
			return null;
		else 		                     // Else, returns the result of all the intersection points
			return result;					
	}
	
	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		if (intersectables.isEmpty())		// In case the collection is empty
			return null;
		
		List<GeoPoint> result = null, points;
		
		for (Intersectable geom : intersectables)	// The loop checks intersections for each shape
		{
			points = geom.findGeoIntersections(ray);
			
			if (points != null) 				// In case there are intersections
			{
				if (result == null)  		    // If we only now start to add shape intersections - assigns the points to result
				{
					result = points;					
				}
				else
					result.addAll(points);
			}				
		}
		return result;
	}

	

}
