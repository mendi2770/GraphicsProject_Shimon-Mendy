/**
 * 
 */
package unittests.geometries;
import geometries.*;
import primitives.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author shimo
 * Testing Geometries Class
 */
public class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#Geometries()}.
	 */
	@Test
	public void testGeometries() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Geometries#Geometries(geometries.Intersectable[])}.
	 */
	@Test
	public void testGeometriesIntersectableArray() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Geometries#add(geometries.Intersectable[])}.
	 */
	@Test
	public void testAdd() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Sphere sph = new Sphere(new Point3D(1, 1, 1), 1);
		Plane plane = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 2));
		Triangle tr = new Triangle(new Point3D(1,0,0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
		Geometries collection = new Geometries(sph, plane, tr);
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: Some of the Geometries are intersected 
		Ray ray = new Ray(new Vector(1, 1, 1), new Point3D(-1, 0, 0));
		assertEquals("Wrong number of intersection points", 3, collection.findIntersections(ray).size()); // Intersects only plane and sphere
		
		// =============== Boundary Values Tests ==================
		// TC11: All the Geometries are intersected
		ray = new Ray(new Vector(-1, -1, -1), new Point3D(2, 2, 2.5));
		assertEquals("Wrong number of intersection points", 4, collection.findIntersections(ray).size()); 
		
		// TC12: No Geometries are intersected
		ray = new Ray(new Vector(-1, -1, -1), new Point3D(-1, 0, 0));
		assertNull("No intersection points", collection.findIntersections(ray)); 
				
		// TC13: Only one Geometry shape is intersected
		ray = new Ray(new Vector(-1, -1, -1), new Point3D(2, 0, 2));
		assertEquals("Wrong number of intersection points", 1, collection.findIntersections(ray).size());  // Intersects only plane
		
		// TC14: Empty Geometries collection
		collection = new Geometries();
		assertNull("No geometry shapes in the collection",
				collection.findIntersections(new Ray(new Vector(1, 1, 0), new Point3D(-1, 0, 0))));
	
	}
}
