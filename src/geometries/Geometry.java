package geometries;
import java.util.List;


import primitives.*;
import static primitives.Util.*;

public abstract class Geometry implements Intersectable {
	protected Color emission = Color.BLACK;
	
	
	/**
	 * @return the emmission
	 */
	public Color getEmission() {
		return emission;
	}


	/**
	 * @param emmission the emmission to set
	 */
	public Geometry setEmission(Color emmission) {
		this.emission = emmission;
		return this;
	}


	/**
	 * Calculates the normal of the geometry shape
	 * @param point
	 * @return the normal vector of the shape
	 */
	public abstract Vector getNormal(Point3D point);
	
	
}
