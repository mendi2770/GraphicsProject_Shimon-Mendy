package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.lang.Math;

/**
 * Test rendering an images
 * 
 * @author Dan
 */
public class ShabbatShalom {

	



	
	
	// Camera from up:
//	private final Camera camera = new Camera(new Point3D(0, 0, 500), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//			.setDistance(1000).setViewPlaneSize(200, 200);
	

	// Camera from x asix:
	//private final Camera camera = new Camera(new Point3D(200, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
//			.setDistance(1000).setViewPlaneSize(200, 200);

	// Camera from y asix:
//	private final Camera camera = new Camera(new Point3D(0, 500, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)) //
//			.setDistance(1000).setViewPlaneSize(200, 200);
//	
	//Camera from -x axis:
//	private final Camera camera = new Camera(new Point3D(-200, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
//			.setDistance(1000).setViewPlaneSize(200, 200);

	 //Camera from +z, -x with angle axis:
//		private Camera camera = new Camera(new Point3D(-250, 0, 92), new Vector(1, 0, -0.35),
//				new Vector(1, 0, 1 / 0.35)) //
//						.setDistance(1000).setViewPlaneSize(200, 200).setAmountOfSampledRays(100);

	private final Scene scene = new Scene("Test scene");

	private static final Color color = new Color(200, 0, 0); // Red
	private static final Color colorGold = new Color(255, 215, 0); // Gold
	private static final Color colorSilver = new Color(167, 167, 167); // Gold1
	private static final Color colorTable = new Color(139, 69, 19); // Saddle brown
	private static final Color colorFire = new Color(255, 69, 0);// orangeRed
	private static final Material matFire = new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0.8);
	private static final Color colorCandle = new Color(135, 206, 235);// ceeb
	private static final Material matCandle = new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkR(0.2);
	private static final Material mat = new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkR(0.5);
	private static final Material matTable = new Material().setKd(0.5).setKs(0.5).setnShininess(50).setkR(0.1);
	private static final Material matMirror = new Material().setKd(0.5).setKs(0.5).setnShininess(10).setkR(1).setkT(0);
	// private static final Color colorWine = new Color(14, 47, 55); // wine: 14,
	// 47, 55
	private static final Color colorWine = new Color(114, 47, 55); // purple
	private static final Material matWine = new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0.3); // Transperant
	private static final double radius = 0.2;

	/**
	 * Produce a scene with a 3D model and render it into a png image
	 */
	@Test
	public void goblet() {
		// Camera GIF project:
//		String title = "1";
//		double x = -27.12;
//		double y = 248.52;
//		double xVector = 0.101;
//		double yVector = -0.93;

		
		
		
		
		double angle = 0.261; // 15 degree
		int totalRotations = 26; // 15 degree * 24 = 360 and two to close the gap
		Vector toMoveVector;
		double oldX = -7;
		double oldY = 0;
		double newX;
		double newY;
		int gobletMaxLevel = 10; // Make it even for the level of wine

		Point3D a;
		Point3D b;
		Point3D[] pntsLevel1 = new Point3D[totalRotations];
		Point3D[] pntsLevel2 = new Point3D[totalRotations];
		Point3D[] pntsWineLevel = new Point3D[totalRotations - 1];
		/////////////// head of the goblet ///////////////////// - 450 triangles and
		/////////////// polygon
		Geometries gobletPolygons = new Geometries();
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
					gobletPolygons.add(
							new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(colorSilver)
									.setMaterial(mat),
							new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(colorSilver)
									.setMaterial(mat));
				}
			}

		}
		// Filling the goblet is wine:
		gobletPolygons.add(new Polygon(pntsWineLevel).setEmission(colorWine).setMaterial(matWine));
		scene.geometries.add(gobletPolygons);

		/////////////// belly of the goblet ///////////////////// - 300 triangles
		int headDepthLevel = -7;
		gobletPolygons = new Geometries();
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
					gobletPolygons.add(
							new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(colorSilver)
									.setMaterial(mat),
							new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(colorSilver)
									.setMaterial(mat));
				}
			}
		}

		scene.geometries.add(gobletPolygons);

		/////////////// Leg of the goblet ///////////////////// 121 Polygons
		int legDepthLevel = -12;
		gobletPolygons = new Geometries();
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
			gobletPolygons.add(new Polygon(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1], pntsLevel2[i])
					.setEmission(colorSilver).setMaterial(mat));
		}

		scene.geometries.add(gobletPolygons);

		/////////////// Bottom of the goblet ///////////////////// - 201 triangles
		int bottomDepthLevel = -16;
		gobletPolygons = new Geometries();
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
					gobletPolygons.add(
							new Triangle(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1]).setEmission(colorSilver)
									.setMaterial(mat),
							new Triangle(pntsLevel1[i], pntsLevel2[i], pntsLevel2[i + 1]).setEmission(colorSilver)
									.setMaterial(mat));

				}
				if (j == bottomDepthLevel) // Closing the leg bottom
					gobletPolygons.add(new Polygon(pntsBottomLevel).setEmission(color).setMaterial(mat));

			}
		}
		scene.geometries.add(gobletPolygons);

		/////////////// Table ///////////////////// - 7 polygons

		// Decorating circles:
		angle = 0.261; // 15 degree
		totalRotations = 24; // 15 degree * 24 = 360
		oldX = -7;
		oldY = 0;
		double radius = 0.2;
		Point3D center = new Point3D(0, 0, bottomDepthLevel), newPoint;
		Geometries decorations = new Geometries();
		for (int j = 0; j < 6; j++) // Number of circles
		{
			for (double i = 0; i < totalRotations; i++) { // Number of angle rotations
				newX = Math.cos(angle) * oldX - Math.sin(angle) * oldY;
				newY = Math.sin(angle) * oldX + Math.cos(angle) * oldY;
				toMoveVector = new Vector(newX, newY, 0);
				newPoint = center.add(toMoveVector);
				decorations.add(new Sphere(newPoint, radius).setEmission(colorGold).setMaterial(mat));
				oldX = newX;
				oldY = newY;
			}
			oldX = oldX - 2;
		}
		scene.geometries.add(decorations); // For the BHV

		double tableThickness = 2;
		double tableSize = 50; // Half size - the number is the x,y of the coordinates
		scene.geometries.add(new Geometries(
				// Up:
				new Polygon(new Point3D(-tableSize, -tableSize, bottomDepthLevel),
						new Point3D(tableSize, -tableSize, bottomDepthLevel),
						new Point3D(tableSize, tableSize, bottomDepthLevel),
						new Point3D(-tableSize, tableSize, bottomDepthLevel)).setEmission(colorTable)
								.setMaterial(matTable),
				// Down:
				new Polygon(new Point3D(-tableSize, -tableSize, bottomDepthLevel - tableThickness),
						new Point3D(tableSize, -tableSize, bottomDepthLevel - tableThickness),
						new Point3D(tableSize, tableSize, bottomDepthLevel - tableThickness),
						new Point3D(-tableSize, tableSize, bottomDepthLevel - tableThickness)).setEmission(colorTable)
								.setMaterial(matTable),
				// -y side:
				new Polygon(new Point3D(-tableSize, -tableSize, bottomDepthLevel),
						new Point3D(tableSize, -tableSize, bottomDepthLevel),
						new Point3D(tableSize, -tableSize, bottomDepthLevel - tableThickness),
						new Point3D(-tableSize, -tableSize, bottomDepthLevel - tableThickness)).setEmission(colorTable)
								.setMaterial(matTable),
				// +y side:
				new Polygon(new Point3D(tableSize, tableSize, bottomDepthLevel),
						new Point3D(-tableSize, tableSize, bottomDepthLevel),
						new Point3D(-tableSize, tableSize, bottomDepthLevel - tableThickness),
						new Point3D(tableSize, tableSize, bottomDepthLevel - tableThickness)).setEmission(colorTable)
								.setMaterial(matTable),
				// -x side
				new Polygon(new Point3D(-tableSize, -tableSize, bottomDepthLevel),
						new Point3D(-tableSize, tableSize, bottomDepthLevel),
						new Point3D(-tableSize, tableSize, bottomDepthLevel - tableThickness),
						new Point3D(-tableSize, -tableSize, bottomDepthLevel - tableThickness)).setEmission(colorTable)
								.setMaterial(matTable),
				// +x side
				new Polygon(new Point3D(tableSize, -tableSize, bottomDepthLevel),
						new Point3D(tableSize, tableSize, bottomDepthLevel),
						new Point3D(tableSize, tableSize, bottomDepthLevel - tableThickness),
						new Point3D(tableSize, -tableSize, bottomDepthLevel - tableThickness)).setEmission(colorTable)
								.setMaterial(matTable)));

		/////////////// Candles ///////////////////// - 4 spheres, 10 polygons
		Point3D candleA = new Point3D(25, -10, 8);
		Point3D candleB = new Point3D(25, 10, 8);
		scene.lights.add(new PointLight(new Color(255, 69, 0), candleA.add(new Vector(0, 0, 1)), 1, 0.01, 0.01) //
				.setkQ(0.000001));
		scene.lights.add(new PointLight(new Color(255, 69, 0), candleB.add(new Vector(0, 0, 1)), 1, 0.01, 0.01) //
				.setkQ(0.000001));
		scene.geometries.add(new Geometries(
				// Minus Y candle:

				new Sphere(candleB, 1).setEmission(colorFire).setMaterial(matFire),
				new Sphere(candleB.add(new Vector(0, 0, 1)), 2).setEmission(colorFire).setMaterial(matFire),
				new Polygon(new Point3D(27, 12, 7), new Point3D(27, 8, 7), new Point3D(23, 8, 7),
						new Point3D(23, 12, 7)).setEmission(colorCandle).setMaterial(matCandle),
				new Polygon(new Point3D(30, 15, bottomDepthLevel), new Point3D(30, 5, bottomDepthLevel),
						new Point3D(27, 8, 7), new Point3D(27, 12, 7)).setEmission(colorCandle).setMaterial(matCandle),
				new Polygon(new Point3D(20, 15, bottomDepthLevel), new Point3D(20, 5, bottomDepthLevel),
						new Point3D(23, 8, 7), new Point3D(23, 12, 7)).setEmission(colorCandle).setMaterial(matCandle),
				new Polygon(new Point3D(30, 15, bottomDepthLevel), new Point3D(20, 15, bottomDepthLevel),
						new Point3D(23, 12, 7), new Point3D(27, 12, 7)).setEmission(colorCandle).setMaterial(matCandle),
				new Polygon(new Point3D(30, 5, bottomDepthLevel), new Point3D(20, 5, bottomDepthLevel),
						new Point3D(23, 8, 7), new Point3D(27, 8, 7)).setEmission(colorCandle).setMaterial(matCandle)),

				new Geometries(
						// Plus Y candle:
						new Sphere(candleA, 1).setEmission(colorFire).setMaterial(matFire),
						new Sphere(candleA.add(new Vector(0, 0, 1)), 2).setEmission(colorFire).setMaterial(matFire),
						new Polygon(new Point3D(27, -12, 7), new Point3D(27, -8, 7), new Point3D(23, -8, 7),
								new Point3D(23, -12, 7)).setEmission(colorCandle).setMaterial(matCandle),
						new Polygon(new Point3D(30, -15, bottomDepthLevel), new Point3D(30, -5, bottomDepthLevel),
								new Point3D(27, -8, 7), new Point3D(27, -12, 7)).setEmission(colorCandle)
										.setMaterial(matCandle),
						new Polygon(new Point3D(20, -15, bottomDepthLevel), new Point3D(20, -5, bottomDepthLevel),
								new Point3D(23, -8, 7), new Point3D(23, -12, 7)).setEmission(colorCandle)
										.setMaterial(matCandle),
						new Polygon(new Point3D(30, -15, bottomDepthLevel), new Point3D(20, -15, bottomDepthLevel),
								new Point3D(23, -12, 7), new Point3D(27, -12, 7)).setEmission(colorCandle)
										.setMaterial(matCandle),
						new Polygon(new Point3D(30, -5, bottomDepthLevel), new Point3D(20, -5, bottomDepthLevel),
								new Point3D(23, -8, 7), new Point3D(27, -8, 7)).setEmission(colorCandle)
										.setMaterial(matCandle)));

		/////////////// Mirror background /////////////////////
		scene.geometries.add(new Polygon(new Point3D(tableSize, tableSize, bottomDepthLevel),
				new Point3D(tableSize, -tableSize, bottomDepthLevel), new Point3D(tableSize * 2, -tableSize, 10),
				new Point3D(tableSize * 2, tableSize, 10)).setEmission(Color.BLACK).setMaterial(matMirror));

		// Lights and rendering
		scene.lights.add(new PointLight(new Color(250, 255, 250), new Point3D(100, 100, 100), 1, 0.0005, 0.0005) //
				.setkQ(0.000001));
		scene.lights.add(new PointLight(new Color(255, 255, 0), new Point3D(-350, 0, -12), 1, 0.06, 0.06) //
				.setkQ(0.000001));
		scene.lights.add(new SpotLight(new Color(255, 69, 0), new Point3D(tableSize, tableSize, 200),
				new Vector(-1, -1, -1), 1, 0.05, 0.05));
		scene.lights.add(new SpotLight(new Color(255, 69, 0), new Point3D(tableSize, -tableSize, 200),
				new Vector(-1, 1, -1), 1, 0.05, 0.05));



		Double CameraAngle = 0.0349; // 2 degree
		int cameraRotations = 180; // 2 degree * 90 = 360
		Point3D[] cameraPoints = new Point3D[cameraRotations];
		Vector[] vectorsNormalized = new Vector[cameraRotations];
		Vector cameraV;
		double oldXcamera = -1;
		double oldYcamera = 300;
		double newXcamera;
		double newYcamera;
		Point3D centerCamera = new Point3D(0, 0, 92);
		for (int i = 0; i < cameraRotations; i++) { // Number of angle rotations
			newXcamera = Math.cos(CameraAngle) *  oldXcamera - Math.sin(CameraAngle) * oldYcamera;
			newYcamera = Math.sin(CameraAngle) *  oldXcamera + Math.cos(CameraAngle) * oldYcamera;
			cameraV = new Vector(newXcamera, newYcamera, 0);
			cameraPoints[i] = centerCamera.add(cameraV);
			vectorsNormalized[i] = new Point3D(0, 0, 0).subtract(cameraPoints[i]).normalize();
			oldXcamera = newXcamera;
			oldYcamera = newYcamera;
		}
		
		for (int i = 0; i < cameraRotations; i++)
		{
			final Camera camera = new Camera(cameraPoints[i], vectorsNormalized[i], new Vector(-1/vectorsNormalized[i].head.x.coord, 1/vectorsNormalized[i].head.y.coord,0)) //
					.setDistance(1000).setViewPlaneSize(200, 200).setAmountOfSampledRays(0);
					ImageWriter imageWriter = new ImageWriter(String.valueOf(i), 800, 800);

					Render render = new Render() //
							.setCamera(camera) //
							.setImageWriter(imageWriter) //
							.setRayTracerBasic(new RayTracerBasic(scene).turnAllBoxesOn()) //
							.setMultithreading(3).setDebugPrint();
					render.renderImage();
					// render.printGrid(50, new Color(java.awt.Color.YELLOW));s
					render.writeToImage();
		}


	}

}

