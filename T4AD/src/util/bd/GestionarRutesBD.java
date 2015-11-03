package util.bd;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Exercicis.Coordenades;
import Exercicis.PuntGeo;
import Exercicis.Ruta;

public class GestionarRutesBD {
	Ruta ruta;
	Connection con = null;
	Driver driver = null;
	PreparedStatement st = null;
	Statement state = null;
	ResultSet rs = null;
	String url = "jdbc:sqlite:Rutes.sqlite";
	String inserir = "INSERT INTO RUTA VALUES (?,?,?,?)";
	ArrayList<Ruta> llista = null;
	ArrayList<PuntGeo> llistaPunts = null;

	public GestionarRutesBD() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
			System.out.println("Connection Success");
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}

	}

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
		}
	}

	public void inserir(Ruta r) {
		int index;
		try {
			// RUTA
			state = con.createStatement();
			rs = state.executeQuery("SELECT MAX(num_r) FROM RUTA");
			index = rs.getInt(1);
			rs = st.executeQuery("INSER INTO RUTA VALUES(" + (index + 1) + "," + r.getNom() + "," + r.getDesnivell()
					+ "," + r.getDesnivellAcumulat() + ")");

			// PUNTS
			llistaPunts = r.getLlistaDePunts();
			for (int i = 0; i < llistaPunts.size(); i++) {
				rs = st.executeQuery("INSERT INTO PUNTS VALUES(" + (index + 1) + "," + i + "," + r.getPuntNom(i) + ","
						+ r.getPuntLatitud(i) + "," + r.getPuntLongitud(i) + ")");
			}
			// st.setInt(1, r.);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Ruta> llistat() {
		llista = new ArrayList<>();
		Statement st;
		try {
			st = con.createStatement();
			rs = st.executeQuery("Select * FROM RUTA");
			while (rs.next()) {
				llista.add(buscarRuta(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error closin ResultSet");
				e.printStackTrace();
			}
		}
		for (Ruta r: llista){
			r.mostraRuta();
		}
		return llista;
	}

	public Ruta buscarRuta(int index) {
		// System.out.println("SELECT * FROM RUTA WHERE num_r=" + index);
		ruta = new Ruta();
		llistaPunts=new ArrayList<PuntGeo>();
		try {
			state = con.createStatement();
			//LEER RUTA
			rs = state.executeQuery("SELECT * FROM RUTA WHERE num_r=" + index);
			while (rs.next()) {
				ruta.setNom(rs.getString(2));
				ruta.setDesnivell(rs.getInt(3));
				ruta.setDesnivellAcumulat(rs.getInt(4));
			}
			//LEER PUNTOS
			rs = state.executeQuery("SELECT * FROM PUNTS WHERE num_r=" + index);
			int count = 0;
			while(rs.next()){
				llistaPunts.add(new PuntGeo(rs.getString(2), new Coordenades(rs.getDouble(3), rs.getDouble(4))));
			}
			ruta.setLlistaDePunts(llistaPunts);
			ruta.mostraRuta();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error closing ResultSet");
				e.printStackTrace();
			}
		}
		return ruta;

	}

	public void borrarRuta(int index) {
		System.out.println("DROP * FROM RUTA WHERE num_r=" + index);
	}

	public static void main(String[] args) {
		GestionarRutesBD ges = new GestionarRutesBD();
		ges.llistat();

	}

}
