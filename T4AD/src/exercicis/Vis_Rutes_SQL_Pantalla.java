package exercicis;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Vis_Rutes_SQL_Pantalla extends JFrame implements ActionListener {
	JComboBox combo;
	JTextArea area = new JTextArea();
	StringBuilder sBuild;
	ArrayList<String> llista_rutes = new ArrayList<String>();
	Connection con = null;
	Driver driver = null;
	ResultSet rs;

	public void iniciar() throws SAXException, IOException, ParserConfigurationException {
		String url = "jdbc:sqlite:Rutes.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			rs = st.executeQuery("Select nom FROM RUTA");
			while (rs.next()) {
				llista_rutes.add(rs.getString(1));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.setBounds(100, 100, 450, 300);
		this.setLayout(new BorderLayout());

		JPanel panell1 = new JPanel(new FlowLayout());
		JPanel panell2 = new JPanel(new BorderLayout());
		this.add(panell1, BorderLayout.NORTH);
		this.add(panell2, BorderLayout.CENTER);

		combo = new JComboBox(llista_rutes.toArray());

		panell1.add(combo);
		JButton eixir = new JButton("Eixir");
		eixir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		panell1.add(eixir);
		panell2.add(new JLabel("LLista de punts de la ruta:"), BorderLayout.NORTH);
		panell2.add(area, BorderLayout.CENTER);

		this.setVisible(true);
		combo.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == combo) {
			sBuild = new StringBuilder();
			Statement st;
			try {
				st = con.createStatement();
				rs = st.executeQuery("Select * FROM PUNTS WHERE num_r=" + (combo.getSelectedIndex() + 1));
				while (rs.next()) {
					sBuild.append("Num Ruta: " + rs.getString(1) + ", Punt: " + rs.getString(2) + "   "
							+ rs.getString(3) + "  (" + rs.getString(4) + " - " + rs.getString(5) + ")\n");
				}
				area.setText(sBuild.toString());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		Vis_Rutes_SQL_Pantalla v = new Vis_Rutes_SQL_Pantalla();
		v.iniciar();
	}

}