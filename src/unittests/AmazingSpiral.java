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

	// Camera from up:
//	private final Camera camera = new Camera(new Point3D(0, 0, 200), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//			.setDistance(1000).setViewPlaneSize(200, 200);
//	

	// Camera from x asix:
//	private final Camera camera = new Camera(new Point3D(200, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
//			.setDistance(1000).setViewPlaneSize(200, 200);
	
// Camera from -x axis:
//	private final Camera camera = new Camera(new Point3D(-200, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
//			.setDistance(1000).setViewPlaneSize(200, 200);
	
// Camera from +z, -x with angle axis:
		private final Camera camera = new Camera(new Point3D(-200, 0, 200), new Vector(1, 0, -1), new Vector(1, 0, 1)) //
				.setDistance(1000).setViewPlaneSize(200, 200);
	
	private final Scene scene = new Scene("Test scene");

	private static final Color color = new Color(200, 0, 0); // Red
	private static final Color colorGold = new Color(255, 215, 0); // Gold
	private static final Color colorSilver = new Color(167, 167, 167); // Gold1
	private static final Color colorTable = new Color(135, 206, 235); // ceeb
	private static final Material mat = new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkR(0.5);
	private static final Material matTable = new Material().setKd(0.5).setKs(0.5).setnShininess(50).setkR(0.1);
	private static final Color colorWine = new Color(14, 47, 55); // wine: 14, 47, 55
	//private static final Color colorWine = new Color(114, 47, 55); // purple
	private static final Material matWine = new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0.2); // Transperant
	private static final double radius = 0.2;

	/**
	 * Produce a scene with a 3D model and render it into a png image
	 */
	@Test
	public void goblet() {
		double angle = 0.261; // 15 degree
		int totalRotations = 26; // 15 degree * 24 = 360 and two to close the gap
		Vector toMoveVector;
		double oldX = -7;
		double oldY = 0;
		double newX;
		double newY;
		int gobletMaxLevel = 10; // Make it even for the level of wine
		int headDepthLevel = -7;
		int legDepthLevel = -12;
		int bottomDepthLevel = -16;

		Point3D a;
		Point3D b;
		Point3D[] pntsLevel1 = new Point3D[totalRotations];
		Point3D[] pntsLevel2 = new Point3D[totalRotations];
		Point3D[] pntsWineLevel = new Point3D[totalRotations - 1];
		/////////////// head of the goblet /////////////////////
		for (int j = 0; j < gobletMaxLevel; j++) // Depth level
		{
			oldX = oldX - j * 0.2;
			oldY = 0;
			// Obtaining the points:
			for (int i = 0; i < totalRotations; i++) { // Number of angle rotations

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
				
				if (j == gobletMaxLevel - 3 && i < totalRotations - 1)
					pntsWineLevel[i] = a;
		
					

				// Updating to the newest point on the circle:
				oldX = newX;
				oldY = newY;
			}

			// Creating the triangles:
			if (j > 0) {
				for (int i = 0; i < totalRotations - 1; i++) {
					if (j % 2 == 0) {
						scene.geometries.add(
								new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat),
								new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat));
					} else {
						scene.geometries.add(
								new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat),
								new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat));
					}

				}
			}
			



		}
		
		// Filling the goblet is wine:
		scene.geometries.add(
				new Polygon(pntsWineLevel).setEmission(colorWine)
				.setMaterial(matWine));
		
		/////////////// belly of the goblet /////////////////////

		for (int j = 0; j > headDepthLevel; j--) // Depth level
		{
			oldX = headDepthLevel - j;
			oldY = 0;
//			if (j % 2 != 0) { // The triangles will be seen
//				oldY = Math.sin(0.5 * angle) * oldX;
//				oldX = Math.cos(0.5 * angle) * oldX - Math.sin(0.5 * angle) * oldY;
//			}
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
					if (j % 2 == 0) {
						scene.geometries.add(
								new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat),
								new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat));
					} else {
						scene.geometries.add(
								new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat),
								new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(colorSilver)
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
		for (int i = 0; i < totalRotations; i++) { // Number of angle rotations
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
			scene.geometries.add(new Polygon(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1], pntsLevel2[i])
					.setEmission(colorSilver).setMaterial(mat));
		}

		/////////////// Bottom of the goblet /////////////////////

		angle = 0.261; // 15 degree
		totalRotations = 26; // 15 degree * 24 = 360 and two to close the gap
		pntsLevel1 = new Point3D[totalRotations];
		pntsLevel2 = new Point3D[totalRotations];
		Point3D[] pntsBottomLevel = new Point3D[totalRotations - 1];

		for (int j = legDepthLevel; j >= bottomDepthLevel; j--) // Depth level
		{
			oldX = j + 11;
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
				
				if (j == bottomDepthLevel && i < totalRotations - 1)
					pntsBottomLevel[i] = a;

				// Updating to the newest point on the circle:
				oldX = newX;
				oldY = newY;
			}

			// Creating the triangles:
			if (j < legDepthLevel) {
				for (int i = 0; i < totalRotations - 1; i++) {
					if (j % 2 == 0) {
						scene.geometries.add(
								new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat),
								new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat));
					} else {
						scene.geometries.add(
								new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat),
								new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(colorSilver)
										.setMaterial(mat));
					}

				}
				if (j == bottomDepthLevel) // Closing the leg bottom
					scene.geometries.add(new Polygon(pntsBottomLevel).setEmission(color)
							.setMaterial(mat));
					
			}
		}

		/////////////// Table /////////////////////
		// Decorating circles:
		angle = 0.261; // 15 degree
		totalRotations = 24; // 15 degree * 24 = 360
		oldX = -7;
		oldY = 0;
		double radius = 0.2;
		Point3D center = new Point3D(0, 0, bottomDepthLevel);
		for (int j = 0; j < 5; j++) // Number of circles
		{
			for (double i = 0; i < totalRotations; i++) { // Number of angle rotations
				newX = Math.cos(angle) * oldX - Math.sin(angle) * oldY;
				newY = Math.sin(angle) * oldX + Math.cos(angle) * oldY;
				toMoveVector = new Vector(newX, newY, 0);
				scene.geometries.add(new Sphere(center.add(toMoveVector), radius).setEmission(color).setMaterial(mat));
				oldX = newX;
				oldY = newY;
			}
			oldX = oldX - 2;
		}
		
		
		double tableThickness = 2;
		double tableSize = 20; // Half size - the number is the x,y of the coordinates
		scene.geometries.add(
				// Up:
				new Polygon(new Point3D(-tableSize, -tableSize, bottomDepthLevel), new Point3D(tableSize, -tableSize, bottomDepthLevel),
						new Point3D(tableSize, tableSize, bottomDepthLevel), new Point3D(-tableSize, tableSize, bottomDepthLevel))
								.setEmission(colorTable).setMaterial(matTable),
				// Down:
				new Polygon(new Point3D(-tableSize, -tableSize, bottomDepthLevel - tableThickness), new Point3D(tableSize, -tableSize, bottomDepthLevel - tableThickness),
						new Point3D(tableSize, tableSize, bottomDepthLevel - tableThickness), new Point3D(-tableSize, tableSize, bottomDepthLevel - tableThickness))
								.setEmission(colorTable).setMaterial(matTable),
				// -y side:
				new Polygon(new Point3D(-tableSize, -tableSize, bottomDepthLevel), new Point3D(tableSize, -tableSize, bottomDepthLevel),
						new Point3D(tableSize, -tableSize, bottomDepthLevel - tableThickness), new Point3D(-tableSize, -tableSize, bottomDepthLevel - tableThickness))
								.setEmission(colorTable).setMaterial(matTable),
				// +y side:
				new Polygon(new Point3D(tableSize, tableSize, bottomDepthLevel), new Point3D(-tableSize, tableSize, bottomDepthLevel),
						new Point3D(-tableSize, tableSize, bottomDepthLevel - tableThickness), new Point3D(tableSize, tableSize, bottomDepthLevel - tableThickness))
								.setEmission(colorTable).setMaterial(matTable),
				// -x side
				new Polygon(new Point3D(-tableSize, -tableSize, bottomDepthLevel), new Point3D(-tableSize, tableSize, bottomDepthLevel),
						new Point3D(-tableSize, tableSize, bottomDepthLevel - tableThickness), new Point3D(-tableSize, -tableSize, bottomDepthLevel - tableThickness))
								.setEmission(colorTable).setMaterial(matTable),
				// +x side
				new Polygon(new Point3D(tableSize, -tableSize, bottomDepthLevel), new Point3D(tableSize, tableSize, bottomDepthLevel),
						new Point3D(tableSize, tableSize, bottomDepthLevel - tableThickness), new Point3D(tableSize, -tableSize, bottomDepthLevel - tableThickness))
								.setEmission(colorTable).setMaterial(matTable));

		// Lights and rendering
		scene.lights.add(new PointLight(new Color(250, 500, 500), new Point3D(100, 100, 100), 1, 0.0005, 0.0005) //
				.setkQ(0.000001));

		ImageWriter imageWriter = new ImageWriter("TheGoblet", 800, 800);
		Render render = new Render() //
				.setCamera(camera) //
				.setImageWriter(imageWriter) //
				.setRayTracerBasic(new RayTracerBasic(scene)) //
				.setMultithreading(3).setDebugPrint();
		render.renderImage();
		//render.printGrid(50, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}

}