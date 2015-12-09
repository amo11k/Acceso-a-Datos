package exemples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import geo.Comarques;

public class Exemple2 {
	public static void main(String[] args) {
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session sessio = sf.openSession();
		Transaction t = sessio.beginTransaction();

		Comarques com = new Comarques(); // A partir d'ací l'objecte és
											// transitori

		com.setNomC((String) "Columbretes");
		com.setProvincia("Castelló");

		sessio.save(com); // A partir d'ací és persistent

		t.commit();

		sessio.close(); // A partir d'ací és separat, igual es pot utilitzar,
						// però no estarà sincronitzat

		System.out.println(com.getNomC() + " (" + com.getProvincia() + ")");
	}
}
