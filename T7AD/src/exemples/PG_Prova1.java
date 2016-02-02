package exemples;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PG_Prova1 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParserConfigurationException, SAXException, IOException  {
        String url = "jdbc:postgresql://172.16.1.2:5432/r04";
        String usuari = "r04";
        String password = "r04";

        Class.forName("org.postgresql.Driver");

        Connection con = DriverManager.getConnection(url, usuari, password);
        
        ResultSet rs = con.createStatement().executeQuery("SELECT doc FROM P_XML WHERE num=5");

        if (rs.next()) {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(rs.getString(1))));

            Element arrel = (Element) doc.getDocumentElement();
            NodeList llista = arrel.getElementsByTagName("nom");

            for (int i = 0; i < llista.getLength(); i++) {
                Element el = (Element) llista.item(i);
                System.out.println(el.getNodeName() + ' ' + el.getFirstChild().getNodeValue());
            }
        }
        con.close();
    }
}

