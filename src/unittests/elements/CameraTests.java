package unittests.elements;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import elements.Camera;
import primitives.*;

/**
 * Testing Camera Class
 * 
 * @author Dan
 *
 */
public class CameraTests {

	/**
	 * Test method for {@link elements.Camera#constructor(elements.Camera)}.
	 */
	@Test
	public void testConstructors() {
		try {
			Camera camera = new Camera(Point3D.ZERO, new Vector(1, 0, 1), new Vector(2, 0, 0));
			fail("two vectors are orthogonal");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for
	 * {@link elements.Camera#constructRayThroughPixel(int, int, int, int)}.
	 */
	@Test
	public void testConstructRayThroughPixel() {
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)).setDistance(10);

		// ============ Equivalence Partitions Tests ==============
		// TC01: 3X3 Corner (0,0)
		assertEquals("Bad ray", new Ray(new Vector(-2, -2, 10), Point3D.ZERO),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 0, 0));

		// TC02: 4X4 Corner (0,0)
		 assertEquals("Bad ray", new Ray(new Vector(-3, -3, 10), Point3D.ZERO),
		camera.setViewPlaneSize(8, 8).constructRayThroughPixel(4, 4, 0, 0));

		// TC03: 4X4 Side (0,1)
		 assertEquals("Bad ray", new Ray(new Vector(-1, -3, 10), Point3D.ZERO),
		 camera.setViewPlaneSize(8, 8).constructRayThroughPixel(4, 4, 1, 0));

		 //TC04: 4X4 Inside (1,1)
		 assertEquals("Bad ray", new Ray(new Vector(-1, -1, 10), Point3D.ZERO),
		 camera.setViewPlaneSize(8, 8).constructRayThroughPixel(4, 4, 1, 1));

		// =============== Boundary Values Tests ==================
		// TC11: 3X3 Center (1,1)
		assertEquals("Bad ray", new Ray(new Vector(0, 0, 10), Point3D.ZERO),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 1, 1));

		// TC12: 3X3 Center of Upper Side (0,1)
		assertEquals("Bad ray", new Ray(new Vector(0, -2, 10), Point3D.ZERO),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 1, 0));

		// TC13: 3X3 Center of Left Side (1,0)
		assertEquals("Bad ray", new Ray(new Vector(-2, 0, 10), Point3D.ZERO),
				camera.setViewPlaneSize(6, 6).constructRayThroughPixel(3, 3, 0, 1));

	}

}
