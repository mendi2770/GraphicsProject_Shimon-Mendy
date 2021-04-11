/**
 * 
 */
package geometries;

import java.util.LinkedList;
import java.util.List;

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
	public List<Point3D> findIntersections(Ray ray) {
		if (intersectables.isEmpty())		// In case the collection is empty
			return null;
		LinkedList<Point3D> result = new LinkedList<Point3D>();
		
		for (Intersectable geom : intersectables)	// The loop checks intersections for each shape
		{
			List<Point3D> points = geom.findIntersections(ray);
			if (points != null) 				// In case there are intersections
				result.addAll(points);
		}
		
		if (result.isEmpty())				  // In case there are no intersections
			return null;
		else 
			return result;					
	}

}
