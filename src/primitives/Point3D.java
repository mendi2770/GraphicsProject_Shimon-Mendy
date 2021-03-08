package primitives;

public class Point3D {

	final static Point3D ZERO = new Point3D(0,0,0);
	
	Coordinate x;
	Coordinate y;
	Coordinate z;
	

	public Point3D(Coordinate p1,Coordinate p2,Coordinate p3) {
		x = p1;
		y= p2;
		z= p3;
	}
	
	public Point3D(double p1,double p2,double p3) {
		x = new Coordinate(p1);
		y= new Coordinate(p2);
		z= new Coordinate(p3);
	}

}
