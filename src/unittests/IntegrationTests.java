/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.Camera;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author 97253
 * Integration tests unit
 */
public class IntegrationTests {

	@Test
	public void test() {
		
		// First test case:
		
		Sphere sph = new Sphere(new Point3D(0, 0, -3), 1);
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)).setDistance(1);
		

	}

}
