package exemples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

public class MetaData {

	public static void main(String[] args)
			throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://172.16.1.2:5432/geo_2015", "geo_2015",
				"geo_2015");

		DatabaseMetaData dbmd = con.getMetaData();
		System.out.println("Informació general");
		System.out.println("------------------");
		System.out.println("SGBD: " + dbmd.getDatabaseProductName());
		System.out.println("Driver: " + dbmd.getDriverName());
		System.out.println("URL: " + dbmd.getURL());
		System.out.println("Usuari: " + dbmd.getUserName());
		System.out.println();
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
		System.out.println("Introdueix un número per veure l'estructura de la taula (0 per acabar): ");
		BufferedReader ent = new BufferedReader(new InputStreamReader(System.in));
		int opcio = Integer.parseInt(ent.readLine());

		while (opcio != 0) {
			if (opcio < compt) {
				ResultSet rs = dbmd.getColumns(null, "public", taules.get(opcio - 1), null);
				System.out.println("Estructura de la taula " + taules.get(opcio - 1));
				System.out.println("----------------------------");
				while (rs.next())
					System.out.println(rs.getString(4) + " (" + rs.getString(6) + ")");
				System.out.println("----------------------------");

				rs = dbmd.getPrimaryKeys(null, "public", taules.get(opcio - 1));
				System.out.print("Clau principal: ");
				while (rs.next())
					System.out.print(rs.getString(4) + " ");
				System.out.println();

				rs = dbmd.getImportedKeys(null, "public", taules.get(opcio - 1));
				System.out.println("Claus externes: ");
				while (rs.next()) {
					System.out.println(rs.getString(8) + " apunta a " + rs.getString(3));
				}

			}
			System.out.println();
			System.out.println("Introdueix un número per veure l'estructura de la taula (0 per acabar): ");
			opcio = Integer.parseInt(ent.readLine());
		}
	}
}
