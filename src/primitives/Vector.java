package primitives;

public class Vector {

	Point3D head;
	
	public Vector(Coordinate p1,Coordinate p2,Coordinate p3) {
		head = new Point3D(p1, p2, p3);
	}
	
	public Vector(double p1,double p2,double p3) {
		head = new Point3D(p1, p2, p3);
	}

	public Vector(Point3D p1) {
		head = p1;
	}
}
