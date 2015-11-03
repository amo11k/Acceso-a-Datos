package exemples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultSetMetaData {
	public static void main(String[] args)
			throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://172.16.1.2:5432/geo_2015", "geo_2015",
				"geo_2015");

		DatabaseMetaData dbmd = con.getMetaData();
		System.out.println("Llistat de taules:");
		System.out.println("Número \tCatàleg \tEsquema \tNom \tTipus");
		System.out.println("---------------------------------------------");
		ResultSet ll = dbmd.getTables(null, "public", null, null);
		int compt = 1;
		ArrayList<String> taules = new ArrayList<String>();
		while (ll.next()) {
			System.out.println((compt++) + "\t" + ll.getString(1) + "\t" + ll.getString(2) + "\t" + ll.getString(3)
					+ "\t" + ll.getString(4));
			taules.add(ll.getString(3));
		}
		System.out.println();
		System.out.println("Introdueix un número per veure el contingut de la taula (0 per acabar): ");
		BufferedReader ent = new BufferedReader(new InputStreamReader(System.in));
		int opcio = Integer.parseInt(ent.readLine());

		while (opcio != 0) {
			if (opcio < compt) {
				ResultSet rs = con.createStatement().executeQuery("SELECT * FROM " + taules.get(opcio - 1));
				System.out.println("Contingut de la taula " + taules.get(opcio - 1));
				System.out.println("----------------------------");

				java.sql.ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
					System.out.print(rsmd.getColumnName(i) + "\t");
				System.out.println();
				System.out.println("------------------------------------------");

				while (rs.next()) {
					for (int i = 1; i <= rsmd.getColumnCount(); i++)
						System.out.print(rs.getString(i) + "\t");
					System.out.println();
				}
			}
			System.out.println();
			System.out.println("Introdueix un número per veure el contingut de la taula (0 per acabar): ");
			opcio = Integer.parseInt(ent.readLine());
		}
	}
}
