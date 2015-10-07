package exemples;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CrearEmpleatsXML {
	public static void main(String[] args)
			throws IOException, ClassNotFoundException, ParserConfigurationException, TransformerException {
		ObjectInputStream f = new ObjectInputStream(new FileInputStream("Empleats.obj"));

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element arrel = doc.createElement("empleats");
		doc.appendChild(arrel);

		Empleat e;
		try {
			while (true) {
				e = (Empleat) f.readObject();
				Element emp = doc.createElement("empleat");
				emp.setAttribute("numero", Integer.toString(e.getNum()));

				Element fill = doc.createElement("nom");
				fill.appendChild(doc.createTextNode(e.getNom()));
				emp.appendChild(fill);

				fill = doc.createElement("departament");
				fill.appendChild(doc.createTextNode(Integer.toString(e.getDepartament())));
				emp.appendChild(fill);

				fill = doc.createElement("edat");
				fill.appendChild(doc.createTextNode(Integer.toString(e.getEdat())));
				emp.appendChild(fill);

				fill = doc.createElement("sou");
				fill.appendChild(doc.createTextNode(Double.toString(e.getSou())));
				emp.appendChild(fill);

				arrel.appendChild(emp);
			}

		} catch (EOFException eof) {
			f.close();
		}
		Transformer trans = TransformerFactory.newInstance().newTransformer();

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new FileOutputStream("Empleats.xml"));

		trans.transform(source, result);
	}
}