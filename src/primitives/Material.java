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
	
	/**
	 * KT = transparency coefficient
	 * KR = reflection coefficient
	 */
	public double kT = 0.0, kR = 0.0;
	
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

	/**
	 * @param kT
	 * @return This
	 */
	public Material setkT(double kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * 
	 * @param kR
	 * @return This
	 */
	public Material setkR(double kR) {
		this.kR = kR;
		return this;
	}

	/**
	 * @return the kT
	 */
	public double getkT() {
		return kT;
	}

	/**
	 * @return the kR
	 */
	public double getkR() {
		return kR;
	}

	
}
