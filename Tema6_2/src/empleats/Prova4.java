package empleats;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;

public class Prova4 {

	public static void main(String[] args) {
		EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
		conf.common().objectClass(Empleat.class).cascadeOnDelete(true);

		ObjectContainer bd = Db4oEmbedded.openFile(conf, "Empleats.db4o");

		Empleat e = new Empleat("33333333c");
		ObjectSet<Empleat> llista = bd.queryByExample(e);
		if (llista.hasNext()) {
			e = llista.next();
			bd.delete(e);
		}
		bd.close();
	}
}
