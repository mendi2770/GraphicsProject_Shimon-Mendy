package geometries;
import java.util.List;
import primitives.*;
import static primitives.Util.*;

public interface Geometry {
	/**
	 * Calculates the normal of the geometry shape
	 * @param point
	 * @return the normal vector of the shape
	 */
	public Vector getNormal(Point3D point);
}
