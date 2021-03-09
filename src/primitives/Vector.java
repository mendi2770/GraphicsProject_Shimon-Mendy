package primitives;

import static primitives.Util.isZero;

public class Vector {

	Point3D head;

	public Vector(Coordinate p1, Coordinate p2, Coordinate p3) {
		Point3D p = new Point3D(p1, p2, p3);
		if (p.equals(p.ZERO))
			throw new IllegalArgumentException("The coordiates cannot be  zeroes.");
		head = p;
	}

	public Vector(double p1, double p2, double p3) {
		Point3D p = new Point3D(p1, p2, p3);
		if (p.equals(p.ZERO))
			throw new IllegalArgumentException("The numbers cannot be zeroes.");
		head = p;
	}

	public Vector(Point3D p1) {
		head = p1;
	}

	public Vector subtract(Vector vec) {
		Vector temp = new Vector(this.head.x.coord - vec.head.x.coord, this.head.y.coord - vec.head.y.coord, this.head.z.coord - vec.head.z.coord);
		return temp;
	}

	public Vector add(Vector vec) {
		Vector temp = new Vector(this.head.x.coord + vec.head.x.coord, this.head.y.coord + vec.head.y.coord, this.head.z.coord + vec.head.z.coord);		Vector temp = new Vector(vec.head.x.coord + this.head.x.coord, vec.head.y.coord + this.head.y.coord, vec.head.z.coord + this.head.z.coord);
		return temp;
	}
	
	public Vector scale(double scale) {
		Vector temp = new Vector(scale * this.head.x.coord, scale * this.head.y.coord, scale * this.head.z.coord);
		return temp;
	}
	
	public double dotProduct(Vector vec) {
		return (this.head.x.coord * vec.head.x.coord + this.head.y.coord * vec.head.y.coord + this.head.z.coord * vec.head.z.coord);
	}

	public Vector crossProduct(Vector vec) {
		Vector temp = new Vector(this.head.y.coord * vec.head.z.coord - this.head.z.coord * vec.head.y.coord,
								this.head.z.coord * vec.head.x.coord - this.head.x.coord * vec.head.z.coord,
								this.head.x.coord * vec.head.y.coord - this.head.y.coord * vec.head.x.coord);				Vector temp = new Vector(vec.head.x.coord + this.head.x.coord, vec.head.y.coord + this.head.y.coord, vec.head.z.coord + this.head.z.coord);
		return temp;
	}
	
	/*************** Admin *****************/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return other.head.equals(this.head);
	}

	@Override
	public String toString() {
		return this.head.toString();
	}
}
