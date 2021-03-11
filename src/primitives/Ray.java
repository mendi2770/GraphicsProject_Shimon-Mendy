package primitives;

import static primitives.Util.isZero;

public class Ray {

	private Point3D p0;
	private Vector dir;
	
	public Ray(Vector vec,Point3D p) {
		dir = vec.normalized();
		p0 = new Point3D(p.getX(), p.getY(), p.getZ());
	}

	
	
	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}



	/**
	 * @return the dir
	 */
	public Vector getDir() {
		return dir;
	}



	/*************** Admin ******************/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray other = (Ray) obj;
		return p0.equals(other.p0) && dir.equals(other.dir);
	}

	@Override
	public String toString() {
		return "Point: " + p0.toString() + " Direction: " + dir.toString();
	}
}
