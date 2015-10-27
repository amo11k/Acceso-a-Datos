package exemples;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class provaConnexio {

    public static void main(String[] args) {
        Connection con = null;
        Driver driver = null;
        String url = "jdbc:postgresql://172.16.1.2:5432/geo_2015";
        String usuari = "geo_2015";
        String password = "geo_2015";

        System.out.println("provaDeConnexio()");

        try {
            // Carreguem el controlador en memòria
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC ("
                    + ex.getMessage() + ")");
            // Si no tenim controlador no podem fer res més. Sortim.
            return;
        }

        try {
            // Obtenim una connexió des de DriverManager
            con = DriverManager.getConnection(url, usuari, password);
            System.out.println("Connexió realitzada utilitzant DriverManager");
            con.close();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }

        try {
            // Obtenim el Driver del controlador des de DriverManager
            driver = DriverManager.getDriver(url);
            // configurem l'usuari i la contrasenya
            Properties properties = new Properties();
            properties.setProperty("user", usuari);
            properties.setProperty("password", password);
            // Obtenim una connexió des de la instància de Driver
            con = driver.connect(url, properties);
            System.out.println("Connexió realitzada utilitzant Driver");
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }

    }

}