package exercici3_2;

import java.io.Serializable;
import java.util.ArrayList;

public class Ruta implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nom=null;;
	private ArrayList<PuntGeo> llistaDePunts;
	private int  desnivell=0;
	private int desnivellAcumulat=0;
	private StringBuilder build = new StringBuilder();
	
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
		return llistaDePunts.get(i).getName();
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
		build.append("Ruta: "+nom+"\n");
		build.append("Desnivell: "+desnivell+"\n");
		build.append("Desnivell acumulat: "+desnivellAcumulat+"\n");
		build.append("Té "+this.length()+" punts:"+"\n");
		for (int i=0;i<this.length();i++){
			build.append("Punt "+i+": "+getPuntNom(i)+" ("+getPuntLatitud(i)+", "+getPuntLongitud(i)+")"+"\n");
		}
		
		System.out.println(build.toString());
		
	}
		
	}