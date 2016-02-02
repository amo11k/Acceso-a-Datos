package exemples;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PG_Prova2 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String url = "jdbc:postgresql://172.16.1.2:5432/r04";
		String usuari = "r04";
		String password = "r04";

		Class.forName("org.postgresql.Driver");

		Connection con = DriverManager.getConnection(url, usuari, password);

		ResultSet rs = con.createStatement().executeQuery("SELECT xpath('//poble[nom_c=\"Plana Alta\"]/nom/text()',doc) FROM P_XML WHERE num=5");

		if (rs.next()) {
			System.out.println(rs.getString(1));
			Array pobles = rs.getArray(1);  //OJO, IMPORT JAVA.SQL.ARRAY
			ResultSet rs1 = pobles.getResultSet();
			while (rs1.next())
				System.out.println(rs1.getString(2)); //SIEMPRE HAY QUE SACAR EN 2 STRING DESPUES DE UN Array.getResulSet();
		}
		con.close();
	}
}