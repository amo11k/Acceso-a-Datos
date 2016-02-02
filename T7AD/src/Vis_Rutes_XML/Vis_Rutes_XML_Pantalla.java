package Vis_Rutes_XML;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Vis_Rutes_XML_Pantalla extends JFrame {

	private JPanel contentPane;
	private JTextField textViewRuta;
	private Connection con = null;
	private String url = "jdbc:postgresql://172.16.1.2:5432/r04";
    private String usuari = "r04";
    private String password = "r04";
    private ArrayList<String> rutes;
    private final JComboBox comboBox;
    private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vis_Rutes_XML_Pantalla frame = new Vis_Rutes_XML_Pantalla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public Vis_Rutes_XML_Pantalla() throws SQLException, ClassNotFoundException, FileNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		connect();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIntrodueixLaRuta = new JLabel("Introdueix la ruta:");
		lblIntrodueixLaRuta.setBounds(41, 12, 143, 15);
		contentPane.add(lblIntrodueixLaRuta);
		
		comboBox = new JComboBox(getRutes().toArray());
		comboBox.setBounds(192, 7, 212, 24);
		contentPane.add(comboBox);
		
		JLabel lblRuta = new JLabel("Ruta:");
		lblRuta.setBounds(12, 51, 47, 15);
		contentPane.add(lblRuta);
		
		textViewRuta = new JTextField();
		textViewRuta.setEditable(false);
		textViewRuta.setBounds(65, 49, 190, 19);
		contentPane.add(textViewRuta);
		textViewRuta.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(22, 75, 414, 184);
		contentPane.add(textArea);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textViewRuta.setText(comboBox.getSelectedItem().toString());
				try {
					getPunts();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void connect() throws ClassNotFoundException, SQLException, FileNotFoundException{
        Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection(url, usuari, password); //CONECTAR CON LA BASE DE DATOS
	}
	
	private ArrayList<String> getRutes() throws SQLException{
		rutes = new ArrayList<>();
		ResultSet rs = con.createStatement().executeQuery("SELECT xpath('//ruta/nom/text()',doc) FROM doc_xml WHERE num=1"); //MUESTRA POR PANTALLA

		while (rs.next()) {
			Array llibres = rs.getArray(1);
			ResultSet rs1 = llibres.getResultSet();
			while (rs1.next())
				rutes.add((rs1.getString(2)));
		}
		return rutes;
	}
	
	private void getPunts() throws SQLException{
		ResultSet rs = con.createStatement().executeQuery("SELECT xpath('//ruta[nom=\""+comboBox.getSelectedItem()+"\"]/punts/punt/nom/text()',doc) FROM doc_xml WHERE num=1"); //MUESTRA POR PANTALLA
		StringBuilder sbuilder = new StringBuilder();
		while (rs.next()) {
			Array llibres = rs.getArray(1);
			ResultSet rs1 = llibres.getResultSet();
			while (rs1.next())
				sbuilder.append(rs1.getString(2)+"\n");
		}
		textArea.setText(sbuilder.toString());
	}
	
	@Override
	public void setDefaultCloseOperation(int operation) {
		super.setDefaultCloseOperation(operation);
		try {
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
