package primitives;

import static primitives.Util.isZero;

public class Vector {

	Point3D head;

	public Vector(Coordinate p1, Coordinate p2, Coordinate p3) {
		Point3D p = new Point3D(p1, p2, p3);
		if (p.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The coordiates cannot be  zeroes.");
		head = p;
	}

	public Vector(double p1, double p2, double p3) {
		Point3D p = new Point3D(p1, p2, p3);
		if (p.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The numbers cannot be zeroes.");
		head = p;
	}

	public Vector(Point3D p) {
		if (p.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The numbers cannot be zeroes.");
		head = p;
	}

	public Vector subtract(Vector vec) {
		return new Vector(this.head.x.coord - vec.head.x.coord, this.head.y.coord - vec.head.y.coord, this.head.z.coord - vec.head.z.coord);
		
	}

	public Vector add(Vector vec) {
		return new Vector(this.head.x.coord + vec.head.x.coord, this.head.y.coord + vec.head.y.coord, this.head.z.coord + vec.head.z.coord);
	
	}
	
	public Vector scale(double scale) {
		return new Vector(scale * this.head.x.coord, scale * this.head.y.coord, scale * this.head.z.coord);

	}
	
	public double dotProduct(Vector vec) {
		return (this.head.x.coord * vec.head.x.coord + this.head.y.coord * vec.head.y.coord + this.head.z.coord * vec.head.z.coord);
	}

	public Vector crossProduct(Vector vec) {
		return new Vector(this.head.y.coord * vec.head.z.coord - this.head.z.coord * vec.head.y.coord,
								this.head.z.coord * vec.head.x.coord - this.head.x.coord * vec.head.z.coord,
								this.head.x.coord * vec.head.y.coord - this.head.y.coord * vec.head.x.coord);

	}
	
	public double lengthSquared() {
		return this.head.x.coord * this.head.x.coord + this.head.y.coord * this.head.y.coord +
				this.head.z.coord * this.head.z.coord;
	}
	
	public double length() {
		return Math.sqrt(lengthSquared());
	}
	
	public Vector normalize() {
		this.head = this.scale(1 / this.length()).head;
		return this;
	} 
	
	public Vector normalized() {
		Vector v = new Vector(this.head);
		return v.normalize();
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
