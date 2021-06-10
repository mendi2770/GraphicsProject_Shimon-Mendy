package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import java.lang.Math;

/**
 * Test rendering an image
 * 
 * @author Dan
 */
public class AmazingSpiral {
	private final Camera camera = new Camera(new Point3D(0, 0, -500), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
			.setDistance(1000).setViewPlaneSize(200, 200).setAmountOfSampledRays(0);
	private final Scene scene = new Scene("Test scene");

	private static final Color color = new Color(200, 0, 0);
	private static final Material mat = new Material().setKd(0.5).setKs(0.5).setnShininess(60);


	/**
	 * Produce a scene with a 3D model and render it into a png image
	 */
	@Test
	public void teapot1() {
		
		for (int i = 0; i < 50; i++)
		scene.geometries.add( 
				new Sphere(new Point3D(i, Math.sin(i), 0.00001 * i), 0.2).setEmission(color).setMaterial(mat) //
		);
		
		scene.lights.add(new PointLight(new Color(500, 500, 500), new Point3D(100, 0, -100), 1, 0.0005, 0.0005) //
				.setkQ(0.000001));

		ImageWriter imageWriter = new ImageWriter("AmazingSpiral", 800, 800);
		Render render = new Render() //
				.setCamera(camera) //
				.setImageWriter(imageWriter) //
				.setRayTracerBasic(new RayTracerBasic(scene)) //
				.setMultithreading(3).setDebugPrint();
		render.renderImage();
		render.printGrid(50, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}

}