/**
 * 
 */
package unittests.geometries;


import geometries.*;
import primitives.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Polygon;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author 97253
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
		// TC01: Wrong normal calculation 
        Sphere sph = new Sphere(new Point3D(1,1,1), 5); // The equation for the sphere is: (x-1)^2 + (y-1)^2 + (z-1)^2 = 25
        // The vector is: (4,5,1) - (1,1,1) = (3,4,0) => After normalization: (3/5, 4/5, 0)
        assertEquals("Bad normal to Sphere", new Vector(3d/5, 4d/5, 0), sph.getNormal(new Point3D(4, 5, 1)));
	}
	
	public void testFindIntersections() {
		Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray's line is outside the sphere (0 points)
		assertNull("Ray's line out of sphere", sphere.findIntersections(new Ray(new Vector(1, 1, 0), new Point3D(-1, 0, 0))));
		
		// TC02: Ray starts before and crosses the sphere (2 points)
		Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
		Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0); 
		List<Point3D> result = sphere.findIntersections(new Ray(
		new Vector(3, 1, 0), new Point3D(-1, 0, 0)));
		assertEquals("Wrong number of points", 2, result.size());
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals("Ray crosses sphere", List.of(p1, p2), result);
		
		// TC03: Ray starts inside the sphere (1 point)
		
		result = sphere.findIntersections(new Ray(
		new Vector(3, 1, 0), new Point3D(0.5, 0.5, 0)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses sphere once", p2, result.get(0));
		
		// TC04: Ray starts after the sphere (0 points)
		assertNull("Ray's line out of sphere", sphere.findIntersections(new Ray(new Vector(3, 1, 0), new Point3D(2, 1, 0))));
		
		// =============== Boundary Values Tests ==================
		// **** Group: Ray's line crosses the sphere (but not the center)
		// TC11: Ray starts at sphere and goes inside (1 points)
		
		// TC12: Ray starts at sphere and goes outside (0 points)
		
		// **** Group: Ray's line goes through the center
		// TC13: Ray starts before the sphere (2 points)
		
		// TC14: Ray starts at sphere and goes inside (1 points)
		
		// TC15: Ray starts inside (1 points)
		
		// TC16: Ray starts at the center (1 points)
		
		// TC17: Ray starts at sphere and goes outside (0 points)
		
		// TC18: Ray starts after sphere (0 points)
		
		// **** Group: Ray's line is tangent to the sphere (all tests 0 points)
		// TC19: Ray starts before the tangent point
		
		// TC20: Ray starts at the tangent point
		
		// TC21: Ray starts after the tangent point
		
		// **** Group: Special cases
		// TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
		}
}
