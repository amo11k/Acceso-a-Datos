package exercisi3_6;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Vis_Rutes_JSON_Pantalla extends JFrame implements ActionListener{
    
    JComboBox combo;
    JTextArea area = new JTextArea();
    Document doc;
    
    public void iniciar() throws SAXException, IOException, ParserConfigurationException{
    	Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("rutesFinal.json");
    	//arrel = (Element) doc.getDocumentElement().getChildNodes();
        
        this.setBounds(100, 100, 450, 300);
        this.setLayout(new BorderLayout());
        
        JPanel panell1 = new JPanel(new FlowLayout());
        JPanel panell2 = new JPanel(new BorderLayout());
        this.add(panell1,BorderLayout.NORTH);
        this.add(panell2,BorderLayout.CENTER);
        
        ArrayList<String> llista_rutes = new ArrayList<String>();
        // sentències per a omplir l'ArrayList amb el nom de les rutes
        
        combo = new JComboBox(llista_rutes.toArray());
        
        panell1.add(combo);
        
        panell2.add(new JLabel("LLista de punts de la ruta:"),BorderLayout.NORTH);
        panell2.add(area,BorderLayout.CENTER);
        
        this.setVisible(true);
        combo.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == combo){
            //accions quan s'ha seleccionat un element del combobox, i que han de consistir en omplir el JTextArea
        }
    }

}