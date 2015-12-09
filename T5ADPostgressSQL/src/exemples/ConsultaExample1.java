package exemples;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ConsultaExample1 {

	public static void main(String[] args) {
		Session sessio = SessionFactoryUtil.getSessionFactory().openSession();

        Query q = sessio.createQuery("select c.nomC,count(p.codM),avg(p.altura) "
                                        + "from Comarques c , Poblacions p "
                                        + "where c.nomC=p.comarques.nomC "
                                        + "group by c.nomC "
                                        + "order by c.nomC");
        List<Object[]> llista = q.list();
        for (Object[] tot : llista)
            System.out.println("Comarca: " + tot[0] + ". Núm. pobles: " + tot[1] + ". Altura mitjana: " + tot[2]);
	}

}

/*
 * ******************************CONSULTA BASICA************************ Session
 * sessio = SessionFactoryUtil.getSessionFactory().openSession();
 * 
 * Query q = sessio.createQuery(
 * "from Poblacions where altura>=? and comarques.nomC=?"); q.setInteger(0,
 * 500); q.setString(1, "Alcalatén");
 * 
 * for (Poblacions p : (List<Poblacions>) q.list()){
 * System.out.println(p.getNom() + " --> " + p.getAltura());
 */

/*				**************************MULTIPLES VALORS**********************
 * 		Session sessio = SessionFactoryUtil.getSessionFactory().openSession();
		Query q = sessio.createQuery("from Poblacions p, Comarques c where p.comarques.nomC=c.nomC order by p.nom");

		Iterator it = q.iterate();
		while (it.hasNext()) {
			Object[] tot = (Object[]) it.next();
			Poblacions p = (Poblacions) tot[0];
			Comarques c = (Comarques) tot[1];
			System.out.println(p.getNom() + " (" + c.getNomC() + ". " + c.getProvincia() + ")");
		}
*/

/*
 * //*******************UN UNIC VALOR**************************
		Session sessio = SessionFactoryUtil.getSessionFactory().openSession();

        Query q = sessio.createQuery("select avg(altura) from Poblacions");

        Double mitjana = (Double) q.uniqueResult();
        
        System.out.println("Altura mitjana: " + mitjana);*/

