package exercisi3_5;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Writer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import exercici3_2.Ruta;

public class PasarRutaObjJSON {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ObjectInputStream f = new ObjectInputStream(new FileInputStream("Ruta.obj"));
		Ruta r = (Ruta) f.readObject();

		JSONArray rutaDatos = new JSONArray();
		JSONArray puntsArray = new JSONArray();

		JSONObject datos = new JSONObject();
		JSONObject arrel = new JSONObject();
		JSONObject punts = new JSONObject();

		for (int i = 0; i < r.length(); i++) {
			punts.put("num", i + 1);
			punts.put("nom", r.getPuntNom(i));
			punts.put("latitud", r.getPuntLatitud(i));
			punts.put("altidu", r.getPuntLongitud(i));
			puntsArray.add(punts);
		}

		while (true) {
			datos.put("nom", r.getNom());
			datos.put("denivell", r.getDesnivell());
			datos.put("desnivell acumulat", r.getDesnivellAcumulat());
			datos.put("punts", puntsArray);
			rutaDatos.add(datos);

			arrel.put("rutes", rutaDatos);

			Writer w_json = new FileWriter("rutes.json");
			w_json.write(arrel.toJSONString());
			w_json.close();
		}
	}
}
