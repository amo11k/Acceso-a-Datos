package programesT5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import exemples.SessionFactoryUtil;
import geo.Comarques;
import geo.Poblacions;

public class Pantalla_Veure_Pobles_Comarca extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel etiqueta = new JLabel("Comarca:");
	JLabel et_ini = new JLabel("Introdueix la comarca:");
	JTextField com = new JTextField(15);
	JButton consultar = new JButton("Consultar");
	JTextArea area = new JTextArea();

	// en iniciar posem un contenidor per als elements anteriors
	public void iniciar() {
		this.setBounds(100, 100, 450, 300);
		this.setLayout(new BorderLayout());
		// contenidor per als elements
		JPanel panell1 = new JPanel(new FlowLayout());
		panell1.add(et_ini);
		panell1.add(com);
		panell1.add(consultar);
		getContentPane().add(panell1, BorderLayout.NORTH);

		JPanel panell2 = new JPanel(new BorderLayout());
		panell2.add(etiqueta, BorderLayout.NORTH);
		area.setForeground(Color.blue);
		JScrollPane scroll = new JScrollPane(area);
		panell2.add(scroll, BorderLayout.CENTER);
		getContentPane().add(panell2, BorderLayout.CENTER);

		setVisible(true);
		consultar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == consultar) {
			etiqueta.setText("Comarca: " + com.getText());
			visualitzaCom(com.getText());
		}
	}

	private void visualitzaCom(String comarca) {

		// Instruccions per a llegir la comarca introduïda (s'ha de deixar en un
		// objecte Comarques).
		// S'ha de cuidar que si no exiteix la comarca, en el JTextArea es pose
		// que no existeix.
		// La manera d'anar introduint informació en el JTextArea és
		// area.append("Linia que es vol introduir ")

		SessionFactory sfact = SessionFactoryUtil.getSessionFactory();
		Session sessio = sfact.openSession();
		Comarques com = (Comarques) sessio.get(Comarques.class, comarca);
		if (com != null){
			area.setText("La Comarca " + com.getNomC() + " te "+ com.getPoblacionses().size() + " pobles\n");
			/*area.setText(area.getText() + "\n" + com.getProvincia());
			area.setText(area.getText() + "\n" + " (" + com.getPoblacionses().size() + " pobles)\n");*/
			area.append("\n" + "Llista de pobles");
			area.append("\n" + "-----------------------------");

			for (Poblacions p : com.getPoblacionses())
				area.append("\n" + p.getNom());

			sessio.close();
		}else{
			area.setText("Busqueda no encontrada");
			etiqueta.setText("Comarca no valdia");
			sessio.close();
		}
			
		
	}

	public static void main(String[] args) {
		Pantalla_Veure_Pobles_Comarca p = new Pantalla_Veure_Pobles_Comarca();
		p.iniciar();
	}
}