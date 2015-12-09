package exemples;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import geo.Comarques;
import geo.Poblacions;

public class TercerAcces {

	public static void main(String[] args) {

		SessionFactory sfact = SessionFactoryUtil.getSessionFactory();
		Session sessio = sfact.openSession();
		Comarques com = (Comarques) sessio.load(Comarques.class, "Alt Maestrat");
		System.out.print("Comarca " + com.getNomC() + ": ");
		System.out.print(com.getProvincia());
		System.out.println(" (" + com.getPoblacionses().size() + " pobles)");
		System.out.println();
		System.out.println("Llista de pobles");
		System.out.println("-----------------");

		for (Poblacions p : com.getPoblacionses())
			System.out.println(p.getNom() + " (" + p.getPoblacio() + " habitants)");

		sessio.close();
	}
}