package exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prova7_2 {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://172.16.1.2:5432/r00";

        Connection con = DriverManager.getConnection(url, "r00", "r00");

        ResultSet rs = con.createStatement().executeQuery("SELECT nom,adreca FROM persona4 ORDER BY nom");
        while (rs.next()) {
            System.out.println("Adreça de " + rs.getString(1) + ": ");
            Adreca adr = (Adreca) rs.getObject(2);
            System.out.println(adr.carrer + ". " + adr.codipostal + " (" +adr.poblacio + ")");
        }
        rs.close();
        con.close();
    }
}