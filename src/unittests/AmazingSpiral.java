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
	private final Camera camera = new Camera(new Point3D(0, 0, -100), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
			.setDistance(1000).setViewPlaneSize(200, 200);
	private final Scene scene = new Scene("Test scene");

	private static final Color color = new Color(200, 0, 0);
	private static final Material mat = new Material().setKd(0.5).setKs(0.5).setnShininess(60);
	private static final double radius = 0.7;

	/**
	 * Produce a scene with a 3D model and render it into a png image
	 */
	@Test
	public void teapot1() {
		Point3D A;
		Point3D B;
		Point3D C;
		Point3D D;
		Point3D Aminus;
		Point3D Bminus;
		Point3D Cminus;
		Point3D Dminus;
		int j = 0;
		for (int i = -25; i < 25; i++)			
		{
				A = new Point3D(i, Math.sqrt(25 - i * i), j);
				B = new Point3D(i + 1, Math.sqrt(25 - i * i - 2*i - 1), j);
				C = new Point3D(i, Math.sqrt(20 - i * i), j - 1);
				D = new Point3D(i + 1, Math.sqrt(20 - i * i - 2*i - 1), j - 1);
				Aminus = new Point3D(i, Math.sqrt(25 - i * i), j);
				Bminus = new Point3D(i + 1, Math.sqrt(25 - i * i - 2*i - 1), j);
				Cminus = new Point3D(i, Math.sqrt(20 - i * i), j - 1);
				D = new Point3D(i + 1, Math.sqrt(20 - i * i - 2*i - 1), j - 1);
				scene.geometries.add(						
						new Triangle(A, B, C).setEmission(color)
								.setMaterial(mat),
					    new Triangle(B, C, D).setEmission(color)
								.setMaterial(mat));
		}
		
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