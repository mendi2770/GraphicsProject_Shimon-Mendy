package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class LightsTests {
	private Scene scene1 = new Scene("Test scene");
	private Scene scene2 = new Scene("Test scene") //
			.setAmbientLight(new Color(java.awt.Color.WHITE), 0.15);
	private Camera camera1 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(150, 150) //
			.setDistance(1000);
	private Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(200, 200) //
			.setDistance(1000);

	private static Geometry triangle1 = new Triangle( //
			new Point3D(-150, -150, -150), new Point3D(150, -150, -150), new Point3D(75, 75, -150));
	private static Geometry triangle2 = new Triangle( //
			new Point3D(-150, -150, -150), new Point3D(-70, 70, -50), new Point3D(75, 75, -150));
	private static Geometry sphere = new Sphere(new Point3D(0, 0, -50), 50) //
			.setEmission(new Color(java.awt.Color.BLUE)) //
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100));

	/**
	 * Produce a picture of a sphere lighted by a directional light
	 */
	@Test
	public void sphereDirectional() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, -1)));

		ImageWriter imageWriter = new ImageWriter("sphereDirectional", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracerBasic(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a point light
	 */
	@Test
	public void spherePoint() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new PointLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), 1, 0.00001, 0.000001));

		ImageWriter imageWriter = new ImageWriter("spherePoint", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracerBasic(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void sphereSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2), 1,
				0.00001, 0.00000001));

		ImageWriter imageWriter = new ImageWriter("sphereSpot", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //				
				.setCamera(camera1) //
				.setRayTracerBasic(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */
	@Test
	public void trianglesDirectional() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.8).setKs(0.2).setnShininess(300)), //
				triangle2.setMaterial(new Material().setKd(0.8).setKs(0.2).setnShininess(300)));
		scene2.lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, -1)));

		ImageWriter imageWriter = new ImageWriter("trianglesDirectional", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracerBasic(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a point light
	 */
	@Test
	public void trianglesPoint() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)), //
				triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)));
		scene2.lights.add(new PointLight(new Color(500, 250, 250), new Point3D(10, -10, -130), 1, 0.0005, 0.0005));

		ImageWriter imageWriter = new ImageWriter("trianglesPoint", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracerBasic(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light
	 */
	@Test
	public void trianglesSpot() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)),
				triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)));
		scene2.lights.add(new SpotLight(new Color(500, 250, 250), new Point3D(10, -10, -130), new Vector(-2, -2, -1), 1,
				0.0001, 0.000005));

		ImageWriter imageWriter = new ImageWriter("trianglesSpot", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracerBasic(new RayTracerBasic(scene2));
		render.renderImage();
		render.writeToImage();
	}
	

	/**
	 * Produce a picture of a sphere lighted by all three light source types
	 */
	@Test
	public void sphereDirectionalPointSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(new Color(500, 400, 50), new Vector(1, -4, -1))); // Above the sphere
		scene1.lights.add(new PointLight(new Color(400, 100, 0), new Point3D(100, 0, 0), 1, 0.00005, 0.000003)); // Right of the sphere
		scene1.lights.add(new SpotLight(new Color(700, 300, 100), new Point3D(-100, -50, 50), new Vector(1, 1, -3), 2,
				0.00002, 0.00000005)); // Left of the sphere


		ImageWriter imageWriter = new ImageWriter("sphereAllLights", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera1) //
				.setRayTracerBasic(new RayTracerBasic(scene1));
		render.renderImage();
		render.writeToImage();
	}
	

	/**
	 * Produce a picture of a two triangles lighted by all three light source types
	 */
	@Test
	public void trianglesDirectionalPointSpot() {
		scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)),
				triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)));
		scene2.lights.add(new DirectionalLight(new Color(30, 150, 90), new Vector(0, 20, -10))); // Effects the both triangles
		scene2.lights.add(new PointLight(new Color(400, 100, 0), new Point3D(50, -20, -90), 1, 0.00005, 0.000003)); //  Orange - hits up and right sides
		scene2.lights.add(new SpotLight(new Color(20, 70, 300), new Point3D(10, -10, -50), new Vector(1, -1, -1), 1,
				0.0001, 0.000005)); // The blue - hits the center
		
		ImageWriter imageWriter = new ImageWriter("trianglesAllLights", 500, 500);
		Render render = new Render()//
				.setImageWriter(imageWriter) //
				.setCamera(camera2) //
				.setRayTracerBasic(new RayTracerBasic(scene2).turnAllBoxesOn());
		render.renderImage();
		render.writeToImage();
	}
}
