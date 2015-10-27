package exemples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class provaEmpleatSQLite {

	public static void main(String[] args) {
		// ací anirem cridant a les successives proves que farem
		// crearTaulaEmpleat();
		// inserirTaulaEmpleat();
		//modificarTaulaEmpleat();
		//consultarTaulaEmpleat();
		consultarEstadistica();
	}

	public static void crearTaulaEmpleat() {
		Connection con = null;
		Statement statement = null;
		String sentenciaSQL = null;

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:Empleats.sqlite";
			con = DriverManager.getConnection(url);

			statement = con.createStatement();

			sentenciaSQL = "CREATE TABLE EMPLEAT(" + "num INTEGER CONSTRAINT cp_emp PRIMARY KEY, " + "nom TEXT, "
					+ "depart INTEGER, " + "edat INTEGER, " + "sou REAL " + ")";

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

	public static void inserirTaulaEmpleat() {
		Connection con = null;
		Statement statement = null;
		String sentenciaSQL = null;

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:Empleats.sqlite";
			con = DriverManager.getConnection(url);

			statement = con.createStatement();

			sentenciaSQL = "INSERT INTO EMPLEAT VALUES (1,'Andreu',10,32,1000.0)";
			statement.executeUpdate(sentenciaSQL);

			sentenciaSQL = "INSERT INTO EMPLEAT VALUES (2,'Bernat',20,28,1200.0)";
			statement.executeUpdate(sentenciaSQL);

			sentenciaSQL = "INSERT INTO EMPLEAT VALUES (3,'Clàudia',10,26,1100.0)";
			statement.executeUpdate(sentenciaSQL);

			sentenciaSQL = "INSERT INTO EMPLEAT VALUES (4,'Damià',10,40,1500.0)";
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
			} catch (SQLException ex) {
				/* llàstima! */}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex) {
				/* llàstima! */}
		}
	}

	public static void modificarTaulaEmpleat() {
		Connection con = null;
		Statement statement = null;
		String sentenciaSQL = null;

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:Empleats.sqlite";
			con = DriverManager.getConnection(url);

			statement = con.createStatement();

			sentenciaSQL = "UPDATE EMPLEAT SET sou = sou * 1.05";
			statement.executeUpdate(sentenciaSQL);

			sentenciaSQL = "UPDATE EMPLEAT SET depart=20 WHERE num = 3";
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
			} catch (SQLException ex) {
				/* llàstima! */}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex) {
				/* llàstima! */}
		}
	}

	public static void consultarTaulaEmpleat() {
		Connection con = null;
		Statement statement = null;
		String sentenciaSQL = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:Empleats.sqlite";
			con = DriverManager.getConnection(url);

			statement = con.createStatement();

			sentenciaSQL = "SELECT * FROM EMPLEAT WHERE sou > 1100";
			rs = statement.executeQuery(sentenciaSQL);

			System.out.println("Núm. Nom Dep Edat Sou");
			System.out.println("-----------------------------------------");

			while (rs.next()) {
				System.out.print(rs.getInt(1) + " ");
				System.out.print(rs.getString(2) + " ");
				System.out.print(rs.getInt(3) + " ");
				System.out.print(rs.getInt(4) + " ");
				System.out.println(rs.getDouble(5));
			}

		} catch (SQLException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("No s'ha trobat el controlador JDBC (" + ex.getMessage() + ")");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				/* llàstima! */}
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException ex) {
				/* llàstima! */}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex) {
				/* llàstima! */}
		}
	}
	
	public static void consultarEstadistica() {
		Connection con = null;
		Statement statement = null;
		String sentenciaSQL = null;
		ResultSet rs = null;

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:Empleats.sqlite";
			con = DriverManager.getConnection(url);

			statement = con.createStatement();

			sentenciaSQL = "SELECT depart, COUNT(*), MAX(sou), MIN(sou), AVG(sou) FROM EMPLEAT GROUP BY depart";
			rs = statement.executeQuery(sentenciaSQL);

			System.out.println("DEP NUM_EMPLEATS MÁXIM MÍNIM MEDIA");
			System.out.println("-----------------------------------------");

			while (rs.next()) {
				System.out.print(rs.getInt(1) + " - ");
				System.out.print(rs.getInt(2) + " - ");
				System.out.print(rs.getDouble(3) + " - ");
				System.out.print(rs.getDouble(4) + " - ");
				System.out.print(rs.getDouble(5) + "\n");
				//System.out.println(rs.getDouble(5));
			}

		} catch (SQLException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("No s'ha trobat el controlador JDBC (" + ex.getMessage() + ")");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				/* llàstima! */}
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException ex) {
				/* llàstima! */}
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex) {
				/* llàstima! */}
		}
	}

}