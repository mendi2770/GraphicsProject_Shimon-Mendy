package primitives;

import static primitives.Util.isZero;

public class Point3D {

	final static Point3D ZERO = new Point3D(0, 0, 0);

	private Coordinate x;
	private Coordinate y;
	private Coordinate z;
	
	

	/**
	 * @return the x
	 */
	public Double getX() {
		return x.coord;
	}

	/**
	 * @return the y
	 */
	public Double getY() {
		return y.coord;
	}
	

	/**
	 * @return the z
	 */
	public Double getZ() {
		return z.coord;
	}


	public Point3D(Coordinate p1, Coordinate p2, Coordinate p3) {
		x = p1;
		y = p2;
		z = p3;
	}

	public Point3D(double p1, double p2, double p3) {
		x = new Coordinate(p1);
		y = new Coordinate(p2);
		z = new Coordinate(p3);
	}

	public Vector subtract(Point3D p) {
		return new Vector(p.x.coord - this.x.coord, p.y.coord - this.y.coord, p.z.coord - this.z.coord);
	}

	public Point3D add(Vector vec) {
		return new Point3D(vec.getHead().x.coord + this.x.coord, vec.getHead().y.coord + this.y.coord,
				vec.getHead().z.coord + this.z.coord);
	}

	double distanceSquared(Point3D p) {
		return this.subtract(p).lengthSquared();
//		return (vec.head.x.coord * vec.head.x.coord + vec.head.y.coord * vec.head.y.coord +
//				vec.head.z.coord * vec.head.z.coord);
	}

	double distance(Point3D p) {
		return Math.sqrt(distanceSquared(p));
	}

	/*************** Admin *****************/
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
