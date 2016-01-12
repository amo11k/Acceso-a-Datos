package empleats;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;

public class Prova5 {

	public static void main(String[] args) {
		EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
		conf.common().objectClass(Empleat.class).cascadeOnUpdate(true);

		ObjectContainer bd = Db4oEmbedded.openFile(conf, "Empleats.db4o");

		Empleat e = new Empleat("11111111a");
		ObjectSet<Empleat> llista = bd.queryByExample(e);
		if (llista.hasNext()) {
			e = llista.next();
			e.setSou(e.getSou() + 200);
			Adreca adr = e.getAdreca();
			adr.setCarrer("Pl. Rei en Janume, 15");
			adr.setCodipostal("12002");
			e.setAdreca(adr);
			bd.store(e);
		}
		bd.close();
	}
}
