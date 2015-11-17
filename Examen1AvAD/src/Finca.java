import java.io.Serializable;
import java.util.ArrayList;

public class Finca implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String adreça=null;
	ArrayList<Vivenda> vivendes;
	
	public Finca(){
		vivendes = new ArrayList<Vivenda>();
	}
	
	public Finca(String adreça, ArrayList<Vivenda> vivendes){
		this.adreça=adreça;
		this.vivendes=vivendes;
	}

	public String getAdreça() {
		return adreça;
	}

	public void setAdreça(String adreça) {
		this.adreça = adreça;
	}

	public ArrayList<Vivenda> getVivendes() {
		return vivendes;
	}

	public void setVivendes(ArrayList<Vivenda> vivendes) {
		this.vivendes = vivendes;
	}
	
	public void resum() {
		System.out.println(this.getAdreça());
		for (Vivenda v : this.getVivendes()){
			System.out.println("\t vivenda " + v.getPis() + v.getPorta() + ", habitants:" + v.getNoms().size());
		}
	}
}
