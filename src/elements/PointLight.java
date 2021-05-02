/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author 97253
 *
 */
public class PointLight extends Light implements LightSource {

	private Point3D position;
	private double kC = 1, kL, kQ;

	/**
	 * 
	 * @param intesns
	 * @param c
	 * @param l
	 * @param q
	 */
	public PointLight(Color intensity, double c, double l, double q) {
		super(intensity);
		kC = c;
		kL = l;
		kQ = q;
	}

	/**
	 * 
	 * @param intesns
	 */
	public PointLight(Color intensity) {
		super(intensity);
	}

	@Override
	public Color getIntensity(Point3D p) {
		double d = position.distance(p);
		Color iL = getIntensity().scale((1 / (kC + kL * d + kQ * d * d ))) ;
		return iL;
	}

	@Override
	public Vector getL(Point3D p) {

		return p.subtract(position);
	}

}
