import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Exercici2 {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("cataleg.xml");
		Element arrel = (Element) doc.getChildNodes().item(0);
		NodeList productes = arrel.getElementsByTagName("producte");
		NodeList carac = arrel.getElementsByTagName("caracteristiques");

		for (int i = 0; i < productes.getLength(); i++) {
			Element el = (Element) productes.item(i);
			System.out.println("---------------" + el.getAttribute("id") + "---------------");
			System.out.println(
					"Producte: " + el.getElementsByTagName("nom").item(0).getChildNodes().item(0).getNodeValue());
			System.out
					.println("Preu: " + el.getElementsByTagName("preu").item(0).getChildNodes().item(0).getNodeValue());
			System.out.println("Caracteristicas:");
			Element e = (Element) carac.item(i);
			NodeList prueba = e.getElementsByTagName("descripcio_caract");
			for (int j = 0; j < prueba.getLength(); j++) {
				System.out.println("	"
						+ el.getElementsByTagName("descripcio_caract").item(j).getChildNodes().item(0).getNodeValue()
						+ ": "
						+ el.getElementsByTagName("valor_caract").item(j).getChildNodes().item(0).getNodeValue());
			}
		}

	}

}
