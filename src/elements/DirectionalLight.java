/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author 97253
 *	Class for the directional light
 */
public class DirectionalLight extends Light implements LightSource{

	private Vector direction;
	
	/**
	 * 
	 * @param intensity
	 * @param dir
	 */
	public DirectionalLight(Color intensity, Vector dir) {
		super(intensity);
		direction =dir;
		}

	@Override
	public Color getIntensity(Point3D p) {

		return getIntensity();
	}

	@Override
	public Vector getL(Point3D p) {
		return direction.normalized();
	}

	@Override
	public double getDistance(Point3D point) {
		return Double.POSITIVE_INFINITY;
	}

}
