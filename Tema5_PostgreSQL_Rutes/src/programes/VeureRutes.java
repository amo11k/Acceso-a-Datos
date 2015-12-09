package programes;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dades.Ruta;
import dades.SessionFactoryUtil;

public class VeureRutes {

	public static void main(String[] args) {
		Session sessio = SessionFactoryUtil.getSessionFactory().openSession();
		Query q = sessio.createQuery("from Ruta order by nomR");
		
		for (Ruta r : (List<Ruta>) q.list())
			System.out.println(r.getNomR() + " - " + r.getPunts().size()+" punts");
		
		sessio.close();
	}

}
