package exercicis;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import util.bd.PuntGeo;
import util.bd.Ruta;

public class Rutes_DB4O_pantalla extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	ArrayList<Ruta> llistaFinal = new ArrayList<Ruta>();
	int num_actual;
	double distanciaRuta = 0.00;
	ObjectContainer bd;

	JLabel etiqueta = new JLabel("");
	JLabel etNom = new JLabel("Ruta:");
	JTextField qNom = new JTextField(15);
	JLabel etDesn = new JLabel("Desnivell:");
	JTextField qDesn = new JTextField(5);
	JLabel etDesnAcum = new JLabel("Desnivell acumulat:");
	JTextField qDesnAcum = new JTextField(5);
	JLabel eDistancia = new JLabel("Distancia ruta");
	JTextField qDistancia = new JTextField(5);
	JLabel etPunts = new JLabel("Punts:");
	JTable punts = new JTable(1, 3);
	JButton primer = new JButton(" << ");
	JButton anterior = new JButton(" < ");
	JButton seguent = new JButton(" > ");
	JButton ultim = new JButton(" >> ");

	// en iniciar posem un contenidor per als elements anteriors
	public void iniciar() {
		getContentPane().setLayout(new GridLayout(0, 1));
		JPanel p_prin = new JPanel();
		p_prin.setLayout(new BoxLayout(p_prin, BoxLayout.Y_AXIS));
		// contenidor per als elements
		JPanel panell1 = new JPanel(new GridLayout(0, 2));
		panell1.add(etNom);
		qNom.setEditable(false);
		panell1.add(qNom);
		panell1.add(etDesn);
		qDesn.setEditable(false);
		panell1.add(qDesn);
		panell1.add(etDesnAcum);
		qDesnAcum.setEditable(false);
		panell1.add(qDesnAcum);
		panell1.add(eDistancia);
		qDistancia.setEditable(false);
		panell1.add(qDistancia);
		panell1.add(etPunts);

		JPanel panell2 = new JPanel(new GridLayout(0, 1));
		punts.setEnabled(false);
		JScrollPane scroll = new JScrollPane(punts);
		panell2.add(scroll, null);

		JPanel panell5 = new JPanel(new FlowLayout());
		panell5.add(primer);
		panell5.add(anterior);
		panell5.add(seguent);
		panell5.add(ultim);

		getContentPane().add(p_prin);
		p_prin.add(panell1);
		p_prin.add(panell2);
		p_prin.add(panell5);

		setVisible(true);
		pack();
		primer.addActionListener(this);
		anterior.addActionListener(this);
		seguent.addActionListener(this);
		ultim.addActionListener(this);

		inicialitzar();
		VisRuta();
	}

	private void plenar_taula(ArrayList<PuntGeo> ll_punts) {
		Object[][] ll = new Object[ll_punts.size()][3];
		for (int i = 0; i < ll_punts.size(); i++) {
			ll[i][0] = ll_punts.get(i).getNom();
			ll[i][1] = ll_punts.get(i).getLatitud();
			ll[i][2] = ll_punts.get(i).getLongitud();
		}
		String[] caps = { "Nom punt", "Latitud", "Longitud" };
		punts.setModel(new javax.swing.table.DefaultTableModel(ll, caps));
		bd.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == primer) && (primer.isEnabled())) {
			num_actual = 0;
			qNom.setText(llistaFinal.get(num_actual).getNom());
			qDesn.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivell()));
			qDesnAcum.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivellAcumulat()));
			plenar_taula(llistaFinal.get(num_actual).getLlistaDePunts());
			primer.setEnabled(false);
			anterior.setEnabled(false);
			seguent.setEnabled(true);
			ultim.setEnabled(true);
		} else if (e.getSource() == seguent) {
			num_actual++;
			qNom.setText(llistaFinal.get(num_actual).getNom());
			qDesn.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivell()));
			qDesnAcum.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivellAcumulat()));
			plenar_taula(llistaFinal.get(num_actual).getLlistaDePunts());
			if (num_actual != 0) {
				primer.setEnabled(true);
				anterior.setEnabled(true);
			}if (num_actual == llistaFinal.size()-1){
				seguent.setEnabled(false);
				ultim.setEnabled(false);
			}
		} else if (e.getSource() == anterior) {
			num_actual--;
			qNom.setText(llistaFinal.get(num_actual).getNom());
			qDesn.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivell()));
			qDesnAcum.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivellAcumulat()));
			plenar_taula(llistaFinal.get(num_actual).getLlistaDePunts());
			seguent.setEnabled(true);
			ultim.setEnabled(true);
			if (num_actual == 0) {
				primer.setEnabled(false);
				anterior.setEnabled(false);
			}
		} else if (e.getSource() == ultim) {
			num_actual = llistaFinal.size()-1;
			qNom.setText(llistaFinal.get(num_actual).getNom());
			qDesn.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivell()));
			qDesnAcum.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivellAcumulat()));
			plenar_taula(llistaFinal.get(num_actual).getLlistaDePunts());
			if (num_actual == llistaFinal.size()-1) {
				seguent.setEnabled(false);
				ultim.setEnabled(false);
				anterior.setEnabled(true);
				primer.setEnabled(true);
			}
		}
	}

	private void inicialitzar() {
		bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Rutes.db4o");
		ObjectSet<Ruta> llista = bd.queryByExample(new Ruta());
		for (Ruta e: llista){
			llistaFinal.add(e);
		}
		num_actual = 0;
		qNom.setText(llistaFinal.get(num_actual).getNom());
		qDesn.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivell()));
		qDesnAcum.setText(String.valueOf(llistaFinal.get(num_actual).getDesnivellAcumulat()));
		plenar_taula(llistaFinal.get(num_actual).getLlistaDePunts());
		primer.setEnabled(false);
		anterior.setEnabled(false);
		bd.close();

	}

	private void VisRuta() {

	}
	
	public static double Dist(double lat1, double lon1, double lat2, double lon2) {

		double R = 6378.137; // Radi de la Tierra en km
		double dLat = rad(lat2 - lat1);
		double dLong = rad(lon2 - lon1);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(rad(lat1)) * Math.cos(rad(lat2))
				* Math.sin(dLong / 2) * Math.sin(dLong / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;

		return (double)Math.round(d*1000)/1000; 
	}

	public static double rad(double x) {
		return x * Math.PI / 180;
	}
}