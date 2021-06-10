package unittests;

import org.junit.Test;

import com.sun.source.doctree.SinceTree;

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
	public void goblet() {

		// Head of the goblet:
		Point3D center = new Point3D(0, 0, 0);
		Point3D newPoint;
		double angle = 0.261; // 15 degree
		Vector toMoveVector;
		double oldX = -5;
		double oldY = 0;
		double newX;
		double newY;
		double radius = 0.2;
		int totalRotations = 24; // 15 degree * 24 = 360
		for (int j = 0; j > -5; j--) // Depth level
		{
			
			scene.geometries.add(new Sphere(new Point3D(oldX, oldY, j), radius).setEmission(color).setMaterial(mat));
			for (double i = 0; i < totalRotations; i = i + 1) { // Number of angle rotations
				newX = Math.cos(angle) * oldX - Math.sin(angle) * oldY;
				newY = Math.sin(angle) * oldX + Math.cos(angle) * oldY;
				toMoveVector = new Vector(newX, newY, j);
				newPoint = center.add(toMoveVector);
				scene.geometries.add(new Sphere(newPoint, radius).setEmission(color).setMaterial(mat));
				oldX = newX;
				oldY = newY;
			}
			oldX++;
			radius = radius * 0.8;
			center = new Point3D(0, 0, j);
		}

		// Lights and rendering
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