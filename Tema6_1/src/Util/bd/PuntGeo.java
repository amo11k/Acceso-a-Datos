package Util.bd;


import java.io.Serializable;

public class PuntGeo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nom=null;
	private Coordenades coord;
	
	public PuntGeo(String nom, Coordenades coord){
		this.setNom(nom);
		this.setCoord(coord);
	}
	
	public PuntGeo(String nom, double latitud, double longitud){
		this.setNom(nom);
		this.setCoord(new Coordenades(latitud,longitud));
	}
	
	public PuntGeo(){
		this.setCoord(new Coordenades());
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Coordenades getCoord() {
		return coord;
	}

	public void setCoord(Coordenades coord) {
		this.coord = coord;
	}
	
	public double getLatitud(){
		return this.coord.getLatitud();
	}
	
	public double getLongitud(){
		return this.coord.getLongitud();
	}
	
}
