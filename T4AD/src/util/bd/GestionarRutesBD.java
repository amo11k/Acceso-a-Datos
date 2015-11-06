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
	Statement state = null;
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
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			// RUTA
			st = con.prepareStatement("SELECT MAX(num_r) FROM RUTA");
			rs = st.executeQuery();
			index = rs.getInt(1);
			System.out.println(index+r.getNom()+r.getDesnivell()+r.getDesnivellAcumulat());
			st = con.prepareStatement("INSERT INTO RUTA VALUES(?,?,?,?)");
			st.setInt(1, index+1);
			st.setString(2, r.getNom());
			st.setDouble(3, r.getDesnivell());
			st.setDouble(4, r.getDesnivellAcumulat());
			st.executeUpdate();
			/*rs = st.executeQuery("INSERT INTO RUTA VALUES(" + (index + 1) + "," + r.getNom() + "," + r.getDesnivell()
					+ "," + r.getDesnivellAcumulat() + ")");*/

			// PUNTS
			llistaPunts = r.getLlistaDePunts();
			
			for (int i = 0; i < llistaPunts.size(); i++) {
				/*rs = st.executeQuery("INSERT INTO PUNTS VALUES(" + (index + 1) + "," + i + "," + r.getPuntNom(i) + ","
						+ r.getPuntLatitud(i) + "," + r.getPuntLongitud(i) + ")");*/
				st = con.prepareStatement("INSERT INTO PUNTS VALUES(?,?,?,?,?)");
				st.setInt(1, index+1);
				st.setInt(2, (i+1));
				st.setString(3, r.getPuntNom(i));
				st.setDouble(4, r.getPuntLatitud(i));
				st.setDouble(5, r.getPuntLongitud(i));
				st.executeUpdate();
			}
			// st.setInt(1, r.);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error closing ResultSet");
			}
		}
	}

	public ArrayList<Ruta> llistat() {
		llista = new ArrayList<>();
		Statement st;
		ResultSet rs = null;
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
		return llista;
	}

	public Ruta buscarRuta(int index) {
		// System.out.println("SELECT * FROM RUTA WHERE num_r=" + index);
		ResultSet rs = null;
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
				llistaPunts.add(new PuntGeo(rs.getString(3), new Coordenades(rs.getDouble(4), rs.getDouble(5))));
			}
			ruta.setLlistaDePunts(llistaPunts);
			

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

	public void guardar(Ruta r){
		
	}
	
	public static void main(String[] args) {
		GestionarRutesBD ges = new GestionarRutesBD();
		ges.llistat();

	}

}
