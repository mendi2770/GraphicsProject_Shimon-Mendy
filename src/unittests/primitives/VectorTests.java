/**
 * 
 */
package unittests.primitives;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;

import static primitives.Util.*;

/**
 * unit test for the vector class
 * 
 * @author 97253
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#constructor(primitives.Vector)}.
	 */
	@Test
	public void testConstructors() {

		// =============== Boundary Values Tests ==================
		try {
			Vector v1 = new Vector(0, 0, 0);
			fail("Vector(double, double, double) for Vector zero does not throw an exception");
			v1 = new Vector(Point3D.ZERO);
			fail("Vector(Point3D) for Vector zero does not throw an exception");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 * 
	 */
	@Test
	public void testSubtract() {
		assertEquals("Vectros subtraction doesn't work" , new Vector(2,3,-2) , new Vector(3,5,7).subtract(new Vector(1, 2, 9)));
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		assertEquals("Vectros addition doesn't work" , new Vector(4,7,16) , new Vector(3,5,7).add(new Vector(1, 2, 9)));
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		// ============ Equivalence Partitions Tests ==============
		assertEquals("scale() wrong scale calculation", new Vector(3, 6, 9), new Vector(1, 2, 3).scale(3));
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);
		Vector v3 = new Vector(0, 3, -2);

		// ============ Equivalence Partitions Tests ==============
		assertTrue("dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
		assertTrue("dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);
		// ============ Equivalence Partitions Tests ==============
		Vector v3 = new Vector(0, 3, -2);
		Vector vr = v1.crossProduct(v3);
		// Test that length of cross-product is proper (orthogonal vectors taken for
		// simplicity)
		assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);
		// Test cross-product result orthogonality to its operands
		assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
		assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));
		// =============== Boundary Values Tests ==================
		// test zero vector from cross-productof co-lined vectors
		try {
			v1.crossProduct(v2);
			fail("crossProduct() for parallel vectors does not throw an exception");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1, 2, 3);
		assertTrue("lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// ============ Equivalence Partitions Tests ==============
		assertTrue("length() wrong value", isZero(new Vector(0, 3, 4).length() - 5));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {

		Vector v = new Vector(1, 2, 3);
		Vector vCopy = new Vector(v.getHead());
		Vector vCopyNormalize = vCopy.normalize();
		// ============ Equivalence Partitions Tests ==============
		assertTrue("normalize() function creates a new vector", vCopy == vCopyNormalize);
		assertTrue("normalize() result is not a unit vector", isZero(vCopyNormalize.length() - 1));
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v = new Vector(1, 2, 3);
		Vector u = v.normalized();

		// ============ Equivalence Partitions Tests ==============
		assertFalse("normalizated() function does not create a new vector", u == v);
	}

}
