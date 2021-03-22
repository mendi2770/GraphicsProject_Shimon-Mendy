package primitives;

import static primitives.Util.isZero;

public class Point3D {

	/**
	 * A Zero point3D
	 */
	final static public Point3D ZERO = new Point3D(0, 0, 0);

	final Coordinate x;
	final Coordinate y;
	final Coordinate z;


	/**
	 * @return the x
	 */
	public double getX() {
		return x.coord;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y.coord;
	}

	/**
	 * @return the z
	 */
	public double getZ() {
		return z.coord;
	}

	/**
	 * Constructor of Point3D by three numbers
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Point3D(double p1, double p2, double p3) {
		x = new Coordinate(p1);
		y = new Coordinate(p2);
		z = new Coordinate(p3);
	}

	/**
	 * Creates a vector by subtracting a given point point from the current one
	 * @param p
	 * @return a vector
	 */
	public Vector subtract(Point3D p) {
		return new Vector(this.x.coord - p.x.coord, this.y.coord - p.y.coord, this.z.coord - p.z.coord);
	}

	/**
	 * Adds a vector to the current point 
	 * @param vec
	 * @return the point created by the addition
	 */
	public Point3D add(Vector vec) {
		Point3D head = vec.getHead();
		return new Point3D(head.x.coord + this.x.coord, head.y.coord + this.y.coord, head.z.coord + this.z.coord);
	}

	/**
	 * Calculates the squared distance between two points
	 * @param p
	 * @return the squared distance between two points
	 */
	public double distanceSquared(Point3D p){
		return (this.x.coord - p.x.coord)*(this.x.coord - p.x.coord) 
				+ (this.y.coord - p.y.coord)*(this.y.coord - p.y.coord) 
				+ (this.z.coord - p.z.coord)*(this.z.coord - p.z.coord);
	}

	/**
	 * Calculates the distance between two points
	 * @param p
	 * @return the root of the squared distance
	 */
	public double distance(Point3D p) {
		return Math.sqrt(distanceSquared(p)); // Calculates the square root of the distance 
	}

	/*************** Admin ******************/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return other.x.equals(this.x) && other.y.equals(this.y)
				&& other.z.equals(this.z);
	}

	@Override
	public String toString() {
		return "(" + x + " , " + y + " , " + z + ")";
	}

}
