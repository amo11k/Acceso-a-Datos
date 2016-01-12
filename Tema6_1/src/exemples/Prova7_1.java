package exemples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prova7_1 {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:postgresql://172.16.1.2:5432/r04";

		Connection con = DriverManager.getConnection(url, "r04", "r04");

		ResultSet rs = con.createStatement().executeQuery("SELECT nom,adreca FROM persona4 ORDER BY nom");
		while (rs.next()) {
			System.out.println("Adreça de " + rs.getString(1) + ": ");
			System.out.println(rs.getObject(2));
		}
	}
}
