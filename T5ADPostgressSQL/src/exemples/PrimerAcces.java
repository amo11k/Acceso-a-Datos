package exemples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import geo.Comarques;

public class PrimerAcces {

	public static void main(String[] args) {
		SessionFactory sf = SessionFactoryUtil.getSessionFactory(); //INICIAR SESSIONFACTORY
		Session sessio = sf.openSession();		//INICIAR SESSIONFACTORY
		Comarques com = (Comarques) sessio.load(Comarques.class, "Alt Maestrat"); //CARGAR DATOS DESDE CLASE COMARQUES
		System.out.print("Comarca " + com.getNomC() + ": ");
		System.out.print(com.getProvincia());
		System.out.println(" (" + com.getPoblacionses().size() + " pobles)"); //OBTENER TODOS LOS ELEMENTOS DE LA TABLA POBLACIONS
		sessio.close();
	}
}