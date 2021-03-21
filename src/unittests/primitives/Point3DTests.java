package unittests.primitives;
import primitives.*;

import static primitives.Util.*;
import static java.lang.System.out;
import static org.junit.Assert.*;

import org.junit.Test;


import primitives.Point3D;
import primitives.Vector;

public class Point3DTests {

	@Test
	public void testSubtract() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Point subtraction wrong calculation
		assertEquals("Points subtraction does not work correctly", new Vector(1, 1, 1), new Point3D(2, 3, 4).subtract(new Point3D(1, 2, 3)));
	}

	@Test
	public void testAdd() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Point addition wrong calculation
		assertEquals("Point + Vector does not work correctly", Point3D.ZERO, new Point3D(1, 2, 3).add(new Vector(-1, -2, -3)));
	}

	@Test
	public void testDistanceSquared() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Point squared distance wrong calculation
		assertTrue("Squared distance wrong calculation", isZero(new Point3D(3, 5, 6).distanceSquared(new Point3D(1, 2, 3)) - 22));
	}

	@Test
	public void testDistance() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Point distance wrong calculation
		assertTrue("Squared distance wrong calculation", isZero(new Point3D(4, 6, 3).distance(new Point3D(1, 2, 3)) - 5));
	}

}
