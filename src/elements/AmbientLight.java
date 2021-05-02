package elements;

import primitives.*;

public class AmbientLight extends Light {

	/**
	 * 
	 * @param Ia 
	 * @param Ka 
	 *  constructor build the ambient light intensity using super class "Light"
	 */
	public AmbientLight(Color iA, double kA) {
		super(iA.scale(kA));
	}
}
