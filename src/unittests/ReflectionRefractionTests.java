/**
 * 
 */
package unittests;

import org.junit.Test;

import elements.*;
import geometries.Geometries;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(150, 150).setDistance(1000).setAmountOfSampledRays(0);

		scene.geometries.add( //
				new Sphere(new Point3D(0, 0, -50), 50) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setnShininess(100).setkT(0.3)),
				new Sphere(new Point3D(0, 0, -50), 25) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2), 1, 0, 0) //
						.setkL(0.0004).setkQ(0.0000006));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCamera(camera) //
				.setRayTracerBasic(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(2500, 2500).setDistance(10000); //

		scene.setAmbientLight(new Color(255, 255, 255), 0.1);

		scene.geometries.add( //
				new Sphere(new Point3D(-950, -900, -1000), 400) //
						.setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20).setkT(0.5)),
				new Sphere(new Point3D(-950, -900, -1000), 200) //
						.setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setnShininess(20)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(670, 670, 3000)) //
								.setEmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setkR(1)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(-1500, -1500, -2000)) //
								.setEmission(new Color(20, 20, 20)) //
								.setMaterial(new Material().setkR(0.5)));

		scene.lights.add(
				new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4), 1, 0, 0) //
						.setkL(0.00001).setkQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBasic(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		scene.setAmbientLight(new Color(java.awt.Color.WHITE), 0.15);

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)), //
				new Sphere(new Point3D(60, 50, -50), 30) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setkT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1), 1, 0, 0) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBasic(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a pyramid lighted by a spot light with a mirror effects
	 */
	@Test
	public void pyramidTransparentSphere() {
		Camera camera = new Camera(new Point3D(-140, 20, 35), new Vector(1, -0.15, -0.25), new Vector(1, 0, 4))//
				.setViewPlaneSize(200, 200).setDistance(1000).setAmountOfSampledRays(0);

		scene.setAmbientLight(new Color(java.awt.Color.WHITE), 0.15);

		scene.geometries.add( //
				new Geometries(
				new Plane(new Point3D(10, 0, -30), new Point3D(0, 10, -30), new Point3D(-10, 0, -30))
				.setEmission(new Color(190,0,190)).setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0).setkR(1)),
				new Polygon(new Point3D(10, 0, -10), new Point3D(0, 10, -10), new Point3D(-10, 0, -10),
						new Point3D(0, -10, -10))
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0.2).setkR(0)),
				new Polygon(new Point3D(10, 0, -10), new Point3D(0, -10, -10), new Point3D(0, -10, 0),
						new Point3D(10, 0, 0))
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0).setkR(1)),
				new Polygon(new Point3D(10, 0, -10), new Point3D(0, 10, -10), new Point3D(0, 10, 0),
						new Point3D(10, 0, 0))
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0.4)),
				new Polygon(new Point3D(-10, 0, -10), new Point3D(0, 10, -10), new Point3D(0, 10, 0),
						new Point3D(-10, 0, 0))
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0.8)),
				new Polygon(new Point3D(-10, 0, -10), new Point3D(0, -10, -10), new Point3D(0, -10, 0),
						new Point3D(-10, 0, 0))
								.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0.4)),
				new Polygon(new Point3D(10, 0, 0), new Point3D(0, -10, 0), new Point3D(-10, 0, 0),
					new Point3D(0, 10, 0))
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0.5))),
				new Geometries(new Triangle(new Point3D(10, 0, 0), new Point3D(0, -10, 0), new Point3D(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(60).setkT(0.5).setkR(0)), //
				new Triangle(new Point3D(10, 0, 0), new Point3D(0, 10, 0), new Point3D(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(60).setkT(1)), //
				new Triangle(new Point3D(-10, 0, 0), new Point3D(0, 10, 0), new Point3D(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(60).setkT(0.8)), //
				new Triangle(new Point3D(-10, 0, 0), new Point3D(0, -10, 0), new Point3D(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(60).setkT(1))), //
				new Geometries(new Sphere(new Point3D(0, 0, 3), 2) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setkT(0.6)),
				new Sphere(new Point3D(3, 0, -6), 2) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setkT(0).setkR(1)),
				new Sphere(new Point3D(-3, 3, -6), 2) //
						.setEmission(new Color(java.awt.Color.GREEN)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setkT(0.5))));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(30, 30, 100), new Vector(0, 0, -1), 1, 0, 0) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadowPyramid", 600, 600);
		Render render = new Render() 
				.setMultithreading(3)//
				.setDebugPrint()//
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBasic(new RayTracerBasic(scene).turnAllBoxesOn())
				.setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}
}
