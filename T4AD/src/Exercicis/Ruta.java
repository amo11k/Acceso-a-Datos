package Exercicis;

import java.io.Serializable;
import java.util.ArrayList;

public class Ruta implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nom=null;;
	private ArrayList<PuntGeo> llistaDePunts;
	private int  desnivell=0;
	private int desnivellAcumulat=0;
	
	public Ruta(){
		this.llistaDePunts = new ArrayList<PuntGeo>();
	}
	
	public Ruta(String nom, ArrayList<PuntGeo> llistaDePunts, int desnivell, int desnivellAcumulat) {
		super();
		this.nom = nom;
		this.llistaDePunts = llistaDePunts;
		this.desnivell = desnivell;
		this.desnivellAcumulat = desnivellAcumulat;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public ArrayList<PuntGeo> getLlistaDePunts() {
		return llistaDePunts;
	}
	public void setLlistaDePunts(ArrayList<PuntGeo> llistaDePunts) {
		this.llistaDePunts = llistaDePunts;
	}
	
	public int getDesnivell() {
		return desnivell;
	}
	
	public void setDesnivell(int desnivell) {
		this.desnivell = desnivell;
	}
	
	public int getDesnivellAcumulat() {
		return desnivellAcumulat;
	}
	
	public void setDesnivellAcumulat(int desnivellAcumulat) {
		this.desnivellAcumulat = desnivellAcumulat;
	}
	
	public void addPunt(PuntGeo punt){
		this.llistaDePunts.add(punt);
	}
	
	public void addPunt(String nom, double latitud, double longitud){
		PuntGeo punt = new PuntGeo(nom,latitud,longitud); 
		this.llistaDePunts.add(punt);
	}

	public PuntGeo getPunt(int i){
		return llistaDePunts.get(i);
	}
	
	public String getPuntNom(int i){
		return llistaDePunts.get(i).getNom();
	}
	
	public double getPuntLatitud(int i){
		return llistaDePunts.get(i).getLatitud();
	}
	
	public double getPuntLongitud(int i){
		return llistaDePunts.get(i).getLongitud();
	}

	public int length(){
		return this.llistaDePunts.size();
	}
	
	public void mostraRuta(){
		//Aquest és el mètode que heu de completar
		System.out.println("Ruta: " + this.nom);
		System.out.println("Desnivell: " + this.desnivell);
		System.out.println("Desnivell Acumulat: " + this.desnivellAcumulat);
		System.out.println("Té " + this.length() + " punts");
		for (int i=0; i<this.length(); i++){
			PuntGeo p = this.llistaDePunts.get(i);
			System.out.println("Punt " + (i+1) + ": " + p.getNom() + " (" + p.getLatitud() + "," + p.getLongitud() + ")");
		}
		
	}
}
