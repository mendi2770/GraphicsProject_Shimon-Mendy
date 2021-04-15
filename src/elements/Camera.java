package elements;

import static primitives.Util.*;

import primitives.*;

/**
 * The camera is the element which absorbs and processes the picture
 * 
 * @author shimo
 *
 */
public class Camera {

	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
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
	 * @return the height
	 */
	public double getHeigth() {
		return height;
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
	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		if (!isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("The given vectors are not vertical.");
		this.p0 = p0;
		this.vUp = vUp.normalize();
		this.vTo = vTo.normalize();
		this.vRight = vUp.crossProduct(vTo).normalize();
	}

	/**
	 * A setter for the size of the view plane
	 * 
	 * @param width
	 * @param height
	 * @return the camera itself
	 */
	public Camera setViewPlaneSize(double width, double height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Width or height cannot be negative!");
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * A setter for the distance of the camera from the view plane
	 * 
	 * @param distance
	 * @return the camera itself
	 */
	public Camera setDistance(double distance) {
		if (distance <= 0)
			throw new IllegalArgumentException("Distance cannot be negative!");
		this.distance = distance;
		return this;
	}

	/**
	 * Calculates the ray that goes through the middle of a pixel i,j on the view
	 * plane
	 * 
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @return The ray that goes through the middle of a pixel i,j on the view plane
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		// Image center:
		Point3D pCenter = this.p0.add(vTo.scale(this.distance));

		// Ratio:
		double Ry = this.height / nY;
		double Rx = this.width / nX;

		if (nX % 2 == 0 && nY % 2 == 0) {
			pCenter = new Point3D(pCenter.getX() - Rx / 2,pCenter.getY() - Ry / 2, pCenter.getZ());
		}
		// Pixel[i,j] center
		double yi = alignZero(-(i - (nY - 1) / 2) * Ry);
		double xj = alignZero((j - (nX - 1) / 2) * Rx);
		Point3D pIJ = pCenter;
		if (xj != 0)
			pIJ = pIJ.add(vRight.scale(xj));
		if (yi != 0)
			pIJ = pIJ.add(vUp.scale(yi));
		Vector vIJ = pIJ.subtract(this.p0);
		return new Ray(vIJ, p0);
	}

}