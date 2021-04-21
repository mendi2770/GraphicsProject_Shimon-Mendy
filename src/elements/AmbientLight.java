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
	public AmbientLight(Color iA, double kA) {

		this.intensity = iA.scale(kA);
	}

	public Color getIntensity() {

		return intensity;
	}

}
