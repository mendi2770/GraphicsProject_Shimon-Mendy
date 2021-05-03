package geometries;
import java.util.List;


import primitives.*;
import static primitives.Util.*;

public abstract class Geometry implements Intersectable {
	
	protected Color emission = Color.BLACK;
	private Material material = new Material();
	
	
	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}


	/**
	 * @param material the material to set
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}

	/**
	 * @return the emission
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * @param emmission the emission to set
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
