package exemples;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prova8 {

	public static void main(String[] args) throws SQLException {
		String url = "jdbc:postgresql://172.16.1.2:5432/r04";

		Connection con = DriverManager.getConnection(url, "r04", "r04");

		ResultSet rs = con.createStatement().executeQuery("SELECT nom,telefons FROM persona4 order by nom");
		while (rs.next()) {
			System.out.println("Telèfons de " + rs.getString(1));
			Array tels = rs.getArray(2);
			if (tels != null) {
				ResultSet rs2 = tels.getResultSet();
				while (rs2.next())
					System.out.println(" " + rs2.getString(2));
			} else
				System.out.println(" No en té");
		}
	}
}
