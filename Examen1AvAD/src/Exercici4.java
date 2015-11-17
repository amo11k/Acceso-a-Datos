import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Exercici4 {

	public static void main(String[] args) throws ParseException, IOException {
		JSONObject arrel = (JSONObject) (new JSONParser().parse(new FileReader("Finques.json")));
		JSONArray finques = (JSONArray) arrel.get("finques");

		for (int i = 0; i < finques.size(); i++) {
			JSONObject e = (JSONObject) finques.get(i);
			System.out.println(e.get("adreça").toString());
			JSONArray vivendes = (JSONArray) e.get("vivendes");
			for (int j = 0; j < vivendes.size(); j++) {
				e = (JSONObject) vivendes.get(j);
				JSONArray habs = (JSONArray) e.get("noms");
				System.out.println("\tVivenda: " + e.get("pis") + e.get("porta") + ", Hab:" + habs.size());
				if (j == habs.size()){
					System.out.println();
				}
			}

		}

	}

}
