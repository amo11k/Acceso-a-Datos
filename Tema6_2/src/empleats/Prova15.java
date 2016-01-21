package empleats;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Prova15 {

	public static void main(String[] args) {

		ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Empleats.db4o");

		Query q = bd.query(); // node arrel.
		q.constrain(Empleat.class); // limitem la cerca als Empleats (pot haver
									// guardarda més d'una classe)
		Query node = q.descend("sou"); // arribem a l'altura del sou, que és on
										// posem restriccions
		node.constrain(1000).greater().and(node.constrain(2000).smaller().equal());
		node.orderDescending();

		ObjectSet<Empleat> llista = q.execute();

		for (Empleat e : llista) {
			System.out.println(e.getNom() + " (" + e.getSou() + ")");
		}
		bd.close();
	}
}
