/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.awt.List;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author 97253 ray tests unit
 */
public class RayTests {

	/**
	 * Test method for closest point to ray point
	 * {@link primitives.Ray#findClosestPoint(primitives.Point3D[])}.
	 */
	@Test
	public void testFindClosestPoint() {
		Ray ray = new Ray(new Vector(-1, 1, 0), new Point3D(1, 0, 0));

		// ============ Equivalence Partitions Tests ==============
		// TC01: The point that was found is in the middle of the list

		assertEquals("The point is not close to the ray (middle test)", new Point3D(2, 0, 0),
				ray.findClosestPoint(new Point3D(5, 0, 0), new Point3D(2, 0, 0), new Point3D(8, 0, 0)));

		// =============== Boundary Values Tests ==================

		// TC11: No point was found (empty list)
		assertNull("The list should return null", ray.findClosestPoint(null));

		// TC12: The point that was found is in the head of the list
		assertEquals("The point is not close to the ray (head test)", new Point3D(2, 0, 0),
				ray.findClosestPoint(new Point3D(2, 0, 0), new Point3D(5, 0, 0), new Point3D(8, 0, 0)));

		// TC13: The point that was found is in the tail of the list
		assertEquals("The point is not close to the ray (tail test)", new Point3D(2, 0, 0),
				ray.findClosestPoint(new Point3D(8, 0, 0), new Point3D(5, 0, 0), new Point3D(2, 0, 0)));	
	}

}
