package primitives;

import static primitives.Util.isZero;

public class Vector {

	/**
	 * Vector's head point
	 */
	public Point3D head;


	/**
	 * Constructor of a vector by three numbers
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Vector(double p1, double p2, double p3) {
		Point3D p = new Point3D(p1, p2, p3);
		// Throws exception in case the point equals zero:
		if (p.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The numbers cannot be zeroes.");
		head = p;
	}

	/**
	 * Constructor of a vector by a Point3D
	 * 
	 * @param p
	 */
	public Vector(Point3D p) {
		// Throws exception in case the point equals zero::
		if (p.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The numbers cannot be zeroes.");
		head = p;
	}

	/**
	 * Subtracts a given vector from the current one
	 * 
	 * @param vec
	 * @return the new vector created by the subtraction
	 */
	public Vector subtract(Vector vec) {
		return new Vector(this.head.x.coord - vec.head.x.coord, this.head.y.coord - vec.head.y.coord,
				this.head.z.coord - vec.head.z.coord);
	}

	/**
	 * Adds a given vector to the current one
	 * 
	 * @param vec
	 * @return the new vector created by the addition
	 */
	public Vector add(Vector vec) {
		return new Vector(this.head.x.coord + vec.head.x.coord, this.head.y.coord + vec.head.y.coord,
				this.head.z.coord + vec.head.z.coord);

	}

	/**
	 * Multiplication of a vector with a scalar
	 * 
	 * @param scale
	 * @return the new vector created by the multiplication with the scalar
	 */
	public Vector scale(double scale) {
		return new Vector(scale * this.head.x.coord, scale * this.head.y.coord, scale * this.head.z.coord);

	}

	/**
	 * executes a dot product calculation between a given vector and the current one
	 * 
	 * @param vec
	 * @return result of the dot product made between the two vectors
	 */
	public double dotProduct(Vector vec) {
		return (vec.head.x.coord * this.head.x.coord + vec.head.y.coord * this.head.y.coord
				+ vec.head.z.coord * this.head.z.coord);
	}

	/**
	 * executes a cross product calculation between a given vector and the current
	 * one
	 * 
	 * @param vec
	 * @return a vector created by the cross product made between two vectors
	 */
	public Vector crossProduct(Vector vec) {
	
		return new Vector(vec.head.y.coord * this.head.z.coord - vec.head.z.coord * this.head.y.coord,
				vec.head.z.coord * this.head.x.coord - vec.head.x.coord * this.head.z.coord,
				vec.head.x.coord* this.head.y.coord - vec.head.y.coord * this.head.x.coord);

	}

	/**
	 * Calculates the squared length of a vector
	 * 
	 * @return the result of the calculation
	 */
	public double lengthSquared() {
		return this.head.x.coord * this.head.x.coord + this.head.y.coord * this.head.y.coord
				+ this.head.z.coord * this.head.z.coord;
	}

	/**
	 * Calculates the length of a vector
	 * 
	 * @return the result of the calculation
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	/**
	 * Executes a normalization of the current vector and changes it
	 * 
	 * @return the vector after its normalization
	 */
	public Vector normalize() {
		this.head = this.scale(1 / this.length()).head;
		return this;
	}

	/**
	 * Executes a normalization of the current vector without changing it
	 * 
	 * @return a new normalized vector based on the current vector
	 */
	public Vector normalized() {

		return new Vector(this.head).normalize();
	}

	/*************** Admin ******************/
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
