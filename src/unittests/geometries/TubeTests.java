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
		Vector vec = new Vector(1, 1, 1);
		Point3D pt = new Point3D(2, 2, 2);
		Ray ray = new Ray(vec, pt);
		Tube tube = new Tube(ray, 5);
		// ============ Equivalence Partitions Tests ==============
		assertEquals("getNormal() - does not work correctly",new Vector(Math.sqrt(1/2d),-1 * Math.sqrt(1/2d),0), tube.getNormal(new Point3D(12,2,7)));
		
		// =============== Boundary Values Tests ==================
		//checks the case if the point is across the ray point
		assertEquals("getNormal() - does not work correctly (Boundary test)",new Vector(Math.sqrt(1/2d),-1 * Math.sqrt(1/2d),0), tube.getNormal(new Point3D(7, -3, 2)));
	}

}
