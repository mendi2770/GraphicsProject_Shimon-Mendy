package geometries;
import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * 
 * 
 * Gets the normal to the Geometry shape=
 */
public interface Geometry {
	public Vector getNormal(Point3D point);
}
