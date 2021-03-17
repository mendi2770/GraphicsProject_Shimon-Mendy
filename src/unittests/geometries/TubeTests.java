/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * @author 97253
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Vector vec = new Vector(1, 1, 1);
		Point3D pt = new Point3D(0, 0, 0);
		Ray ray = new Ray(vec, pt);
		Tube tube = new Tube(ray, 5);
        // =============== Boundary Values Tests ==================
	}

}
