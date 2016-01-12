package exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prova6 {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://172.16.1.2:5432/r04";

        Connection con = DriverManager.getConnection(url, "r04", "r04");

        ResultSet rs = con.createStatement().executeQuery("SELECT nom,correus_e[1] FROM persona4 ORDER BY nom");
        while (rs.next()) {
            System.out.print("Primer correu de " + rs.getString(1) + ": ");
            if (rs.getString(2)!= null)
            	System.out.println(rs.getObject(2));
            else
            	System.out.println("No te correu");
            	
        }
        rs.close();
        con.close();
    }
}