/**
 * 
 */
package scene;

import primitives.*;
import elements.*;
import geometries.*;


/**
 * @author 97253 scene class
 */
public class Scene {

	public String name = null;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 0);
	public Geometries geometries;

	/**
	 * @param name
	 * Ctor set up the name an create an empty collection of geometries
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
		if (this.geometries == null)
			this.geometries = new Geometries();
		geometries.add(geo);
		return this;
	}

}
