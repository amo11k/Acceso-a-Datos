package ejemplos;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;

import net.xqj.exist.ExistXQDataSource;

public class Prova4_XQJ {

    public static void main(String[] args) throws XQException {
        XQDataSource s = new ExistXQDataSource();
        s.setProperty("serverName", "localhost");
        s.setProperty("port", "8080");
        s.setProperty("user", "admin");
        s.setProperty("password", "admin");

        XQConnection conn = s.getConnection();
        String sent = "doc(\"/db/Tema7_AD/clase.xml\")//alumne/cognoms/text()";  //COGE PARENTESIS
        String sentSinClaudators = "doc(\"/db/Tema7_AD/clase.xml\")//alumne/cognoms/xs:string(text())"; //COGE SIN PARENTESIS

        XQResultSequence rs = conn.createExpression().executeQuery(sentSinClaudators); //TODO EN 1

        while (rs.next())
            System.out.println(rs.getItemAsString(null));

        conn.close();
    }
}