package exemples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import geo.Comarques;

public class PrimerAcces {

	public static void main(String[] args) {
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session sessio = sf.openSession();
		Comarques com = (Comarques) sessio.load(Comarques.class, "Alt Maestrat");
		System.out.print("Comarca " + com.getNomC() + ": ");
		System.out.print(com.getProvincia());
		System.out.println(" (" + com.getPoblacionses().size() + " pobles)");
		sessio.close();
	}
}