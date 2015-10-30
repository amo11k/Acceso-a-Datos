package exercicis;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Exercicis.Ruta;

public class PassarRutesObjSQLite {

	public static void main(String[] args) {
		inserirTaulesRutaPunts();
	}

	public static void inserirTaulesRutaPunts() {
		Ruta r;
		Connection con = null;
		PreparedStatement statement = null, statement2 = null;
		Driver driver = null;
		String sentenciaSQL = null, sentenciaSQL2 = null;
		int numero = 1;

		ObjectInputStream f;

		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:Rutes.sqlite";
			con = DriverManager.getConnection(url);
			driver = DriverManager.getDriver(url);
			f = new ObjectInputStream(new FileInputStream("Rutes.obj"));
			sentenciaSQL = "INSERT INTO RUTA VALUES(?,?,?,?)";
			sentenciaSQL2 = "INSERT INTO PUNTS VALUES(?,?,?,?,?)";
			
			while (true) {
				
				r = (Ruta) f.readObject();

				statement = con.prepareStatement(sentenciaSQL);
				statement.setInt(1, numero);
				statement.setString(2, r.getNom());
				statement.setDouble(3, r.getDesnivell());
				statement.setDouble(4, r.getDesnivellAcumulat());
				/*sentenciaSQL = "INSERT INTO RUTA VALUES (" + contador + "," + r.getNom() + "," + r.getDesnivell() + ","
						+ r.getDesnivellAcumulat() + ")";*/
				statement.executeUpdate();
				
				statement2 = con.prepareStatement(sentenciaSQL2);
				for (int i = 0; i < r.length(); i++) {
					/*sentenciaSQL = "INSERT INTO PUNTS VALUES (" + contador + "," + i + "," + r.getPuntNom(i) + ","
							+ r.getPuntLatitud(i) + "," + r.getPuntLongitud(i) + ")";
					statement.executeUpdate(sentenciaSQL);*/
					statement2.setInt(1, numero);
					statement2.setInt(2, (i+1));
					statement2.setString(3, r.getPuntNom(i));
					statement2.setDouble(4, r.getPuntLatitud(i));
					statement2.setDouble(5, r.getPuntLongitud(i));
					statement2.executeUpdate();
				}

				numero++;

			}
		} catch (SQLException ex) {
			System.out.println("Error " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("No s'ha trobat el controlador JDBC (" + ex.getMessage() + ")");
		}catch (EOFException e){
			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if(statement2 !=null){
					statement2.close();
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
