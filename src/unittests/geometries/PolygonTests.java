/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Polygons
 * @author Dan
 *
 */
public class PolygonTests {

	/**
	 * Test method for
	 * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct concave quadrangular with vertices in correct order
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct polygon");
		}

		// TC02: Wrong vertices order
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0), new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
			fail("Constructed a polygon with wrong order of vertices");
		} catch (IllegalArgumentException e) {
		}

		// TC03: Not in the same plane
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 2, 2));
			fail("Constructed a polygon with vertices that are not in the same plane");
		} catch (IllegalArgumentException e) {
		}

		// TC04: Concave quadrangular
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
			fail("Constructed a concave polygon");
		} catch (IllegalArgumentException e) {
		}

		// =============== Boundary Values Tests ==================

		// TC10: Vertex on a side of a quadrangular
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
			fail("Constructed a polygon with vertix on a side");
		} catch (IllegalArgumentException e) {
		}

		// TC11: Last point = first point
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
			fail("Constructed a polygon with vertice on a side");
		} catch (IllegalArgumentException e) {
		}

		// TC12: Colocated points
		try {
			new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 1, 0));
			fail("Constructed a polygon with vertice on a side");
		} catch (IllegalArgumentException e) {
		}

	}

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
				new Point3D(-1, 1, 1));
		double sqrt3 = Math.sqrt(1d / 3); // Normalizing the vector components
		assertTrue("Bad normal to plane", new Vector(sqrt3, sqrt3, sqrt3).equals(pl.getNormal(new Point3D(0, 0, 1)))
				|| new Vector(-1 * sqrt3, -1 * sqrt3, -1 * sqrt3).equals(pl.getNormal(new Point3D(0, 0, 1))));
	}

	/**
	 * Test method for {@link geometries.Polygon#findIntersections}.
	 */
	@Test
	public void testFindIntersections() {
		Polygon polygon = new Polygon(new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(-1, 1, 1),new Point3D(0, 0, 1));
		// ============ Equivalence Partitions Tests ==============

		// TC11: Inside polygon/triangle
		List<Point3D> result = polygon
				.findIntersections(new Ray(new Vector(new Point3D(1, 2, 1)), new Point3D(-1, -2, -1)));
		assertEquals("Wrong number of points", 1, result.size());
		assertEquals("Ray crosses triangle once", new Point3D(0.25, 0.5, 0.25), result.get(0));

		// TC12: Outside against edge
		assertNull("Ray's crosses outside the triangle",
				polygon.findIntersections(new Ray(new Vector(1, 3, 3), new Point3D(-1, -2, -1))));

		// TC13: Outside against vertex
		assertNull("Ray's crosses outside the triangle",
				polygon.findIntersections(new Ray(new Vector(1, 2, 4), new Point3D(-1, -2, -1))));

		// =============== Boundary Values Tests ==================

		// ***** (the ray begins "before" the plane)

		// TC21: In vertex
		assertNull("Ray's crosses the triangle's vertices",
				polygon.findIntersections(new Ray(new Vector(1, 2, 2), new Point3D(-1, -2, -1))));

		// TC22: On edge
		assertNull("Ray's crosses the triangle's edge",
				polygon.findIntersections(new Ray(new Vector(1.5, 2, 1.5), new Point3D(-1, -2, -1))));

		// TC23: On edge's continuation
		assertNull("Ray's crosses the triangle's edge",
				polygon.findIntersections(new Ray(new Vector(0, 2, 3), new Point3D(-1, -2, -1))));
	}

}
