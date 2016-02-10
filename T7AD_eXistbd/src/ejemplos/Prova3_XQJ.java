package ejemplos;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQStaticContext;

import net.xqj.exist.ExistXQDataSource;

public class Prova3_XQJ {

	public static void main(String[] args) throws XQException {
		XQDataSource s = new ExistXQDataSource();
		s.setProperty("serverName", "localhost");
		s.setProperty("port", "8080");
		s.setProperty("user", "admin");
		s.setProperty("password", "admin");

		XQConnection conn = s.getConnection();
		String sent = "for $alumne in doc(\"/db/Tema7_AD/clase.xml\")//alumne order by $alumne/cognoms return $alumne";

		XQStaticContext cntxt = conn.getStaticContext(); //CON ESTO PODREMOS MODIFICAR EL CONTEXTO PARA RECORRER HACIA ATRÁS O ALANTE
		cntxt.setScrollability(XQConstants.SCROLLTYPE_SCROLLABLE); 
		conn.setStaticContext(cntxt);
		XQPreparedExpression cons = conn.prepareExpression(sent);
		XQResultSequence rs = cons.executeQuery();
		rs.afterLast();					//RECORREMOS EL RESULTSEQUENCE AL REVÉS.
		while (rs.previous())
			System.out.println(rs.getItemAsString(null));

		conn.close();
	}
}
