import java.io.Serializable;
import java.util.ArrayList;

public class Vivenda implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int pis=0;
	String porta=null;
	ArrayList<String> noms;
	
	public Vivenda(){
		noms = new ArrayList<String>();
	}
	
	public Vivenda(int pis,String porta,ArrayList<String> noms ){
		this.pis=pis;
		this.porta=porta;
		this.noms=noms;
	}

	public int getPis() {
		return pis;
	}

	public void setPis(int pis) {
		this.pis = pis;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public ArrayList<String> getNoms() {
		return noms;
	}

	public void setNoms(ArrayList<String> noms) {
		this.noms = noms;
	}
}
