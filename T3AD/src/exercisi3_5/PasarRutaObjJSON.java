package exercisi3_5;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Writer;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import exercici3_2.Ruta;

public class PasarRutaObjJSON {
	public static void main(String[] args) throws ClassNotFoundException, IOException, EOFException {
		ObjectInputStream f = new ObjectInputStream(new FileInputStream("Ruta.obj"));
		Ruta r;

		JSONArray rutaDatos = new JSONArray();
		JSONArray puntsArray = new JSONArray();
		
		LinkedHashMap datos;
		LinkedHashMap punts;

		try {

			while (true) {
				r = (Ruta) f.readObject();
				datos= new LinkedHashMap();
				datos.put("nom", r.getNom());
				datos.put("denivell", r.getDesnivell());
				datos.put("desnivell acumulat", r.getDesnivellAcumulat());
				datos.put("punts", puntsArray);
				rutaDatos.add(datos);

				for (int i = 0; i < r.length(); i++) {
					punts = new LinkedHashMap();
					punts.put("num", i + 1);
					punts.put("nom", r.getPuntNom(i));
					punts.put("latitud", r.getPuntLatitud(i));
					punts.put("longitud", r.getPuntLongitud(i));
					puntsArray.add(punts);
				}
				
			}
		} catch (EOFException e) {
			f.close();
		}
		f.close();
		JSONObject arrel = new JSONObject();
		arrel.put("rutes", rutaDatos);
		Writer w_json = new FileWriter("rutesFinal.json");
		w_json.write(arrel.toJSONString());
		w_json.close();
	}
}
