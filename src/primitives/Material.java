/**
 * 
 */
package primitives;

/**
 * @author 97253
 * Material class (PDS) with builder design pattern
 */
public class Material {

	public double kD = 0, kS = 0;
	public int nShininess = 0;
	
	/**
	 * 
	 * @param kD
	 * @return The object material (this)
	 */
	public Material setkD(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * 
	 * @param kS
	 * @return The object material (this)
	 */
	public Material setkS(double kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * 
	 * @param nShininess
	 * @return The object material (this)
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}


}
