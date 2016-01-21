package geo;
// Generated 10/11/2015 17:08:58 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Comarques generated by hbm2java
 */
public class Comarques implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomC;
	private String provincia;
	private Set<Poblacions> poblacionses = new HashSet<Poblacions>(0);

	public Comarques() {
	}

	public Comarques(String nomC) {
		this.nomC = nomC;
	}

	public Comarques(String nomC, String provincia, Set<Poblacions> poblacionses) {
		this.nomC = nomC;
		this.provincia = provincia;
		this.poblacionses = poblacionses;
	}

	public String getNomC() {
		return this.nomC;
	}

	public void setNomC(String nomC) {
		this.nomC = nomC;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Set<Poblacions> getPoblacionses() {
		return this.poblacionses;
	}

	public void setPoblacionses(Set<Poblacions> poblacionses) {
		this.poblacionses = poblacionses;
	}

}