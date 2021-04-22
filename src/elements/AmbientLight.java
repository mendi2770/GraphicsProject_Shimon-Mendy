package elements;

import primitives.*;

public class AmbientLight {

	private Color intensity;

	/**
	 * 
	 * @param Ia 
	 * @param Ka 
	 *  constructor build the ambient light intensity
	 */
	public AmbientLight(Color iA, double kA) {

		this.intensity = iA.scale(kA);
	}
	
	/**
	 * 
	 * @return The ambient light intensity
	 */
	public Color getIntensity() {

		return intensity;
	}

}
