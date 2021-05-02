/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author 97253
 *
 */
abstract class Light {

	private Color intensity;

	/**
	 * 
	 * @param intesns
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
