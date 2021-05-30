package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

/**
 * @author 97253
 *Test classs for Anti-aliasing improvment
 */

public class AntiAliasingTests {	
	/**
	 * Produce a picture of a pyramid lighted
	 * using Anti-aliasing improvment
	 */ 
	private Scene scene = new Scene("Test scene");
	@Test
	public void test() {
		Camera camera = new Camera(new Point3D(-140, 20, 35), new Vector(1, -0.15, -0.25), new Vector(1, 0, 4))//
				.setViewPlaneSize(200, 200).setDistance(1000).setAmountOfSampledRays(0);

		scene.setAmbientLight(new Color(java.awt.Color.WHITE), 0.15);

		scene.geometries.add( //				
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
							.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60).setkT(0.5)),
				new Triangle(new Point3D(10, 0, 0), new Point3D(0, -10, 0), new Point3D(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(60).setkT(0.5).setkR(0)), //
				new Triangle(new Point3D(10, 0, 0), new Point3D(0, 10, 0), new Point3D(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(60).setkT(1)), //
				new Triangle(new Point3D(-10, 0, 0), new Point3D(0, 10, 0), new Point3D(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(60).setkT(0.8)), //
				new Triangle(new Point3D(-10, 0, 0), new Point3D(0, -10, 0), new Point3D(0, 0, 10)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(60).setkT(1)), //
				new Sphere(new Point3D(0, 0, 3), 2) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setkT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(30, 30, 100), new Vector(0, 0, -1), 1, 0, 0) //
				.setkL(4E-5).setkQ(2E-7));
		
		scene.lights.add(new PointLight(new Color(500, 250, 250), new Point3D(60, 60, 200), 1, 0.0005, 0.0005));
		
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(120, 120, 300), new Vector(0, 0, -1), 1, 0, 0) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("antiAliasingPyramid", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBasic(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

}
