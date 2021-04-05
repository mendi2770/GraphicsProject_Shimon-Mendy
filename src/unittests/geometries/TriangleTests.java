/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.Polygon;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author 97253
 *
 */
public class TriangleTests {

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Triangle triangle = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3); // Normalizing the vector components
		assertTrue("Bad normal to plane",
				new Vector(sqrt3, sqrt3, sqrt3).equals(triangle.getNormal(new Point3D(0, 0, 1)))
						|| new Vector(-1 * sqrt3, -1 * sqrt3, -1 * sqrt3)
								.equals(triangle.getNormal(new Point3D(0, 0, 1))));
	}

	/**
	 * Test method for {@link geometries.Polygon#findIntersections}.
	 */
	@Test
	public void testFindIntersections() {
		
		Triangle tr = new Triangle(new Point3D(1,0,0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
		// ============ Equivalence Partitions Tests ==============

		// TC11: Inside polygon/triangle
		List<Point3D> result = tr.findIntersections(new Ray(new Vector(new Point3D(1, 2, 1) ), new Point3D(-1, -2, -1)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses triangle once", new Point3D(0.25, 0.5, 0.25), result.get(0));		
		
		// TC12: Outside against edge
		assertNull("Ray's crosses outside the triangle",
				 tr.findIntersections(new Ray(new Vector(1, 3, 3), new Point3D(-1, -2, -1))));
		
		// TC13: Outside against vertex
		assertNull("Ray's crosses outside the triangle",
				 tr.findIntersections(new Ray(new Vector(1, 2, 4), new Point3D(-1, -2, -1))));

		
		// =============== Boundary Values Tests ==================
		
		// ***** (the ray begins "before" the plane)

		// TC21: On edge

		// TC22: In vertex

		// TC23: On edge's continuation
	}
}
