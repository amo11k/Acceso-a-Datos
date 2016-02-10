package ejemplos;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

public class Prova5_XQJ {
	public static void main(String[] args) throws XQException {
		XQDataSource s = new ExistXQDataSource();
		s.setProperty("serverName", "localhost");
		s.setProperty("port", "8080");
		s.setProperty("user", "admin");
		s.setProperty("password", "admin");

		XQConnection conn = s.getConnection();
		String sent = "for $alumne in doc(\"/db/Tema7_AD/classe.xml\")//alumne  return xs:int($alumne/nota/text())";

		XQPreparedExpression cons = conn.prepareExpression(sent);

		XQResultSequence rs = cons.executeQuery();
		while (rs.next())
			System.out.println(rs.getInt());

		conn.close();
	}
}
