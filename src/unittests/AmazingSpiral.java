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
//	private final Camera camera = new Camera(new Point3D(0, 0, -200), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
//			.setDistance(1000).setViewPlaneSize(200, 200);
	private final Camera camera = new Camera(new Point3D(-200, 0, 0), new Vector(1, 0, 0), new Vector(0, 1, 0)) //
			.setDistance(1000).setViewPlaneSize(200, 200);
	private final Scene scene = new Scene("Test scene");

	private static final Color color = new Color(200, 0, 0); // Red
	private static final Color color2 = new Color(255, 215, 0); // Gold
	private static final Material mat = new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkR(0.4);
	private static final double radius = 0.2;

	/**
	 * Produce a scene with a 3D model and render it into a png image
	 */
	@Test
	public void goblet() {

		double angle = 0.261; // 15 degree
		int totalRotations = 26; // 15 degree * 24 = 360 and two to close the gap
		Vector toMoveVector;
		double oldX = -5;
		double oldY = 0;
		double newX;
		double newY;
		int headDepthLevel = -7;
		int legDepthLevel = -12;
		int bottomDepthLevel = -15;

		Point3D a;
		Point3D b;

		/////////////// Head of the goblet /////////////////////
		Point3D[] pntsLevel1 = new Point3D[totalRotations];
		Point3D[] pntsLevel2 = new Point3D[totalRotations];
		for (int j = 0; j > headDepthLevel; j--) // Depth level
		{
			oldX = headDepthLevel - j;
			oldY = 0;

			// Obtaining the points:
			for (int i = 0; i < totalRotations; i = i + 1) { // Number of angle rotations

				// Vector rotation and new point creation:
				newX = Math.cos(angle) * oldX - Math.sin(angle) * oldY;
				newY = Math.sin(angle) * oldX + Math.cos(angle) * oldY;
				toMoveVector = new Vector(newX, newY, 0);
				a = new Point3D(0, 0, j).add(toMoveVector);

				// Adding the new point to the appropriate array:
				if (j % 2 == 0)
					pntsLevel1[i] = a;
				else
					pntsLevel2[i] = a;

				// Updating to the newest point on the circle:
				oldX = newX;
				oldY = newY;
			}

			// Creating the triangles:
			if (j < 0) {
				for (int i = 0; i < totalRotations - 1; i++) {
					if (j%2 == 0)
					{
					scene.geometries.add(
					new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(color).setMaterial(mat),
					new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(color2)
							.setMaterial(mat));
					}
					else
					{
					scene.geometries.add(
					new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(color2).setMaterial(mat),
					new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(color)
							.setMaterial(mat));
					}
						
				}
			}
		}

		/////////////// Leg of the goblet /////////////////////

		angle = 0.05235; // 3 degree
		totalRotations = 122; // 3 degree * 120 = 360 and two to close the gap
		pntsLevel1 = new Point3D[totalRotations];
		pntsLevel2 = new Point3D[totalRotations];
		oldX = -1;
		oldY = 0;
		// Obtaining the points:
		for (int i = 0; i < totalRotations; i = i + 1) { // Number of angle rotations
															// Vector rotation and new point creation:
			newX = Math.cos(angle) * oldX - Math.sin(angle) * oldY;
			newY = Math.sin(angle) * oldX + Math.cos(angle) * oldY;
			toMoveVector = new Vector(newX, newY, 0);
			a = new Point3D(0, 0, headDepthLevel + 1).add(toMoveVector);
			b = new Point3D(0, 0, legDepthLevel).add(toMoveVector);
			// Adding the new point to the appropriate array:
			pntsLevel1[i] = a;
			pntsLevel2[i] = b;

			// Updating to the newest point on the circle:
			oldX = newX;
			oldY = newY;
		}

		// Creating the Polygons:
		for (int i = 0; i < totalRotations - 1; i++) {
			scene.geometries.add(
					new Polygon(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1], pntsLevel2[i]).setEmission(color).setMaterial(mat));
		}

		/////////////// Bottom of the goblet /////////////////////

		angle = 0.261; // 15 degree
		totalRotations = 26; // 15 degree * 24 = 360 and two to close the gap
		pntsLevel1 = new Point3D[totalRotations];
		pntsLevel2 = new Point3D[totalRotations];
		oldX = -1;
		oldY = 0;
		for (int j = 0; j > bottomDepthLevel; j--) // Depth level
		{
			oldX = bottomDepthLevel - j;
			oldY = 0;

			// Obtaining the points:
			for (int i = 0; i < totalRotations; i = i + 1) { // Number of angle rotations

				// Vector rotation and new point creation:
				newX = Math.cos(angle) * oldX - Math.sin(angle) * oldY;
				newY = Math.sin(angle) * oldX + Math.cos(angle) * oldY;
				toMoveVector = new Vector(newX, newY, 0);
				a = new Point3D(0, 0, j).add(toMoveVector);

				// Adding the new point to the appropriate array:
				if (j % 2 == 0)
					pntsLevel1[i] = a;
				else
					pntsLevel2[i] = a;

				// Updating to the newest point on the circle:
				oldX = newX;
				oldY = newY;
			}

			// Creating the triangles:
			if (j < 0) {
				for (int i = 0; i < totalRotations - 1; i++) {
					if (j%2 == 0)
					{
					scene.geometries.add(
					new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(color).setMaterial(mat),
					new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(color2)
							.setMaterial(mat));
					}
					else
					{
					scene.geometries.add(
					new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(color2).setMaterial(mat),
					new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(color)
							.setMaterial(mat));
					}
						
				}
			}
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