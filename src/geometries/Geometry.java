package geometries;
import java.util.List;


import primitives.*;
import static primitives.Util.*;

public abstract class Geometry extends Intersectable {
	
	protected Color emission = Color.BLACK;
	private Material material = new Material();


	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}


	/**
	 * 
	 * @param material
	 * @return This (of geometry)
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
	 * 
	 * @param emmission
	 * @return This (of geometry)
	 */
	public Geometry setEmission(Color emmission) {
		this.emission = emmission;
		return this;
	}
	
	/**
	 * @return The kD
	 */
	public double getkD() {
		return material.kD;
	}

	/**
	 * @return The kS
	 */
	public double getkS() {
		return material.kS;
	}

	/**
	 * @return The nShininess
	 */
	public int getnShininess() {
		return material.nShininess;
	}

	/**
	 * Calculates the normal of the geometry shape
	 * @param point
	 * @return the normal vector of the shape
	 */
	public abstract Vector getNormal(Point3D point);	
}
