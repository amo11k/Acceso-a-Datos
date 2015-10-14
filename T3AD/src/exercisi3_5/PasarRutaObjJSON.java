package exercisi3_5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import exercici3_2.Ruta;

public class PasarRutaObjJSON {
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		ObjectInputStream f = new ObjectInputStream(new FileInputStream("Ruta.obj"));
		Ruta r = (Ruta) f.readObject();
		
		JSONArray rutes = (JSONArray) ((JSONObject) ((JSONArray ) new JSONParser().parse(f)).get(0)).get("ocupacion");
		
	}
}
