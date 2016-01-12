package empleats;

public class Empleat {
	private String nif;
	private String nom;
	private int departament;
	private int edat = 0;
	private double sou = 0.0;
	private byte[] foto;
	private char[] curriculum;
	private Adreca adreca;
	private String[] correus_e;
	private Telefon[] telefons;

	public Empleat() {
	}

	public Empleat(String nif) {
		this.setNif(nif);
	}

	public Empleat(String nif, String nom, int dep, int edat, double sou, byte[] foto, char[] curr, Adreca adr,
			String[] corr, Telefon[] tels) {
		this.setNif(nif);
		this.setNom(nom);
		this.setDepartament(dep);
		this.setEdat(edat);
		this.setSou(sou);
		this.setFoto(foto);
		this.setCurriculum(curr);
		this.setAdreca(adr);
		this.setCorreus_e(corr);
		this.setTelefons(tels);
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getDepartament() {
		return departament;
	}

	public void setDepartament(int departament) {
		this.departament = departament;
	}

	public int getEdat() {
		return edat;
	}

	public void setEdat(int edat) {
		this.edat = edat;
	}

	public double getSou() {
		return sou;
	}

	public void setSou(double sou) {
		this.sou = sou;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public char[] getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(char[] curriculum) {
		this.curriculum = curriculum;
	}

	public Adreca getAdreca() {
		return adreca;
	}

	public void setAdreca(Adreca adreca) {
		this.adreca = adreca;
	}

	public String[] getCorreus_e() {
		return correus_e;
	}

	public void setCorreus_e(String[] correus_e) {
		this.correus_e = correus_e;
	}

	public Telefon[] getTelefons() {
		return telefons;
	}

	public void setTelefons(Telefon[] telefons) {
		this.telefons = telefons;
	}

	// mètodes get i set

}
