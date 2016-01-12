package empleats;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Prova2 {
	public static void main(String[] args) {
		ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Empleats.db4o");
		Empleat e = new Empleat();
		Adreca a=new Adreca();
		a.setPoblacio("Castelló");
		e.setAdreca(a);
		ObjectSet<Empleat> llista = bd.queryByExample(e);

		for(Empleat empleat: llista) {
			e = llista.next();
			System.out.println(
					"Nif:" + e.getNif() + ". Nom:" + e.getNom() + ". Població: " + e.getAdreca().getPoblacio());
	
		}
		bd.close();
	}
}
