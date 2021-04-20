package elements;

import primitives.*;

public class AmbientLight {

	private Color intensity;

	/**
	 * 
	 * @param Ia 
	 * @param Ka 
	 *  ctor build the ambient light intensity
	 */
	public AmbientLight(Color Ia, double Ka) {

		this.intensity = Ia.scale(Ka);
	}

	public Color getIntensity() {

		return intensity;
	}

}
