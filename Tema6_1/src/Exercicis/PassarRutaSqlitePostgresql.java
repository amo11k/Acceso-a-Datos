package Exercicis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Util.bd.GestionarRutesBD;
import Util.bd.Ruta;

public class PassarRutaSqlitePostgresql {
	private static GestionarRutesBD gestioRutas;
	private static ArrayList<Ruta> llistat = new ArrayList<Ruta>();
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		String url = "jdbc:postgresql://172.16.1.2:5432/r04";

		Connection con = DriverManager.getConnection(url, "r04", "r04");
		
		int contador = 1;
		
		String select = "SELECT MAX(num_r) FROM RUTA";
		Statement st1 = con.createStatement();
		ResultSet result = st1.executeQuery(select);
		if (result.next()) {
			contador = result.getInt(1);
		}
		
		
		gestioRutas = new GestionarRutesBD();
		llistat = gestioRutas.llistat();
		
		for (Ruta r : llistat) {
			String s = "INSERT INTO RUTA(num_r, nom_r, desn, desn_acum, llista_punts) VALUES (" + contador + "," + cadenaRuta(r) + ")";
			System.out.println(s);
			Statement st = con.createStatement();
			st.executeUpdate(s);
			contador ++;
		}
		con.close();
	}
	
	public static String cadenaRuta(Ruta r){
		String aux = "'" + r.getNom() + "'," + r.getDesnivell() + "," + r.getDesnivellAcumulat() + ",";
		if (r.length()>0){
			aux += "CAST(ARRAY[";
			for (int i=0;i<r.length();i++){
				aux += "(" + (i+1) + ",'" + r.getPuntNom(i) + "',";
				aux += "(" + r.getPuntLatitud(i) + "," + r.getPuntLongitud(i) + ")";
				aux += "),";
			}
			aux=aux.substring(0, aux.length()-1);
			aux += "] AS punt ARRAY)";
		}
		return aux;
	}



}
