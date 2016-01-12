package exemples;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prova3 {
	//LEER FICHEROS DESDE BD
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:postgresql://172.16.1.2:5432/r04";

        Connection con = DriverManager.getConnection(url, "r04", "r04");

        ResultSet rs = con.createStatement().executeQuery(
                "SELECT nom,curriculum FROM persona4 WHERE nom='Eva'");
        while (rs.next()) {
            System.out.println(rs.getString(1));
            BufferedReader br = new BufferedReader(rs.getCharacterStream(2));
            String s = br.readLine();
            while (s != null) {
                System.out.println(s);
                s = br.readLine();
            }
        }
        rs.close();
        con.close();
    }
}