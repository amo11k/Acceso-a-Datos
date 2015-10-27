package exercicis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreaTaulesRuta4_1 {

	public static void main(String[] args) {
		crearBasesDadesRuta();
	}

	public static void crearBasesDadesRuta() {
		Connection con = null;
		Statement statement = null;
		String sentenciaSQL = null;

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:Rutes.sqlite";
			con = DriverManager.getConnection(url);

			statement = con.createStatement();

			sentenciaSQL = "CREATE TABLE IF NOT EXISTS RUTA(" + "num_r INTEGER CONSTRAINT cp_ruta PRIMARY KEY, "
					+ "nom TEXT, " + "desnivell REAL, " + "Desnivell_Acumulat REAL)";

			statement.executeUpdate(sentenciaSQL);
			
			sentenciaSQL = "CREATE TABLE IF NOT EXISTS PUNTS(" + "num_r INTEGER, " + "num_p INTEGER, " + "nom TEXT, "
					+ "latitud REAL, " + "longitud REAL, " + "CONSTRAINT cp_punt PRIMARY KEY (num_r, num_p))";

			statement.executeUpdate(sentenciaSQL);

		} catch (SQLException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("No s'ha trobat el controlador JDBC (" + ex.getMessage() + ")");
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException ex) {/* llàstima! */
			}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex) {/* llàstima! */
			}
		}
	}
}
