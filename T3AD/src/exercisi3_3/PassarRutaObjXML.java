package exercisi3_3;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import exemples.Empleat;
import exercici3_2.Ruta;

public class PassarRutaObjXML {

	public static void main(String[] args) throws ParserConfigurationException, FileNotFoundException, IOException,
			ClassNotFoundException, TransformerFactoryConfigurationError, TransformerException {
		ObjectInputStream f = new ObjectInputStream(new FileInputStream("Ruta.obj"));

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element arrel = doc.createElement("rutes");
		doc.appendChild(arrel);

		Ruta r;
		try {
			while (true) {
				r = (Ruta) f.readObject();
				Element emp = doc.createElement("Ruta");
				emp.setAttribute("Nom", r.getNom());

				Element fill = doc.createElement("Desnivell");
				fill.appendChild(doc.createTextNode(Integer.toString(r.getDesnivell())));
				emp.appendChild(fill);

				fill = doc.createElement("Desnivell_Acumulat");
				fill.appendChild(doc.createTextNode(Integer.toString(r.getDesnivellAcumulat())));
				emp.appendChild(fill);

				fill = doc.createElement("Punts");
				emp.appendChild(fill);
				
				for(int i=0;i<r.length();i++){
					Element punts = doc.createElement("Punt_"+(i+1));
					punts.setAttribute("Numero", ""+i);
					fill.appendChild(punts);
					punts=doc.createElement("Nom");
					punts.appendChild(doc.createTextNode(r.getPuntNom(i)));
					fill.appendChild(punts);
					
				}
				
				//fill = doc.createElement("sou");
				
				emp.appendChild(fill);

				arrel.appendChild(emp);
			}

		} catch (EOFException eof) {
			f.close();
		}
		Transformer trans = TransformerFactory.newInstance().newTransformer();

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new FileOutputStream("Rutes.xml"));

		trans.transform(source, result);
	}
}
