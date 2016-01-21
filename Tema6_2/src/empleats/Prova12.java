package empleats;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class Prova12 {

	public static void main(String[] args) {
		ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Empleats.db4o");
		String[] pobl = { "Castelló", "Borriana" };

		ObjectSet<Empleat> llista = bd.query(new EmpleatsPerPoblacio(pobl));

		for (Empleat e : llista) {
			System.out.println(e.getNom() + " (" + e.getAdreca().getPoblacio() + ")");
		}
		bd.close();
	}

	public static class EmpleatsPerPoblacio extends Predicate<Empleat> {
		String[] poblacions;

		public EmpleatsPerPoblacio(String[] poblacions) {
			this.poblacions = poblacions;
		}

		@Override
		public boolean match(Empleat emp) {
			boolean ret = false;
			for (int i = 0; !ret && i < poblacions.length; i++) {
				if (emp.getAdreca().getPoblacio().equalsIgnoreCase(poblacions[i]))
					ret = true;
			}
			return ret;
		}
	}
}
