package exercisi3_6;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Vis_Rutes_JSON_Pantalla extends JFrame implements ActionListener {
	StringBuilder builder;
	JComboBox combo;
	JTextArea area = new JTextArea();
	Document doc;
	JSONObject arrel;
	JSONArray rutes;
	JSONArray punts;

	public void iniciar() throws SAXException, IOException, ParserConfigurationException, ParseException {
		Reader r_json = new FileReader("Rutes.json");
		arrel = (JSONObject) (new JSONParser().parse(r_json));
		rutes = (JSONArray) arrel.get("rutes");

		this.setBounds(100, 100, 450, 300);
		this.setLayout(new BorderLayout());

		JPanel panell1 = new JPanel(new FlowLayout());
		JPanel panell2 = new JPanel(new BorderLayout());
		this.add(panell1, BorderLayout.NORTH);
		this.add(panell2, BorderLayout.CENTER);

		ArrayList<String> llista_rutes = new ArrayList<String>();
		for (int i = 0; i < rutes.size(); i++) {
			JSONObject rutaNom = (JSONObject) rutes.get(i);
			llista_rutes.add((String) rutaNom.get("nom"));
		}

		combo = new JComboBox(llista_rutes.toArray());

		panell1.add(combo);

		panell2.add(new JLabel("LLista de punts de la ruta:"), BorderLayout.NORTH);
		panell2.add(area, BorderLayout.CENTER);

		this.setVisible(true);
		combo.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		builder = new StringBuilder();
		if (e.getSource() == combo) {
			// accions quan s'ha seleccionat un element del combobox, i que han
			// de consistir en omplir el JTextArea
			
			JSONObject ruta = (JSONObject) rutes.get(combo.getSelectedIndex());
			JSONArray punts = (JSONArray) ruta.get("punts");
			for(int i=0;i<punts.size();i++){
				JSONObject punt = (JSONObject) punts.get(i);
				builder.append((String) punt.get("nom")+" ("+punt.get("latitud")+")-("+punt.get("longitud")+")"+"\n");
			}
			area.setText(builder.toString());
		}
	}

	public static void main(String[] args)
			throws SAXException, IOException, ParserConfigurationException, ParseException {
		Vis_Rutes_JSON_Pantalla v = new Vis_Rutes_JSON_Pantalla();
		v.iniciar();
	}

}