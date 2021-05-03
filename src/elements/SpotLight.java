/**
 * 
 */
package elements;

import static primitives.Util.*;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author 97253
 *
 */
public class SpotLight extends PointLight {

	private Vector direction;

	/**
	 * 
	 * @param intensity
	 * @param position
	 * @param dir
	 * @param c
	 * @param l
	 * @param q
	 */
	public SpotLight(Color intensity, Point3D position, Vector dir, double c, double l, double q) {
		super(intensity, position, c, l, q);
		direction = dir.normalized();
	}

	@Override
	public Color getIntensity(Point3D p) {

		Vector l = super.getL(p);

		if (alignZero(direction.dotProduct(l)) <= 0)
			return Color.BLACK;

		return super.getIntensity(p).scale(direction.dotProduct(l));
	}

	@Override
	public Vector getL(Point3D p) {
		return super.getL(p);
	}

}
