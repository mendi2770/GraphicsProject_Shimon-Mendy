/**
 * 
 */
package scene;


import primitives.*;
import elements.*;
import geometries.*;
import static primitives.Color.BLACK;

import java.awt.List;
/**
 * @author 97253
 * scene class
 */
public class Scene {

	public String name= "";
	public Color background= new Color(BLACK);
	public AmbientLight ambientLight = new AmbientLight(BLACK,0);
	public Geometries geometries = null;
	
	/**
	 * @param name
	 */
	public Scene(String name) {
		this.name = name;
		geometries = new Geometries();
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param background the background to set
	 */
	public void setBackground(Color background) {
		this.background = background;
	}
	
	/**
	 * @param ambientLight the ambientLight to set
	 */
	public void setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
	}
	
	/**
	 * @param geometries the geometries to set
	 */
	public void setGeometries(Geometries geometries) {
		this.geometries = geometries;
	}


	
	

}
