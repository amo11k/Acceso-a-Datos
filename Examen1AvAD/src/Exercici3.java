import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Exercici3 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ObjectInputStream f = new ObjectInputStream(new FileInputStream("Finques.obj"));
		JSONObject arrel = new JSONObject();
		JSONArray fIxida = new JSONArray();
		arrel.put("finques", fIxida);

		try {
			while (true) {
				Finca finca = (Finca) f.readObject();
				JSONObject fincaEixida = new JSONObject();
				fIxida.add(fincaEixida);
				fincaEixida.put("adreça", finca.getAdreça());
				JSONArray vivendesEixida = new JSONArray();
				fIxida.add(vivendesEixida);
				for (Vivenda v : finca.getVivendes()) {
					JSONObject vEixida = new JSONObject();
					vivendesEixida.add(vEixida);
					vEixida.put("pis", v.getPis());
					vEixida.put("porta", v.getPorta());
					JSONArray nomsEixida = new JSONArray();
					vEixida.put("noms", nomsEixida);
					for (String nom : v.getNoms()) {
						nomsEixida.add(nom);
					}
				}

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("Finish");
		} finally {
			f.close();
			FileWriter salida = new FileWriter("Finques2.json");
			salida.write(arrel.toJSONString());
			salida.close();
			f.close();
		}
	}

}
