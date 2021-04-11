package elements;
import static primitives.Util.*;
import primitives.*;
public class Camera {
	
	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double heigth;
	private double distance;

	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}


	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}


	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}


	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}


	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}


	/**
	 * @return the heigth
	 */
	public double getHeigth() {
		return heigth;
	}


	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}


	/**
	 * @param p0
	 * @param vUp
	 * @param vTo
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo) {
		if (!isZero(vUp.dotProduct(vTo)))
			throw  new IllegalArgumentException("The given vectors are not vertical.");
		this.p0 = p0;
		this.vUp = vUp.normalize();
		this.vTo = vTo.normalize();
		this.vRight = vUp.crossProduct(vTo).normalize();
	}
		



}
