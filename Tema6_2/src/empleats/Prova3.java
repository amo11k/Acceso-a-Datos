package empleats;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Prova3 {

	public static void main(String[] args) {

		ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Empleats.db4o");
		Empleat e = new Empleat("22222222b");

		// Si posàrem ací db.delete(e) no tindría efecte, perquè e no es
		// correspon amb cap instància de la BD

		ObjectSet<Empleat> llista = bd.queryByExample(e);
		if (llista.hasNext()) {
			e = llista.next();
			bd.delete(e);
		}
		bd.close();
	}
}
