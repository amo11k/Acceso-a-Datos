package ejemplos;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class Prova1_XQJ {

	public static void main(String[] args) throws XQException {
		XQDataSource s = new ExistXQDataSource(); //CONEXION POR DEFECTO
		/*s.setProperty("serverName", "localhost");
		s.setProperty("port", "8080");
		s.setProperty("user", "admin");
		s.setProperty("password", "admin");*/

		XQConnection conn = s.getConnection(); //SE PUEDE HACER DIRECTAMENTE SI NO HACE FALTA CAMBIAR NINGUNA PROPIEDAD
		System.out.println("Connexió feta");
		String sent = "for $alumne in doc(\"/db/Tema7_AD/clase.xml\")//alumne order by $alumne/cognoms return $alumne/cognoms/text()";
		
		
		//CON PREPAREDEXPRESSION
		/*XQPreparedExpression cons = conn.prepareExpression(sent); //ESTO VA IGUAL QUE UN PREPAREDSTATEMENT
		XQResultSequence rs = cons.executeQuery(); //PREPAREDEXPRESION = PREPAREDSTATEMENT ------ EXPRESION = STATEMENT
		while (rs.next())
			System.out.println(rs.getItemAsString(null));
		
		conn.close();*/
		
		
		//CON XQEXPRESSION
		XQExpression cons = conn.createExpression();
		XQResultSequence rs = cons.executeQuery(sent);
		while(rs.next())
			System.out.println(rs.getItemAsString(null));
		 
		conn.close();
	}
}
