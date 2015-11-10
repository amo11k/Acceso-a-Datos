package exemples;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class JsonToXML {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException,
			ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		JSONObject arrel = (JSONObject) new JSONParser().parse(new FileReader("vehicles.json"));
		JSONObject oferta = (JSONObject) arrel.get("oferta");
		JSONArray vehicles = (JSONArray) oferta.get("vehiculo");

		Document arrelXML = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element ofertaXML = arrelXML.createElement("oferta");
		arrelXML.appendChild(ofertaXML);

		for (int i = 0; i < vehicles.size(); i++) {
			Element vehiclePartXML = arrelXML.createElement("vehicle");
			ofertaXML.appendChild(vehiclePartXML);
			JSONObject vehicle = (JSONObject) vehicles.get(i);
			vehicle.get("marca");
			Element fill = arrelXML.createElement("marca");
			vehiclePartXML.appendChild(fill);
			fill.appendChild(arrelXML.createTextNode(vehicles.get(i).toString()));
			vehicle.get("marca");
		}
		
		Transformer trans = TransformerFactory.newInstance().newTransformer();

		DOMSource source = new DOMSource(arrelXML);
		StreamResult result = new StreamResult("vehicles.xml");

		trans.transform(source, result);

	}

}
