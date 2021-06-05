/**
 * 
 */
package geometries;

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
	
	
}
