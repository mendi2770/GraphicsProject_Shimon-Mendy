/**
 * 
 */
package scene;

import primitives.*;
import java.util.List;
import java.util.LinkedList;

import elements.*;
import geometries.*;


/**
 * @author 97253
 * Class that create the scene
 */
public class Scene {

	public String name = null;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 0);
	public Geometries geometries;
	public List<LightSource> lights = new LinkedList<LightSource>();


	/**
	 * @param name
	 * Ctor sets up the name and creates an empty collection of geometries
	 */
	public Scene(String name) {
		this.name = name;
		geometries = new Geometries();
	}

	/**
	 * @param background
	 * @return The scene object (this)
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * 
	 * @param color
	 * @param Ka
	 * @return The scene object (this)
	 */
	public Scene setAmbientLight(Color color,double Ka) {
		this.ambientLight = new AmbientLight(color,Ka);
		return this;
	}

	/**
	 * 
	 * @param geo
	 * @return The scene object (this)
	 */
	public Scene setGeometries(Geometries geo) {
		this.geometries = geo;
		return this;
	}

	/**
	 * @param lights the lights to set
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}
}
