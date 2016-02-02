package exemples;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class PG_Prova3_1 {
 
public static void main(String[] args) throws SQLException, ClassNotFoundException {
String url="jdbc:postgresql://172.16.1.2:5432/r04";
String usuari="r04";
String password="r04";
 
      Class.forName("org.postgresql.Driver");

      Connection con = DriverManager.getConnection(url, usuari, password);

      ResultSet rs = con.createStatement().executeQuery(
    		  "SELECT (xpath('//poble[nom=\"Vistabella del Maestrat\"]/altura/text()',doc))[1] FROM P_XML WHERE num=5"); //CONSULTA PARA SACAR SOLO 1

      if (rs.next()) {
    	  System.out.println(rs.getString(1));
          /*Array poble = rs.getArray(1);
          ResultSet rs1 = poble.getResultSet();
          if (rs1.next())
              System.out.println(rs1.getString(2));*/
      }
      con.close();
  }
}