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
				//Lee objeto
				r = (Ruta) f.readObject();
				//Crea el primer elemento, nombre
				Element emp = doc.createElement("Ruta");
				Element fill = doc.createElement("Nom");
				fill.appendChild(doc.createTextNode(r.getNom()));
				emp.appendChild(fill);
				
				//Crea el primer hijo del primer elemento
				fill = doc.createElement("Desnivell");
				fill.appendChild(doc.createTextNode(Integer.toString(r.getDesnivell())));
				emp.appendChild(fill);
				
				//Segundo Hijo del primer elemento
				fill = doc.createElement("Desnivell_Acumulat");
				fill.appendChild(doc.createTextNode(Integer.toString(r.getDesnivellAcumulat())));
				emp.appendChild(fill);
				
				//Tercer Hijo
				fill = doc.createElement("Punts");
				emp.appendChild(fill);
				
				//Añade los hijos del tercer hijo
				for (int i = 0; i < r.length(); i++) {
					Element punts = doc.createElement("Punt");
					punts.setAttribute("Numero", "" + (i + 1));
					fill.appendChild(punts);
					Element fill2 = doc.createElement("Nom");
					fill2.appendChild(doc.createTextNode(r.getPuntNom(i)));
					punts.appendChild(fill2);
					fill2 = doc.createElement("Longitud");
					fill2.appendChild(doc.createTextNode(Double.toString(r.getPuntLongitud(i))));
					punts.appendChild(fill2);
					fill2 =doc.createElement("Latitud");
					fill2.appendChild(doc.createTextNode(Double.toString(r.getPuntLatitud(i))));
					punts.appendChild(fill2);

				}

				emp.appendChild(fill);

				arrel.appendChild(emp);
			}

		} catch (EOFException eof) {
			f.close();
		}
		Transformer trans = TransformerFactory.newInstance().newTransformer();

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new FileOutputStream("Rutes.xml"));
		
		//Convierte el doc en xml
		trans.transform(source, result);
	}
}
