package exemples;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.postgresql.util.PGobject;

public class Prova9_Pantalla extends JFrame{
    JTextField nif = new JTextField(9);
    JTextField nom = new JTextField(9);
    JCheckBox major = new JCheckBox();
    JLabel foto = null;
    JTextArea curric = new JTextArea();
    JTextField adreca = new JTextField(20);
    JTextArea correus = new JTextArea();
    JTextArea telefons = new JTextArea();
    
    JLabel et_nif = new JLabel("Nif");
    JLabel et_nom = new JLabel("Nom");
    JLabel et_major = new JLabel("Major d'edat");
    JLabel et_adr = new JLabel("Adreça");
    JLabel et_correus = new JLabel("Correus");
    JLabel et_telefons = new JLabel("Telèfons");

    JPanel pan1 = new JPanel(new GridLayout(1,2));
    JPanel pan1_1 = new JPanel(new GridLayout(3,1));
    JPanel pan1_1_1 = new JPanel(new FlowLayout());
    JPanel pan1_1_2 = new JPanel(new FlowLayout());
    JPanel pan1_1_3 = new JPanel(new FlowLayout());
    JPanel pan1_2 = new JPanel(new FlowLayout());
    JPanel pan2 = new JPanel(new BorderLayout());
    JPanel pan2_1 = new JPanel(new FlowLayout());
    JPanel pan2_2 = new JPanel(new GridLayout(2,2));
    JPanel pan3 = new JPanel(new BorderLayout());
    JPanel pan4 = new JPanel();
    
    

    public void iniciar() throws SQLException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.setLayout(new GridLayout(3,1));
        this.setBounds(100, 100, 300, 300);
        
        this.getContentPane().add(pan1);
        this.getContentPane().add(pan2);
        this.getContentPane().add(pan3);
        
        pan1.add(pan1_1);
        pan1.add(pan1_2);
        pan1_1.add(pan1_1_1);
        pan1_1.add(pan1_1_2);
        pan1_1.add(pan1_1_3);
        pan1_1_1.add(et_nif);
        pan1_1_1.add(nif);
        pan1_1_2.add(et_nom);
        pan1_1_2.add(nom);
        pan1_1_3.add(et_major);
        pan1_1_3.add(major);
        
        pan2.add(pan2_1,BorderLayout.NORTH);
        pan2.add(pan2_2,BorderLayout.CENTER);
        pan2_1.add(et_adr);
        pan2_1.add(adreca);
        pan2_2.add(et_correus);
        pan2_2.add(et_telefons);
        pan2_2.add(correus);
        pan2_2.add(telefons);
        
        pan3.add(curric);
        
        String url = "jdbc:postgresql://172.16.1.2:5432/r04";

        Connection con = DriverManager.getConnection(url, "r04", "r04");

        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM persona4 WHERE nom='Eva'");
        
        if (rs.next()){
            nif.setText(rs.getString(1));
            nom.setText(rs.getString(2));
            major.setSelected(rs.getBoolean(3));
            if (rs.getBinaryStream(4)!=null){
                Image img = ImageIO.read(rs.getBinaryStream(4));
                foto = new JLabel(new ImageIcon(img));
                pan1_2.add(foto);
            }
            if (rs.getCharacterStream(5)!=null){
                BufferedReader bf = new BufferedReader(rs.getCharacterStream(5));
                String s;
                String tot="";
                while ((s = bf.readLine()) != null) {
                    tot += s + "\n";
                }
                curric.setText(tot);
            }
            if (rs.getObject(6)!=null){
                adreca.setText(rs.getObject(6).toString());
            }
                
            if (rs.getArray(7)!=null){
                String[] corr = (String[]) rs.getArray(7).getArray();
                for (String c : corr)
                correus.append(c+"\n");
            }
            if (rs.getArray(8)!=null){
                ResultSet tels = rs.getArray(8).getResultSet();
                while (tels.next())
                    telefons.append(tels.getString(2)+"\n");
            }    
        }
        
        rs.close();
        con.close();
        this.setVisible(true);
    }
    
    public static void main(String[] args) throws SQLException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Prova9_Pantalla finestra = new Prova9_Pantalla();
        finestra.iniciar();
    }

}