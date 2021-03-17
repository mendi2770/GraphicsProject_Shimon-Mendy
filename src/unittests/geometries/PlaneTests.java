/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Polygon;
import primitives.Point3D;
import primitives.Vector;

import geometries.*;
import primitives.*;

/**
 * @author 97253
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
	 */
	@Test
	public void testPlanePoint3DPoint3DPoint3D() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point3D, primitives.Vector)}.
	 */
	@Test
	public void testPlanePoint3DVector() {
		fail("Not yet implemented");
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
        		||  new Vector(-1 * sqrt3, -1 * sqrt3, -1 * sqrt3).equals(pl.getNormal(new Point3D(0, 0, 1))));
	}

}
