package exemples;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Prova2 {
	//GUARDAR FICHEROS EN BD
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:postgresql://172.16.1.2:5432/r04";

        Connection con = DriverManager.getConnection(url, "r04", "r04");

        File f1 = new File("c_eva.txt");
        FileReader in1 = new FileReader(f1);
        File f2 = new File("foto_eva.png"); //XK FILES? XK NECESITO PASARLE EL TAMAÑO CON LOS STREAM.
        FileInputStream in2 = new FileInputStream(f2);

        PreparedStatement st = con.prepareStatement("INSERT INTO persona4 (nif,nom,curriculum,foto) VALUES(?,?,?,?)");
        st.setString(1, "55555555e");
        st.setString(2, "Eva");
        st.setCharacterStream(3, in1, (int) f1.length()); //GRANDARIA, PORQUE ES UNA FOTO POR ESO HAGO LOS Fs.
        st.setBinaryStream(4, in2, (int) f2.length());
        st.executeUpdate();
        in1.close();
        in2.close();
        con.close();
    }
}