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
	public Material setKd(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * 
	 * @param kS
	 * @return The object material (this)
	 */
	public Material setKs(double kS) {
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

//	/**
//	 * @return The kD
//	 */
//	public double getkD() {
//		return kD;
//	}
//
//	/**
//	 * @return The kS
//	 */
//	public double getkS() {
//		return kS;
//	}
//
//	/**
//	 * @return The nShininess
//	 */
//	public int getnShininess() {
//		return nShininess;
//	}


}
