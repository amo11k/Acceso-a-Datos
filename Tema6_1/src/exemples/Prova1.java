package exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prova1 {
	//CONECTAR BD
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:postgresql://172.16.1.2:5432/r04";

        Connection con = DriverManager.getConnection(url, "r04", "r04");

        ResultSet rs = con.createStatement().executeQuery("select nom,major_edat from persona4 order by nom");

        while (rs.next()) {
            if (rs.getBoolean(2))
                System.out.println(rs.getString(1) + " és major d'edat");
            else
                System.out.println(rs.getString(1) + " és menor d'edat");
        }
        rs.close();
        con.close();
    }
}