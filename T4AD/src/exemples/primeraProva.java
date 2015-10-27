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
                Class.forName("oracle.jdbc.driver.OracleDriver");

                
                String postgresql="jdbc:postgresql://172.16.1.2:5432/geo_2015",
                	    mySql="jdbc:mysql://172.16.1.2:3306/geo",
                		oracle="jdbc:oracle:thin@172.16.1.2:1521:XE";
                String posUsr="geo_2015",
                		mySqlUsr="geo",
                		oracleUsr="scott";
                String posPass="geo_2015",
                		mySqlPass="geo",
                		oraclePass="tiger";

                Connection con = DriverManager.getConnection(mySql, mySqlUsr, mySqlPass);
                
                Statement st = con.createStatement();
                
                ResultSet rs = st.executeQuery("Select * FROM comarques");
                
                while(rs.next()){
                	System.out.println(rs.getString(1)+" --> "+rs.getString(2));
                }
                rs.close();
                st.close();
                
            } catch (ClassNotFoundException ex) {
                System.out.println("No s'ha trobat el controlador JDBC");
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}