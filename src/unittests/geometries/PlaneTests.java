/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Polygon;
import geometries.*;
import primitives.*;

/**
 * @author 97253
 *
 */
public class PlaneTests {

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testPlanePoint3DPoint3DPoint3D() {
		// =============== Boundary Values Tests ==================
		
		// TC01: First and second points are the same
		try {			
			Plane plane = new Plane(new Point3D(1, 2, 3), new Point3D(1, 2, 3), new Point3D(3, 6, 9));
			fail("ERROR: Two points are the same point, exception was not thrown");
		} catch (Exception e) {
		}

		// TC02: The three points are on the same line
		try {			
			 Plane plane = new Plane(new Point3D(1, 2, 3), new Point3D(2, 4, 6), new Point3D(3, 6, 9));
				fail("ERROR: The points are on the same line, exception was not thrown");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Plane pl = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
		double sqrt3 = Math.sqrt(1d / 3); // Normalizing the vector components
		// Two opposite sides of the vector must be checked:
		assertTrue("Bad normal to plane", new Vector(sqrt3, sqrt3, sqrt3).equals(pl.getNormal(new Point3D(0, 0, 1)))
				|| new Vector(-1 * sqrt3, -1 * sqrt3, -1 * sqrt3).equals(pl.getNormal(new Point3D(0, 0, 1))));
	}
	
	/**
	 * Test method for {@link geometries.Plane#findIntersections}.
	 */
	@Test
	public void testFindIntersections() {
		
		Plane plane = new Plane(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
		// ============ Equivalence Partitions Tests ==============
		//**** Group:The Ray must be neither orthogonal nor parallel to the plane
		
		//TC01: Ray intersects the plane(1 point)
		List<Point3D> result = plane.findIntersections(new Ray(new Vector(-3, -1, 0), new Point3D(2, 4, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses plane once", new Point3D(-1.75, 2.75, 0), result.get(0));
		
		//TC02: Ray does not intersect the plane(0 point)
		assertNull("Ray's line out of plane",
				plane.findIntersections(new Ray(new Vector(3, 4, 0), new Point3D(2, 4, 0))));

		// =============== Boundary Values Tests ==================
		
		//**** Group: Ray is parallel to the plane
		
		//TC10: The ray included in the plane
		assertNull("Ray's line out of plane",
				plane.findIntersections(new Ray(new Vector(-1, 1, 0), new Point3D(-1, -1, 0))));
		
		//TC11: The ray is not included in the plane	
		assertNull("Ray's line out of plane",
				plane.findIntersections(new Ray(new Vector(-1, 1, 0), new Point3D(1, 0, 0))));
		
		//**** Group: Ray is orthogonal to the plane
		
		//TC12: The ray starts is in the plane
		
		//TC13: The ray starts is before the plane
		
		//TC14: The ray starts is after the plane
		
		
		
	}
}
