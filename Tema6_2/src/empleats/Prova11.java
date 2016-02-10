package empleats;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Prova11 {

	public static void main(String[] args) {
		ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Empleats.db4o");

		Empleat f = new Empleat();
		//f.setDepartament(10);
		//f.setAdreca(new Adreca(null, null, "Castelló"));

		ObjectSet<Empleat> llista = bd.queryByExample(new Empleat()); //SACA UNA LISTA DE OBJETO
		for (Empleat e : llista) {
			System.out.println("Nif: " + e.getNif() + ". Nom: " + e.getNom() + ". Departament:" + e.getDepartament()
					+ ". Població:" + e.getAdreca().getPoblacio());
		}
		bd.close();
	}
}
