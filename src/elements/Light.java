/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author 97253
 * Abstract class for light
 */
abstract class Light {

	private Color intensity;

	/**
	 * Protected constructor for intensity
	 * @param intensity
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * @return The intensity of the light
	 */
	public Color getIntensity() {
		return intensity;
	}

}
