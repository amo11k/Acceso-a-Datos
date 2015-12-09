package exemples;

import org.hibernate.Session;
import org.hibernate.Transaction;

import geo.Comarques;

public class QuartAcces {

	public static void main(String[] args) {
		//**********************INSERTAR********************
		Session sessio = SessionFactoryUtil.getSessionFactory().openSession();
		Transaction t = sessio.beginTransaction();
		Comarques com = new Comarques();

		com.setNomC((String) "Columbretes");
		com.setProvincia("Castelló");

		sessio.save(com);

		t.commit();
		sessio.close();
	}
}

/*
 		***********************BORRAR*************************
		Session sessio = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction t = sessio.beginTransaction();
        Comarques com = (Comarques) sessio.load(Comarques.class, "Columbretes");
        
        sessio.delete(com);

        t.commit();
        sessio.close();
*/

/*		**********************MODIFICAR********************
 *  	Session sessio = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction t = sessio.beginTransaction();
        Comarques com = (Comarques) sessio.load(Comarques.class, "Camp de Morvedre");
        
        com.setProvincia("Castelló");
        sessio.update(com); OR sessio.save(com);

        t.commit();
        sessio.close();
 * */