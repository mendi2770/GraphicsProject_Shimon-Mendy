/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author 97253 Box class for every shape
 */
public class Box {

	public double maxX;
	public double maxY;
	public double maxZ;
	public double minX;
	public double minY;
	public double minZ;

	/**
	 * @param maxX
	 * @param maxY
	 * @param maxZ
	 * @param minX
	 * @param minY
	 * @param minZ
	 */
	public Box(double maxX, double maxY, double maxZ, double minX, double minY, double minZ) {
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
	}

	public boolean IsRayHitBox(Ray ray) {
		Vector dirRay = ray.getDir();
		Point3D rayP0= ray.getP0();
		// Ray coordinates:
		double rDx = dirRay.head.x.coord;
		double rDy = dirRay.head.y.coord;
		double rDz = dirRay.head.z.coord;

		// Ray head point coordinates:
		double rOx = rayP0.x.coord;
		double rOy = rayP0.y.coord;
		double rOz = rayP0.z.coord;

		// In case the ray is parallel to one of the dimensions
		// there is no intersection with the box:
		if (rDx == 0 && (rOx < minX || rOx > maxX))
			return false;
		if (rDy == 0 && (rOy < minY || rOy > maxY))
			return false;
		if (rDz == 0 && (rOz < minZ || rOz > maxZ))
			return false;

		// Intersection distance t1 and t2 for each dimension:
		double temp;
		double t1 = 0, t2 = 0, tStart = Double.NEGATIVE_INFINITY, tEnd = Double.POSITIVE_INFINITY;
		for (int i = 0; i < 3; i++) {
			if (i == 0) { // check dimension x
				t1 = (minX - rOx) / rDx;
				t2 = (maxX - rOx) / rDx;
			}
			if (i == 1) { // check dimension y
				t1 = (minY - rOy) / rDy;
				t2 = (maxY - rOy) / rDy;
			}
			if (i == 2) { // check dimension z
				t1 = (minZ - rOz) / rDz;
				t2 = (maxZ - rOz) / rDz;
			}
			if (t1 > t2) {
				temp = t1;
				t1 = t2;
				t2 = temp;
			}
			if (t1 > tStart)
				tStart = t1;

			if (t2 < tEnd)
				tEnd = t2;
		}
		if (tStart > tEnd) // Box is missed
			return false;
		else if (tEnd < 0) // Box is behind
			return false;
		else // closest intersection at tStart or tEnd
			return true;
	}

}
