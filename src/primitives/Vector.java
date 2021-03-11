package primitives;

import static primitives.Util.isZero;

public class Vector {

	private Point3D head;

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

	/**
	 * @return the head
	 */
	public Point3D getHead() {
		return head;
	}

	public Vector subtract(Vector vec) {
		return new Vector(this.head.getX() - vec.head.getX(), this.head.getY() - vec.head.getY(), this.head.getZ() - vec.head.getZ());
		
	}

	public Vector add(Vector vec) {
		return new Vector(this.head.getX() + vec.head.getX(), this.head.getY() + vec.head.getY(), this.head.getZ() + vec.head.getZ());
	
	}
	
	public Vector scale(double scale) {
		return new Vector(scale * this.head.getX(), scale *  this.head.getY(), scale * this.head.getZ());

	}
	
	public double dotProduct(Vector vec) {
		return (this.head.getX() * this.head.getX() + this.head.getY() * this.head.getY() + this.head.getZ() * this.head.getZ());
	}

	public Vector crossProduct(Vector vec) {
		return new Vector(this.head.getY() * vec.head.getZ() - this.head.getZ() * vec.head.getY(),
								this.head.getZ() * vec.head.getX() - this.head.getX() * vec.head.getZ(),
								this.head.getX() * vec.head.getY() - this.head.getY() * vec.head.getX());

	}
	
	public double lengthSquared() {
		return this.head.getX() * this.head.getX() + this.head.getY() * this.head.getY() +
				this.head.getZ() * this.head.getZ();
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
