package util.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionarRutesBD {
	Connection con = null;
	
	public GestionarRutesBD() {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:Rutes.sqlite");
			Statement st = con.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS RUTES ("
					+ "num_r INTEGER CONSTRAINT cp_rutes PRIMARY KEY,"
					+ "nom_r TEXT,"
					+ "desnivell INTEGER,"
					+ "desnivell_acumulat INTEGER )";
			st.executeUpdate(sql);
			
			sql = "CREATE TABLE IF NOT EXISTS PUNTS ("
					+ "num_r INTEGER CONSTRAINT ce_punts_rutes REFERENCES RUTES,"
					+ "num_p INTEGER,"
					+ "nom_p TEXT,"
					+ "latitud REAL,"
					+ "longitud REAL,"
					+ "CONSTRAINT cp_punts PRIMARY KEY(num_r,num_p) )";

			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inserir(Ruta r){
		try {
			Statement st_prev = con.createStatement();
			ResultSet rs_prev = st_prev.executeQuery("SELECT * FROM RUTA WHERE nom_r='" + r.getNom() +"'");
			if (rs_prev.next())
				System.out.println("Ja existeix");
			else {
				String sql = "INSERT INTO RUTES VALUES (?,?,?,?)"; 
				PreparedStatement st = con.prepareStatement(sql);
				Statement st_max = con.createStatement();
				ResultSet rs = st_max.executeQuery("SELECT MAX(num_r) FROM RUTA");
				rs.next();
				st.setInt(1, rs.getInt(1)+1);
				st.setString(2, r.getNom());
				st.setInt(3, r.getDesnivell());
				st.setInt(4, r.getDesnivellAcumulat());
				st.executeUpdate();
				PreparedStatement st2 = con.prepareStatement("INSERT INTO PUNTS VALUES (?,?,?,?,?)");
				for (int i=0; i<r.length(); i++){
					
					st2.setInt(1, rs.getInt(1)+1);
					st2.setInt(2, i+1);
					st2.setString(3, r.getPuntNom(i));
					st2.setDouble(4, r.getPuntLatitud(i));
					st2.setDouble(5, r.getPuntLongitud(i));
					st2.executeUpdate();
				}
				st2.close();
				rs.close();
				st.close();
				st_max.close();
			}
			rs_prev.close();
			st_prev.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Ruta buscar(int i){
		Ruta aux = new Ruta();
		try {
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("SELECT * FROM RUTA WHERE num_r=" + i);
			if (rs1.next()){
				aux.setNom(rs1.getString(2));
				aux.setDesnivell(rs1.getInt(3));
				aux.setDesnivellAcumulat(rs1.getInt(4));
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery("SELECT * FROM PUNTS WHERE num_r=" + i);
				while (rs2.next())
					aux.addPunt(new PuntGeo(rs2.getString(3),rs2.getDouble(4),rs2.getDouble(5)));
				rs2.close();
				st2.close();
			}
			rs1.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aux;
	}
	
	public Ruta buscar(String nom){
		Ruta aux = new Ruta();
		try {
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("SELECT * FROM RUTA WHERE nom_r='" + nom + "'");
			if (rs1.next()){
				aux.setNom(rs1.getString(2));
				aux.setDesnivell(rs1.getInt(3));
				aux.setDesnivellAcumulat(rs1.getInt(4));
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery("SELECT * FROM PUNTS WHERE num_r=" + rs1.getInt(1));
				while (rs2.next())
					aux.addPunt(new PuntGeo(rs2.getString(3),rs2.getDouble(4),rs2.getDouble(5)));
				rs2.close();
				st2.close();
			}
			rs1.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aux;
	}

	public ArrayList<Ruta> llistat(){
		ArrayList<Ruta> aux = new ArrayList<Ruta>();
		try {
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("SELECT * FROM RUTA");
			while (rs1.next()){
				Ruta r = new Ruta();
				r.setNom(rs1.getString(2));
				r.setDesnivell(rs1.getInt(3));
				r.setDesnivellAcumulat(rs1.getInt(4));
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery("SELECT * FROM PUNTS WHERE num_r=" + rs1.getInt(1));
				while (rs2.next())
					r.addPunt(new PuntGeo(rs2.getString(3),rs2.getDouble(4),rs2.getDouble(5)));
				aux.add(r);
				rs2.close();
				st2.close();
			}
			rs1.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aux;
	}
	
	public void esborrar(int i){
		
		try {
			Statement st1;
			st1 = con.createStatement();
			st1.executeUpdate("DELETE FROM PUNTS WHERE num_r=" + i);
			st1.executeUpdate("DELETE FROM RUTES WHERE num_r=" + i);
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void esborrar(String nom){
		
		try {
			Statement st_prev = con.createStatement();
			ResultSet rs_prev = st_prev.executeQuery("SELECT num_r FROM RUTA WHERE nom_r='" + nom + "'");
			if (rs_prev.next()) {
				int i = rs_prev.getInt(1);
				rs_prev.close();
				st_prev.close();
				Statement st1;
				st1 = con.createStatement();
				st1.executeUpdate("DELETE FROM PUNTS WHERE num_r=" + i);
				st1.executeUpdate("DELETE FROM RUTES WHERE num_r=" + i);
				st1.close();
			}
			else {
				rs_prev.close();
				st_prev.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void guardar(Ruta r){
		try {
			Statement st_prev = con.createStatement();
			ResultSet rs_prev = st_prev.executeQuery("SELECT * FROM RUTA WHERE nom_r='" + r.getNom() +"'");
			if (rs_prev.next()){
				int n = rs_prev.getInt(1);
				String sql = "UPDATE RUTES SET desnivell=?, desnivell_acumulat=? WHERE num_r=" + n; 
				PreparedStatement st = con.prepareStatement(sql);
				st.setInt(1, r.getDesnivell());
				st.setInt(2, r.getDesnivellAcumulat());
				st.executeUpdate();
				Statement st1 = con.createStatement();
				st1.executeUpdate("DELETE FROM PUNTS WHERE num_r=" + n);
				PreparedStatement st2 = con.prepareStatement("INSERT INTO PUNTS VALUES (?,?,?,?,?)");
				for (int i=0; i<r.length(); i++){
					st2.setInt(1, n);
					st2.setInt(2, i+1);
					st2.setString(3, r.getPuntNom(i));
					st2.setDouble(4, r.getPuntLatitud(i));
					st2.setDouble(5, r.getPuntLongitud(i));
					st2.executeUpdate();
				}
				st2.close();
				
			}
			else {
				String sql = "INSERT INTO RUTES VALUES (?,?,?,?)"; 
				PreparedStatement st = con.prepareStatement(sql);
				Statement st_max = con.createStatement();
				ResultSet rs = st_max.executeQuery("SELECT MAX(num_r) FROM RUTA");
				rs.next();
				st.setInt(1, rs.getInt(1)+1);
				st.setString(2, r.getNom());
				st.setInt(3, r.getDesnivell());
				st.setInt(4, r.getDesnivellAcumulat());
				st.executeUpdate();
				PreparedStatement st2 = con.prepareStatement("INSERT INTO PUNTS VALUES (?,?,?,?,?)");
				for (int i=0; i<r.length(); i++){
					st2.setInt(1, rs.getInt(1)+1);
					st2.setInt(2, i+1);
					st2.setString(3, r.getPuntNom(i));
					st2.setDouble(4, r.getPuntLatitud(i));
					st2.setDouble(5, r.getPuntLongitud(i));
					st2.executeUpdate();
				}
				st2.close();
				rs.close();
				st.close();
				st_max.close();
			}
			rs_prev.close();
			st_prev.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
