/**
 * 
 */
package unittests.primitives;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import geometries.Intersectable.GeoPoint;
import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author 97253 Ray tests unit for "FindClosestPoint" function
 */
public class RayTests {

	
	/**
	 * Tests method findClosestGeoPoint 
	 * {@link primitives.Ray#findClosestGeoPoint(primitives.Point3D[])}. Test method
	 * Test method for getting the closest point to ray point 
	 */
	@Test
	public void testFindClosestGeoPoint() {
		Plane plane = new Plane(new Point3D(1, 1, 1),null);
		Ray ray = new Ray(new Vector(-1, 1, 0), new Point3D(1, 0, 0));

		// ============ Equivalence Partitions Tests ==============
		// TC20: The point that was found is in the middle of the list

		assertEquals("The point is not the closeset to the ray (middle test)",new GeoPoint(plane, new Point3D(2, 0, 0)),
				ray.findClosestGeoPoint(List.of(new GeoPoint(plane, new Point3D(5, 0, 0)),
						new GeoPoint(plane, new Point3D(2, 0, 0)), new GeoPoint(plane, new Point3D(8, 0, 0)))));

		// =============== Boundary Values Tests ==================

		// TC31: No point was found (empty list)
		assertNull("The list should return null", ray.findClosestGeoPoint(null));

		// TC32: The point that was found is in the head of the list
		assertEquals("The point is not close to the ray (head test)", new GeoPoint(plane, new Point3D(2, 0, 0)),
				ray.findClosestGeoPoint(List.of(new GeoPoint(plane, new Point3D(2, 0, 0)),
						new GeoPoint(plane, new Point3D(5, 0, 0)), new GeoPoint(plane, new Point3D(8, 0, 0)))));

		// TC33: The point that was found is in the tail of the list
		assertEquals("The point is not close to the ray (tail test)", new GeoPoint(plane, new Point3D(2, 0, 0)),
				ray.findClosestGeoPoint(List.of(new GeoPoint(plane, new Point3D(8, 0, 0)),
						new GeoPoint(plane, new Point3D(5, 0, 0)), new GeoPoint(plane, new Point3D(2, 0, 0)))));
	}

}
