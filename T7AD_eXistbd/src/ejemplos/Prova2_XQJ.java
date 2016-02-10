package ejemplos;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class Prova2_XQJ {

	public static void main(String[] args) throws XQException {
		XQDataSource s = new ExistXQDataSource();
		s.setProperty("serverName", "localhost");
		s.setProperty("port", "8080");
		s.setProperty("user", "admin");
		s.setProperty("password", "admin");

		XQConnection conn = s.getConnection();
		String sent = "for $alumne in doc(\"/db/Tema7_AD/clase.xml\")//alumne order by $alumne/cognoms return $alumne/cognoms/text()";

		XQPreparedExpression cons = conn.prepareExpression(sent);
		XQResultSequence rs = cons.executeQuery();

		XQResultItem r_item = null;
		while (rs.next()) {
			r_item = (XQResultItem) rs.getItem();
			System.out.println(r_item.getItemAsString(null));
		}
		conn.close();
	}
}
