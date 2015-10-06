package exercici3_2;

import java.io.Serializable;

public class Coordenades implements Serializable {

	private static final long serialVersionUID = 1L;
	private Double latitud;
	private Double longitud;

	public Coordenades(Double lat, Double longi) {
		latitud = lat;
		longitud = longi;
	}

	public Double getLatitud() {
		return latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLatitud(Double x) {
		latitud = x;
	}

	public void setLongitud(Double x) {
		longitud = x;
	}
}
