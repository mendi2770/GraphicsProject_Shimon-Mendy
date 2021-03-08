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
	
	public Vector subtract(Point3D p1) {
	Vector temp = new Vector(p1.x.coord - this.x.coord,p1.y.coord - this.y.coord,p1.z.coord-this.z.coord);
	return temp;	
	}
	
	public Point3D add(Vector vec) {
		Point3D temp = new Point3D(vec.head.x.coord + this.x.coord, vec.head.y.coord + this.y.coord, vec.head.z.coord + this.z.coord);
		return temp;
	}
	
	double distanceSquared(Point3D p1,Point3D p2) {
		Vector vec = p1.subtract(p2);
		double distance = vec.head.x.coord * vec.head.x.coord + vec.head.y.coord * vec.head.y.coord +
				vec.head.z.coord * vec.head.z.coord;
		return distance;
	}
	
	double distance(Point3D p1,Point3D p2) {
		double dis = Math.sqrt(distanceSquared(p1, p2));
		return dis;
	}

}
