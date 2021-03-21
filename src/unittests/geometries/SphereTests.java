/**
 * 
 */
package unittests.geometries;


import geometries.*;
import primitives.*;
import static org.junit.Assert.*;

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
}
