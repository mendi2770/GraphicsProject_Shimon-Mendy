package primitives;

import static primitives.Util.isZero;

public class Point3D {

	/**
	 * A Zero point3D
	 */
	final static public Point3D ZERO = new Point3D(0, 0, 0);

	private Coordinate x;
	private Coordinate y;
	private Coordinate z;

	/**
	 * @return X coordinate
	 */
	public Double getX() {
		return x.coord;
	}

	/**
	 * @return Y coordinate
	 */
	public Double getY() {
		return y.coord;
	}

	/**
	 * @return Z coordinate
	 */
	public Double getZ() {
		return z.coord;
	}

	/**
	 * Constructor of Point3D by three coordinates
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Point3D(Coordinate p1, Coordinate p2, Coordinate p3) {
		x = p1;
		y = p2;
		z = p3;
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
		return new Point3D(vec.getHead().x.coord + this.x.coord, vec.getHead().y.coord + this.y.coord, vec.getHead().z.coord + this.z.coord);
	}

	/**
	 * Calculates the squared distance between two points
	 * @param p
	 * @return the squared distance between two points
	 */
	double distanceSquared(Point3D p){
		return (this.x.coord - p.x.coord)*(this.x.coord - p.x.coord) 
				+ (this.y.coord - p.y.coord)*(this.y.coord - p.y.coord) 
				+ (this.z.coord - p.z.coord)*(this.z.coord - p.z.coord);
	}

	/**
	 * Calculates the distance between two points
	 * @param p
	 * @return the root of the squared distance
	 */
	double distance(Point3D p) {
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
		return isZero(other.x.coord - this.x.coord) && isZero(other.y.coord - this.y.coord)
				&& isZero(other.z.coord - this.z.coord);
	}

	@Override
	public String toString() {
		return "(" + x + " , " + y + " , " + z + ")";
	}

}
