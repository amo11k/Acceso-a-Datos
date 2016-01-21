package empleats;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Prova16 {

	public static void main(String[] args) {

		ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Empleats.db4o");

		Query q = bd.query();
		q.constrain(Empleat.class);

		Query node = q.descend("departament");
		node.constrain(10);

		node = q.descend("adreca").descend("poblacio");
		node.constrain("Castelló");

		ObjectSet<Empleat> llista = q.execute();

		for (Empleat e : llista) {
			System.out.println("Nom: " + e.getNom() + ". Població: " + e.getAdreca().getPoblacio() + ". Departament: "
					+ e.getDepartament());
		}
		bd.close();
	}
}
