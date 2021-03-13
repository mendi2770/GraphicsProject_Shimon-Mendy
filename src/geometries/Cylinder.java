package geometries;

import primitives.*;
import static primitives.Util.*;
import primitives.Vector;

public class Cylinder extends Tube implements Geometry {

	private double height;
	
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	/*************** Admin *****************/

	@Override
	public String toString() {
		return "Height: " + String.valueOf(this.height);
	}
}
