package empleats;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Prova14 {

	public static void main(String[] args) {
		ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Empleats.db4o");
		Query q = bd.query(); // node arrel.
		q.constrain(Empleat.class); // limitem la cerca als Empleats (pot haver
									// guardarda més d'una classe)

		Query node = q.descend("nif"); // arribem a l'altura del nif, que és on
										// posem la restricció
		node.constrain("11111111a");

		ObjectSet<Empleat> llista = q.execute();

		for (Empleat e : llista) {
			System.out.println("Nif: " + e.getNif() + ". Nom: " + e.getNom() + " (" + e.getSou() + ")");
		}
		bd.close();
	}
}
