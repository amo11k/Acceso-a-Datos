package exemples;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MirarXML {

	public static void main(String[] args)
			throws ParserConfigurationException, FileNotFoundException, IOException, SAXException {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("cotxes.xml");
		Element arrel = doc.getDocumentElement(); // apuntarà a l'element arrel
		NodeList fills = arrel.getChildNodes();
		System.out.println(fills.item(0).getNodeName()); // el primer fill és el
															// retorn de carro.
		System.out.println(fills.item(1).getNodeName()); // el segon fill sí que
															// és vehiculo
		System.out.println(fills.item(2).getNodeName()); // el tercer fill és el
															// retorn de carro.
		System.out.println(fills.item(3).getNodeName()); // el quart fill sí que
															// és vehiculo
		System.out.println(fills.item(4).getNodeName()); // el cinquè fill és el
															// retorn de carro.
		System.out.println(fills.item(5).getNodeName()); // no existeix el sisè
															// fill. Donarà
															// error
	}

}