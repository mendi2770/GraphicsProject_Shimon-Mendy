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
	 * 
	 */
	public Geometries() {
		// TODO Auto-generated constructor stub
		intersectables = new LinkedList<>();
	}

	public Geometries(Intersectable... geometries)
	{
		intersectables = List.of(geometries);
	}
	
	public void add(Intersectable... geometries)
	{
		intersectables.addAll(List.of(geometries));
	}
	
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		if (intersectables.isEmpty())
			return null;
		LinkedList<Point3D> result = new LinkedList<Point3D>();
		
		for (Intersectable geom : intersectables)
		{
			List<Point3D> geomResult = geom.findIntersections(ray);
			if (geomResult != null)
				result.addAll(geomResult);
		}
		
		if (result.isEmpty())
			return null;
		else 
			return result;
	}

}
