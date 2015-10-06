package exercici3_2;

import java.io.Serializable;

public class PuntGeo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Coordenades coordenades;
	
	public PuntGeo(String nom, Coordenades coord){
		name=nom;
		coordenades=coord;
	}
	public PuntGeo(String nom, double latitud, double longitud){
		name=nom;
		coordenades = new Coordenades(latitud,longitud);
	}
	public String getName() {
		return name;
	}
	public Coordenades getCoordenades() {
		return coordenades;
	}
	public Double getLatitud(){
		return coordenades.getLatitud();
	}
	
	public Double getLongitud(){
		return coordenades.getLongitud();
	}
}
