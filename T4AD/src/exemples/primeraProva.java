package exemples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class primeraProva {

    public static void main(String[] args) {

            try {
                //Class.forName("org.postgresql.Driver");
                //Class.forName("org.postgresql.Driver");
                Class.forName("oracle.jdbc.driver.OracleDriver"); //SE VA A CARGAR LA CLASE, EN ESTE CASO ORACLE

                
                String postgresql="jdbc:postgresql://172.16.1.2:5432/geo_2015",
                	    mySql="jdbc:mysql://172.16.1.2:3306/geo",
                		oracle="jdbc:oracle:thin@172.16.1.2:1521:XE";
                String posUsr="geo_2015",
                		mySqlUsr="geo",
                		oracleUsr="scott";
                String posPass="geo_2015",
                		mySqlPass="geo",
                		oraclePass="tiger";

                Connection con = DriverManager.getConnection(mySql, mySqlUsr, mySqlPass); //CREAR CONEXION, HAY QUE PASARLE UNA URL, UN USER Y UN PASSWORD
                
                Statement st = con.createStatement(); //Instanciació del Statment a partir d’una connexió activa
                
                ResultSet rs = st.executeQuery("Select * FROM comarques"); //Ejecucion del statement
                
                while(rs.next()){
                	System.out.println(rs.getString(1)+" --> "+rs.getString(2));
                }
                rs.close();
                st.close(); // CIERRE DE STATEMENT
                
            } catch (ClassNotFoundException ex) {
                System.out.println("No s'ha trobat el controlador JDBC");
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}