package empleats;

public class Adreca {
	private String carrer;
	private String codipostal;
	private String poblacio;

	public String getCarrer() {
		return carrer;
	}

	public void setCarrer(String carrer) {
		this.carrer = carrer;
	}

	public String getCodipostal() {
		return codipostal;
	}

	public void setCodipostal(String codipostal) {
		this.codipostal = codipostal;
	}

	public String getPoblacio() {
		return poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	public Adreca() {
	}

	public Adreca(String c, String cp, String p) {
		setCarrer(c);
		setCodipostal(cp);
		setPoblacio(p);
	}

	// mètodes get i set

}
