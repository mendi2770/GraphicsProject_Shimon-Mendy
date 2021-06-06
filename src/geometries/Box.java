/**
 * 
 */
package geometries;

import primitives.Ray;

/**
 * @author 97253
 * Box class for every shape
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
	
	public boolean IsRayHitBox(Ray ray)
	{
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
		if (rDx == 0 && rOx < minX || rOx > maxX)
			return false;
		if (rDy == 0 && rOy < minY || rOy > maxY)
			return false;
		if (rDz == 0 && rOz < minZ || rOz > maxZ)
			return false;
		
		
		// Intersection distance t1 and t2 for each dimension:
		double temp;
		double t1, t2;
		double t1x = (minX - rOx) / rDx;
		double t2x = (maxX - rOx) / rDx;
		if (t1x > t2x)
		{
			temp = t1x;
			t1x = t2x;
			t2x = temp;
		}
			
		
		double t1y = (minY - rOy) / rDy;
		double t2y = (maxY - rOy) / rDy;
		if (t1y > t2y)
		{
			temp = t1y;
			t1y = t2y;
			t2y = temp;
		}
		
		double t1z = (minZ - rOz) / rDz;
		double t2z = (maxZ - rOz) / rDz;
		if (t1z > t2z)
		{
			temp = t1z;
			t1z = t2z;
			t2z = temp;
		}

		
		
		return false;
	}
	
	
}
