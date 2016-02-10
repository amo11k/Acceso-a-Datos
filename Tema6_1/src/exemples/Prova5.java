package exemples;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prova5 {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://172.16.1.2:5432/r04";

        Connection con = DriverManager.getConnection(url, "r04", "r04");

        ResultSet rs = con.createStatement().executeQuery("SELECT nom,correus_e FROM persona4 WHERE nom='Ferran'");
        while (rs.next()) {
            System.out.println("Correus de " + rs.getString(1));
            String[] correus = (String[]) rs.getArray(2).getArray(); //SACAR ARRAYS ojojojojo
            for (String c : correus) {
                System.out.println(c);
            }
        }
        rs.close();
        con.close();
    }
}