package exercicis;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Array;
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

public class Ruta_XML_PostgreSQL {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParserConfigurationException, SAXException, IOException {
		String url = "jdbc:postgresql://172.16.1.2:5432/r04";
        String usuari = "r04";
        String password = "r04";

        Class.forName("org.postgresql.Driver");

		Connection con = DriverManager.getConnection(url, usuari, password); //CONEXION TEMA4

		FileReader f = new FileReader("Rutes.xml");

		String tot = "";
		int c = f.read();
		while (c != -1) {
			tot += (char) c;
			c = f.read();
		}

		//con.createStatement().executeUpdate("INSERT INTO doc_xml VALUES(1, 'Rutes', XMLPARSE(CONTENT '" + tot + "'))"); //INSERTAR EN XML MODO PROFESIONAL "USA PARSE"

		// Ja està inserit. Anem a comprovar que ha arribat de forma correcta
		ResultSet rs = con.createStatement().executeQuery("SELECT xpath('//ruta[nom=\"Pujada a Penyagolosa\"]/punts/punt/nom/text()',doc) FROM doc_xml WHERE num=1"); //MUESTRA POR PANTALLA

		while (rs.next()) {
			Array llibres = rs.getArray(1); //ESTO DEVUELVE UN ARRAY SQL NO JAVA, OJO. SI LO QUEREMOS TRATAR COMO ARRAY.JAVA TENDREMOS QUE PASARLO
			ResultSet rs1 = llibres.getResultSet();
			while (rs1.next())
				System.out.println(rs1.getString(2));
		}
		f.close();
		con.close();

	}

}
