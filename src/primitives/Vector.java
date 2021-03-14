package primitives;

import static primitives.Util.isZero;

public class Vector {

	/**
	 * Vector's head point
	 */
	private Point3D head;

	/**
	 * Constructor of the vector by three points
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Vector(Coordinate p1, Coordinate p2, Coordinate p3) {
		Point3D p = new Point3D(p1, p2, p3);
		 // Throws exception in case the point equals zero:
		if (p.equals(Point3D.ZERO))
			throw new IllegalArgumentException("The coordiates cannot be  zeroes.");
		head = p;
	}

	/**
	 * Constructor of a vector by three numbers
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
	 * @param p
	 */
	public Vector(Point3D p) {
		// Throws exception in case the point equals zero::
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

	/**
	 * Subtracts a given vector from the current one
	 * @param vec
	 * @return the new vector created by the subtraction
	 */
	public Vector subtract(Vector vec) {
		return new Vector(this.head.getX() - vec.head.getX(), this.head.getY() - vec.head.getY(), this.head.getZ() - vec.head.getZ());
		
	}

	/**
	 * Adds a given vector to the current one
	 * @param vec
	 * @return the new vector created by the addition
	 */
	public Vector add(Vector vec) {
		return new Vector(this.head.getX() + vec.head.getX(), this.head.getY() + vec.head.getY(), this.head.getZ() + vec.head.getZ());
	
	}
	
	/**
	 * Multiplication of a vector with a scalar 
	 * @param scale
	 * @return the new vector created by the multiplication with the scalar
	 */
	public Vector scale(double scale) {
		return new Vector(scale * this.head.getX(), scale *  this.head.getY(), scale * this.head.getZ());

	}
	
	/**
	 * executes a dot product calculation between a given vector and the current one
	 * @param vec
	 * @return result of the dot product made between the two vectors
	 */
	public double dotProduct(Vector vec) {
		return (vec.head.getX() * this.head.getX() + vec.head.getY() * this.head.getY() + vec.head.getZ() * this.head.getZ());
	}

	/**
	 * executes a cross product calculation between a given vector and the current one
	 * @param vec
	 * @return a vector created by the cross product made between two vectors
	 */
	public Vector crossProduct(Vector vec) {
		return new Vector(vec.head.getY() * this.head.getZ() - vec.head.getZ() * this.head.getY(),
								vec.head.getZ() * this.head.getX() - vec.head.getX() * this.head.getZ(),
								vec.head.getX() * this.head.getY() - vec.head.getY() * this.head.getX());

	}
	
	/**
	 * Calculates the squared length of a vector
	 * @return the result of the calculation
	 */
	public double lengthSquared() {
		return this.head.getX() * this.head.getX() + this.head.getY() * this.head.getY() +
				this.head.getZ() * this.head.getZ();
	}
	
	/**
	 * Calculates the length of a vector
	 * @return the result of the calculation
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}
	
	/**
	 * Executes a normalization of the current vector and changes it
	 * @return the vector after its normalization
	 */
	public Vector normalize() {
		this.head = this.scale(1 / this.length()).head;
		return this;
	} 
	
	/**
	 * Executes a normalization of the current vector without changing it
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
