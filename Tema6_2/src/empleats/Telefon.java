package empleats;

public class Telefon {
	private boolean mobil;
	private String numero;

	public Telefon() {
	}

	public Telefon(boolean m, String num) {
		setMobil(m);
		setNumero(num);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public boolean isMobil() {
		return mobil;
	}

	public void setMobil(boolean mobil) {
		this.mobil = mobil;
	}

	// mètode get i set per a numero

}
