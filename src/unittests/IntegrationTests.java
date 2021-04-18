/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

import elements.Camera;
import geometries.*;
import primitives.*;

/**
 * @author 97253 Integration tests unit
 */
public class IntegrationTests {


	/**
	 * Integration tests of camera rays and shapes
	 */
	public void test() {
		testCameraAndSphere();
		testCameraAndPlane();
		testCameraAndTriangle();
	}



	@Test
	/**
	 * Tests the camera rays and sphere intersections
	 */
	public void testCameraAndSphere() {
		// **** Group: Sphere&Camera integration test cases ****//
		// #1: Camera rays intersects 2 points with sphere
		Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1);
		LinkedList<Ray> rayList = findRaysThroughVpPixles(camera.setViewPlaneSize(3, 3), 3, 3);
		Sphere sph = new Sphere(new Point3D(0, 0, -3), 1);
		assertEquals("Wrong number of intersections of camera rays with sphere - expected 2", 2, countIntersections(rayList, sph));

		// #2: Camera rays intersects 18 points with sphere
		camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1);
		rayList = findRaysThroughVpPixles(camera.setViewPlaneSize(3, 3), 3, 3);
		sph = new Sphere(new Point3D(0, 0, -2.5), 2.5);
		assertEquals("Wrong number of intersections of camera rays with sphere - expected 18", 18, countIntersections(rayList, sph));

		// #3: Camera rays intersects 10 points with sphere
		sph = new Sphere(new Point3D(0, 0, -2), 2);
		assertEquals("Wrong number of intersections of camera rays with sphere - expected 10", 10, countIntersections(rayList, sph));

		// #4: Camera rays intersects 9 points with sphere
		sph = new Sphere(new Point3D(0, 0, -2), 4);
		assertEquals("Wrong number of intersections of camera rays with sphere - expected 9", 9, countIntersections(rayList, sph));

		// #5: No camera rays intersection with sphere
		sph = new Sphere(new Point3D(0, 0, 1), 0.5);
		assertEquals("Wrong number of intersections of camera rays with sphere - expected 0", 0, countIntersections(rayList, sph));
	}

	@Test
	/**
	 * Tests the camera rays and plane intersections
	 */
	public void testCameraAndPlane() {
		// **** Group: Plane&Camera integration test cases ****//
		Camera  camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1);
		LinkedList<Ray> rayList = findRaysThroughVpPixles(camera.setViewPlaneSize(3, 3), 3, 3);
		
		// #11: Camera intersects 9 points with plan
		Plane plane = new Plane(new Point3D(0, 0, -4), camera.getvTo());
		assertEquals("Wrong number of intersections of camera rays with plane - expected 9", 9, countIntersections(rayList, plane));

		// #12: Camera rays intersects 9 points with plan
		plane = new Plane(new Point3D(0, 0, -4), new Vector(new Point3D(0, -0.2, 1)));
		assertEquals("Wrong number of intersections of camera rays with plane - expected 9", 9, countIntersections(rayList, plane));

		// #13: Camera rays intersects 6 points with plan
		plane = new Plane(new Point3D(0, 0, -4), new Vector(new Point3D(0, -1.5, 1)));
		assertEquals("Wrong number of intersections of camera rays with plane - expected 6", 6, countIntersections(rayList, plane));

	}

	@Test
	/**
	 * Tests the camera rays and triangle intersections
	 */
	public void testCameraAndTriangle() {
		// **** Group: Triangle&Camera integration test cases ****//
		Camera  camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1);
		LinkedList<Ray> rayList = findRaysThroughVpPixles(camera.setViewPlaneSize(3, 3), 3, 3);
		
		// #21: Camera rays intersects 1 points with triangle
		Triangle tri = new Triangle(new Point3D(0, 1, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
		assertEquals("Wrong number of intersections of camera rays with triangle - expected 1", 1, countIntersections(rayList, tri));

		// #22: Camera rays intersects 2 points with triangle
		tri = new Triangle(new Point3D(0, 20, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2));
		assertEquals("Wrong number of intersections of camera rays with triangle - expected 2", 2, countIntersections(rayList, tri));
	}
	
	/**
	 * Calculates with loop all the rays from camera through middle of each pixel
	 * 
	 * @param camera
	 * @param nX
	 * @param nY
	 * @return List of rays from camera through pixels
	 */
	public LinkedList<Ray> findRaysThroughVpPixles(Camera camera, int nX, int nY) {

		LinkedList<Ray> raysList = new LinkedList<>();
		for (int j = 0; j < nY; j++) {
			for (int i = 0; i < nX; i++) { // For each pixel calls "constructRayThroughPixel" function
				raysList.add(camera.constructRayThroughPixel(nX, nY, j, i));
			}
		}
		return raysList;
	}

	/**
	 * Finds and sums up the number of camera rays that intersect with a given shape
	 * 
	 * @param rayList
	 * @param shape
	 * @return Number of intersections of the camera rays with a given shape
	 */
	public int countIntersections(LinkedList<Ray> rayList, Intersectable shape) {
		int counter = 0;
		for (Ray ray : rayList) // The loop checks intersections for each ray with the given shape
		{
			LinkedList<Point3D> pointsList = shape.findIntersections(ray);
			if (pointsList != null)
				counter += pointsList.size();
		}
		return counter;
	}

}
