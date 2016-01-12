package exemples;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Prova4 {
	//INTO ARRAYS
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:postgresql://172.16.1.2:5432/r04";

		Connection con = DriverManager.getConnection(url, "r04", "r04");

		PreparedStatement st = con.prepareStatement("INSERT INTO persona4(nif,nom,correus_e) VALUES(?,?,?)");
		st.setString(1, "66666666f");
		st.setString(2, "Ferran");
		String[] correus = { "alu66666666f@ieselcaminas.org", "ferran@gmail.com", "f_66@hotmail.com" };
		Array c = con.createArrayOf("varchar", correus);
		st.setArray(3, c);
		st.executeUpdate();
		con.close();
	}
}
