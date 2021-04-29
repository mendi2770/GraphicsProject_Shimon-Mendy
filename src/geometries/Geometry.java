package geometries;
import java.util.List;


import primitives.*;
import static primitives.Util.*;

public abstract class Geometry implements Intersectable {
	protected Color emmission = Color.BLACK;
	
	
	/**
	 * @return the emmission
	 */
	public Color getEmmission() {
		return emmission;
	}


	/**
	 * @param emmission the emmission to set
	 */
	public Geometry setEmmission(Color emmission) {
		this.emmission = emmission;
		return this;
	}


	/**
	 * Calculates the normal of the geometry shape
	 * @param point
	 * @return the normal vector of the shape
	 */
	public abstract Vector getNormal(Point3D point);
	
	
}
