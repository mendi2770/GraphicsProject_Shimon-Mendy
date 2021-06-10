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
public class Geometries extends Intersectable {

	public List<Intersectable> intersectables;
	
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
	
	/**
	 * Create big box that will contain all of the geometries
	 */
	@Override
	public void setBox() {
		double maxX = intersectables.get(0).box.maxX;
		double maxY = intersectables.get(0).box.maxY;
		double maxZ = intersectables.get(0).box.maxZ;
		double minX = maxX;
		double minY = maxY;
		double minZ = maxZ;
		
		for(Intersectable geo : intersectables) {
			
			if (maxX < geo.box.maxX)
				maxX = geo.box.maxX;

			if (maxY < geo.box.maxY)
				maxY = geo.box.maxY;

			if (maxZ < geo.box.maxZ)
				maxZ = geo.box.maxZ;

			if (minX > geo.box.minX)
				minX = geo.box.minX;

			if (minY > geo.box.minY)
				minY = geo.box.minY;

			if (minZ > geo.box.minZ)
				minZ = geo.box.minZ;
		}
		box = new Box(maxX, maxY, maxZ, minX, minY, minZ);
	}
}
