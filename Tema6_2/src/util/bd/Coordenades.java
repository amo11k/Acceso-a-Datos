package util.bd;

import java.io.Serializable;

public class Coordenades implements Serializable{

	private static final long serialVersionUID = 1L;
	public double latitud=0;
	public double longitud=0;
	
	public Coordenades(double latitud, double longitud){
		this.latitud=latitud;
		this.longitud=longitud;
	}
	
	public Coordenades(){
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
}
