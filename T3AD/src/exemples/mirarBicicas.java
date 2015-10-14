package exemples;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class mirarBicicas {

    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
        Reader r_json = new FileReader("bicicas.json");
        
        JSONArray arrel = (JSONArray) new JSONParser().parse(r_json);
        JSONObject obj = (JSONObject) arrel.get(0);
        JSONArray estacions = (JSONArray) obj.get("ocupacion");

        for (int i=0; i<estacions.size();i++){
            JSONObject e = (JSONObject) estacions.get(i);
            System.out.println(e.get("id") + ".- " + e.get("punto") + " (" + e.get("ocupados") + "/" + e.get("puestos") + ")");
        }
    }
}