//angle =  1.5707; // 15 degree
//totalRotations = 3; //  degree * 120 = 180 
//pntsLevel1 = new Point3D[totalRotations];
//pntsLevel2 = new Point3D[totalRotations];
//oldX = -tableSize;
//oldY = 0;
//// Obtaining the points:
//for (int i = 0; i < totalRotations; i++) { // Number of angle rotations
//											// Vector rotation and new point creation:
//	newX = Math.cos(angle) * oldX - Math.sin(angle) * oldY;
//	newY = Math.sin(angle) * oldX + Math.cos(angle) * oldY;
//	toMoveVector = new Vector(newX, newY, 0);
//	a = new Point3D(0, 0, 20).add(toMoveVector);
//	b = new Point3D(0, 0, bottomDepthLevel).add(toMoveVector);
//	// Adding the new point to the appropriate array:
//	pntsLevel1[i] = a;
//	pntsLevel2[i] = b;
//
//	// Updating to the newest point on the circle:
//	oldX = newX;
//	oldY = newY;
//}
//
//Geometries mirrors = new Geometries();
//// Creating the Polygons:
//for (int i = 0; i < totalRotations - 1; i++) {
//	mirrors.add(new Polygon(pntsLevel1[i], pntsLevel1[i + 1], pntsLevel2[i + 1], pntsLevel2[i])
//			.setEmission(Color.BLACK).setMaterial(matMirror));
//}
//scene.geometries.add(mirrors);

