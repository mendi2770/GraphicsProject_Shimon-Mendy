package primitives;

public class Ray {

	Point3D p0;
	Vector dir;
	
	public Ray(Vector vec,Point3D p) {
		dir = vec.normalized();
		p0 = new Point3D(p.x, p.y, p.z);
	}

}
