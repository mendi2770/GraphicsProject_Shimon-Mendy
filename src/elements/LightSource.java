/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author 97253
 * interface for light source
 */
public interface LightSource {

	/**
	 * Calculate the intensity of each light
	 * @param p
	 * @return The intensity
	 */
	public Color getIntensity(Point3D p);
	
	/**
	 * 
	 * @param p
	 * @return The Vector between point p to point position
	 */
	public Vector getL(Point3D p);

}
