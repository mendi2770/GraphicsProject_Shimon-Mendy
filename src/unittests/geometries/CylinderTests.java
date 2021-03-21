/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Cylinder;
import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author 97253
 *
 */
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Vector vec = new Vector(1, 1, 1);
		Point3D pt = new Point3D(2, 2, 2);
		Ray ray = new Ray(vec, pt);
		Cylinder cyl = new Cylinder(ray, 5, 10);
		// ============ Equivalence Partitions Tests ==============
        // TC01: Wrong normal calculation (in case the point is not across the ray.p0)
		assertEquals("getNormal() - does not work correctly",new Vector(Math.sqrt(1/2d),-1 * Math.sqrt(1/2d),0), cyl.getNormal(new Point3D(12,2,7)));	
	}

}
