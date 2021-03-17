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
	 * Test method for {@link geometries.Triangle#Triangle(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testTriangle() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
		Triangle triangle = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3); // Normalizing the vector components
        assertTrue("Bad normal to plane", new Vector(sqrt3, sqrt3, sqrt3).equals(triangle.getNormal(new Point3D(0, 0, 1))) 
        		||  new Vector(-1 * sqrt3, -1 * sqrt3, -1 * sqrt3).equals(triangle.getNormal(new Point3D(0, 0, 1))));
	}
}
