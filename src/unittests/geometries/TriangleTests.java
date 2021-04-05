/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Polygon;
import geometries.Triangle;
import primitives.Point3D;
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

		// ============ Equivalence Partitions Tests ==============

		// TC11: Inside polygon/triangle

		// TC12: Outside against edge

		// TC13: Outside against vertex

		// =============== Boundary Values Tests ==================
		// ***** (the ray begins "before" the plane)

		// TC21: On edge

		// TC22: In vertex

		// TC23: On edge's continuation
	}
}
