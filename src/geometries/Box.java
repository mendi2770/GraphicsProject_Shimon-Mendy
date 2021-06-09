/**
 * 
 */
package geometries;

import primitives.Ray;

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
	 * 
	 */
	public Box() {
		// TODO Auto-generated constructor stub
	}

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
		// Ray coordinates:
		double rDx = ray.getDir().getHead().getX();
		double rDy = ray.getDir().getHead().getY();
		double rDz = ray.getDir().getHead().getZ();

		// Ray head point coordinates:
		double rOx = ray.getP0().getX();
		double rOy = ray.getP0().getY();
		double rOz = ray.getP0().getZ();

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
		double t1 = 0, t2 = 0, tStart = 0, tEnd = 0;
		for (int i = 0; i < 3; i++) {
			if (i == 0) { 				// check dimension x
				t1 = (minX - rOx) / rDx;
				t2 = (maxX - rOx) / rDx;
			}
			if (i == 1) { 				// check dimension y
				t1 = (minY - rOy) / rDy;
				t2 = (maxY - rOy) / rDy;
			}
			if (i == 2) { 				// check dimension z
				t1 = (minZ - rOz) / rDz;
				t2 = (maxZ - rOz) / rDz;
			}
			if (t1 > t2) {
				temp = t1;
				t1 = t2;
				t2 = temp;
			}
			tStart = t1;
			tEnd = t2;
			if (t1 > tStart)
				tStart = t1;

			if (t2 < tEnd)
				tEnd = t2;
		}
		if (tStart > tEnd) 	// Box is missed
			return false;
		else if (tEnd < 0)		// Box is behind
			return true;
		else				// closest intersection at tStart or tEnd
			return true;

	}

}
