package exemples;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import geo.Comarques;

public class Exemple5 {

    public static void main(String[] args) {
    	//**************Recorrer con list()*********************
        Session sessio = SessionFactoryUtil.getSessionFactory().openSession();

        Query q = sessio.createQuery("from Comarques");

        List<Comarques> llista = q.list();

        Iterator<Comarques> it = llista.iterator();
        while (it.hasNext()) {
            Comarques com = it.next();
            System.out.println(com.getNomC() + " - " + com.getProvincia());
        }

    }

}

/*		**************CON ITERATOR*****************
 *      Session sessio = SessionFactoryUtil.getSessionFactory().openSession();
        Query q = sessio.createQuery("from Comarques");
        List<Comarques> llista = q.list();
        Iterator<Comarques> it = llista.iterator();
        while (it.hasNext()) {
            Comarques com = it.next();
            System.out.println(com.getNomC() + " - " + com.getProvincia());
        }
    }
}*/