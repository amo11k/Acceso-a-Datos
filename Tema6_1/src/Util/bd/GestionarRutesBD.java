package Util.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionarRutesBD {

	Connection con = null;

	public GestionarRutesBD() throws ClassNotFoundException, SQLException {

		Class.forName("org.sqlite.JDBC");

		String url = "jdbc:sqlite:Rutes.sqlite";
		con = DriverManager.getConnection(url);
	}

	public void close() throws SQLException {
		con.close();
	}

	public void inserir(Ruta r) throws SQLException {

		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		Statement buscaTotal = null;
		String sentenciaSQL = null;
		String sentenciaSQL2 = null;
		String sentenciaSQL3 = null;
		ResultSet t;
		int total;

		buscaTotal = con.createStatement();
		sentenciaSQL3 = "SELECT MAX(num_r) FROM RUTES";
		t = buscaTotal.executeQuery(sentenciaSQL3);
		total = t.getInt(1);

		sentenciaSQL = "INSERT INTO RUTES VALUES (?,?,?,?)";
		sentenciaSQL2 = "INSERT INTO PUNTS VALUES (?,?,?,?,?)";

		statement = con.prepareStatement(sentenciaSQL);
		statement2 = con.prepareStatement(sentenciaSQL2);

		statement.setInt(1, (total+1));
		//statement.setInt(1, (8));
		statement.setString(2, r.getNom());
		statement.setInt(3, r.getDesnivell());
		statement.setInt(4, r.getDesnivellAcumulat());
		statement.executeUpdate();

		for (int i = 0; i < r.length(); i++) {
			statement2.setInt(1, (total+1));
			//statement2.setInt(1, (8));
			statement2.setInt(2, (i + 1));
			statement2.setString(3, r.getPuntNom(i));
			statement2.setDouble(4, r.getPuntLatitud(i));
			statement2.setDouble(5, r.getPuntLongitud(i));
			statement2.executeUpdate();
		}
		
	}
	
	public Ruta buscar(int num_ruta) throws SQLException {
		Ruta r = new Ruta();
		ArrayList<PuntGeo> puntos = new ArrayList<>();
		Statement st;
		Statement st2;
		ResultSet result;
		ResultSet result2;
		String sql;
		String sql2;
		
		st = con.createStatement();
		st2 = con.createStatement();
		sql = "SELECT nom, desnivell, desnivell_acumulat FROM RUTES WHERE num_r =" + num_ruta;
		sql2 = "SELECT nom, latitud, longitud FROM PUNTS WHERE num_r =" + num_ruta;
		
		result = st.executeQuery(sql);
		result2 = st2.executeQuery(sql2);
		
		r.setNom(result.getString(1));
		r.setDesnivell(result.getInt(2));
		r.setDesnivellAcumulat(result.getInt(3));
		
		while (result2.next()) {
			puntos.add(new PuntGeo(result2.getString(1), result2.getDouble(2), result2.getDouble(3)));
		}
		
		r.setLlistaDePunts(puntos);
		
		
		return r;
	}
	
	public ArrayList<Ruta> llistat() throws SQLException {
		ArrayList<Ruta> rs = new ArrayList<>();
		Statement st1;
		Statement st2;
		String sql1;
		String sql2;
		ResultSet result1;
		ResultSet result2;
		
		st1 = con.createStatement();
		st2 = con.createStatement();
		sql1 = "SELECT num_r, nom, desnivell, desnivell_acumulat FROM RUTES";
		result1 = st1.executeQuery(sql1);
		
		while (result1.next()) {
			Ruta r = new Ruta();
			r.setNom(result1.getString(2));
			r.setDesnivell(result1.getInt(3));
			r.setDesnivellAcumulat(result1.getInt(4));
			sql2 = "SELECT nom, latitud, longitud FROM PUNTS WHERE num_r =" + result1.getInt(1);
			result2 = st2.executeQuery(sql2);
			
			while (result2.next()) {
				r.addPunt(new PuntGeo(result2.getString(1), result2.getDouble(2), result2.getDouble(3)));
			}
			
			rs.add(r);
		}
		
		return rs;
	}
	
	public void esborrar(int i) throws SQLException {
		Statement st1;
		Statement st2;
		String sql1;
		String sql2;
		
		st1 = con.createStatement();
		st2 = con.createStatement();
		sql1 = "DELETE FROM RUTES WHERE num_r = " + i;
		sql2 = "DELETE FROM PUNTS WHERE num_r = " + i;
		st1.executeUpdate(sql1);
		st2.executeUpdate(sql2);
	}

	public void guardar(Ruta r) throws SQLException {
		Statement st1;
		Statement st2;
		PreparedStatement pst1;
		PreparedStatement pst2;
		String sql1;
		String sql2;
		String sql3;
		String sql4;
		ResultSet rs1;
		ResultSet rs2;
		
		sql1 = "SELECT num_r, nom FROM RUTES WHERE nom = '" + r.getNom()+ "'";
		st1 = con.createStatement();
		rs1 = st1.executeQuery(sql1);
		
		if ( rs1.next() ) {
			sql2 = "UPDATE RUTES SET desnivell = ?, desnivell_acumulat = ? WHERE num_r = " + rs1.getInt(1);
			pst1 = con.prepareStatement(sql2);
			pst1.setInt(1, r.getDesnivell());
			pst1.setInt(2, r.getDesnivellAcumulat());
			pst1.executeUpdate();
			
			sql3 = "DELETE FROM PUNTS WHERE num_r = " + rs1.getInt(1);
			st2 = con.createStatement();
			st2.executeUpdate(sql3);
			
			sql4 = "INSERT INTO PUNTS VALUES (?,?,?,?,?)";
			pst2 = con.prepareStatement(sql4);
			
			for (int i=0; i<r.length();i++) {
				pst2.setInt(1, rs1.getInt(1));
				pst2.setInt(2, (i+1));
				pst2.setString(3, r.getPuntNom(i));
				pst2.setDouble(4, r.getPuntLatitud(i));
				pst2.setDouble(5, r.getPuntLongitud(i));
				pst2.executeUpdate();
			}
		} else {
			inserir(r);
		}
	}

}
