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
	private double kC = 1, kL = 0, kQ = 0;

	/**
	 * 
	 * @param intensity
	 * @param position
	 * @param c
	 * @param l
	 * @param q
	 */
	public PointLight(Color intensity, Point3D position, double c, double l , double q ) {
		super(intensity);
		this.position = position;
		kC = c;
		kL = l;
		kQ = q;
	}

	@Override
	public Color getIntensity(Point3D p) {
		double d = position.distance(p);
		Color iL = getIntensity().scale((1 / (kC + kL * d + kQ * d * d)));
		return iL;
	}

	@Override
	public Vector getL(Point3D p) {

		return p.subtract(position).normalized();
	}

	/**
	 * @param kC
	 * @return This
	 */
	public PointLight setkC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * @param kL
	 * @return This
	 */
	public PointLight setkL(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * @param kQ
	 * @return This
	 */
	public PointLight setkQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public double getDistance(Point3D point) {
		return point.distance(position);
	}

	
}
