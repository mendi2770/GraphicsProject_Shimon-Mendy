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
	 */
	public Scene(String name) {
		this.name = name;
		geometries = new Geometries();
	}

	/**
	 * @param background the background to set
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * @param ambientLight the ambientLight to set
	 */
	public Scene setAmbientLight(Color color,double Ka) {
		this.ambientLight = new AmbientLight(color,Ka);
		return this;
	}

	/**
	 * @param geometries the geometries to set
	 */
	public Scene setGeometries(Geometries geo) {
		if (this.geometries == null)
			this.geometries = new Geometries();
		geometries.add(geo);
		return this;
	}

}
