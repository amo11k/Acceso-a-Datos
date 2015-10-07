package exemples;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MirarXmlCotxes {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new FileInputStream("cotxes.xml"));
		Element arrel = (Element) doc.getChildNodes().item(0);
		NodeList llista = arrel.getElementsByTagName("vehiculo");

		for (int i = 0; i < llista.getLength(); i++) {
			Element el = (Element) llista.item(i);
			System.out.println(el.getNodeName() + " " + (i + 1));
			System.out.println(
					"Marca: " + el.getElementsByTagName("marca").item(0).getChildNodes().item(0).getNodeValue());
			System.out.println(
					"Matrícula: " + el.getElementsByTagName("matricula").item(0).getFirstChild().getNodeValue());
			System.out.println(
					"Combustible: " + el.getElementsByTagName("motor").item(0).getAttributes().item(0).getNodeValue());
			Element m = (Element) el.getElementsByTagName("motor").item(0);
			System.out.println("Combustible: " + m.getAttribute("combustible"));
			System.out.println();
		}
	}
}
