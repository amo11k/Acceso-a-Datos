package exemples;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PG_Prova4 {
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
		String url = "jdbc:postgresql://172.16.1.2:5432/r04";
		String usuari = "r04";
		String password = "r04";

		Class.forName("org.postgresql.Driver");

		Connection con = DriverManager.getConnection(url, usuari, password); //CONEXION TEMA4

		FileReader f = new FileReader("biblioteca.xml");

		String tot = "";
		int c = f.read();
		while (c != -1) {
			tot += (char) c;
			c = f.read();
		}

		con.createStatement().executeUpdate("INSERT INTO P_XML VALUES(7,XMLPARSE(CONTENT '" + tot + "'))"); //INSERTAR EN XML MODO PROFESIONAL "USA PARSE"

		// Ja està inserit. Anem a comprovar que ha arribat de forma correcta
		ResultSet rs = con.createStatement().executeQuery("SELECT xpath('//titol/text()',doc) FROM P_XML WHERE num=7");

		while (rs.next()) {
			Array llibres = rs.getArray(1);
			ResultSet rs1 = llibres.getResultSet();
			while (rs1.next())
				System.out.println(rs1.getString(2));
		}
		f.close();
		con.close();
	}
}