// Camera rotations:

//
//Render render = new Render(); //s
//		
//
//angle = 0.261; // 15 degree
//totalRotations = 2; // 15 degree * 24 = 360
//oldX = -250;
//oldY = 0;
//ImageWriter imageWriter;
//Vector vToVector;
//Vector vUpVector;
//center = new Point3D(0, 0, 92);
//	for (double i = 0; i < totalRotations; i++) { // Number of angle rotations
//		newX = Math.cos(angle) * oldX - Math.sin(angle) * oldY;
//		newY = Math.sin(angle) * oldX + Math.cos(angle) * oldY;
//		toMoveVector = new Vector(newX, newY, 0);
//		newPoint = center.add(toMoveVector);
//		imageWriter = new ImageWriter(String.valueOf(i), 800, 800);
//		camera.setP0(newPoint);
//		vToVector = new Point3D(0, 0, 0).subtract(newPoint);
//		camera.setvTo(vToVector);
//		vUpVector = new Vector(new Point3D(1 / vToVector.head.x.coord, -1 / vToVector.head.y.coord, 0));
//		camera.setvUp(vUpVector);
//		render.setImageWriter(imageWriter);
//		render.setCamera(camera);
//		render.setRayTracerBasic(new RayTracerBasic(scene).turnAllBoxesOn() //
//		render.setMultithreading(3).setDebugPrint();
//		render.renderImage();
//		// render.printGrid(50, new Color(java.awt.Color.YELLOW));s
//		render.writeToImage();
//		oldX = newX;
//		oldY = newY;
//	}