package exemples;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Exemple_3_55 {

    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
        Reader r_json = new FileReader("empresa.json");
        
        JSONObject arrel = (JSONObject) (new JSONParser().parse(r_json));
        
        JSONObject empresa = (JSONObject) arrel.get("empresa");
        
        JSONArray empleats = (JSONArray) empresa.get("empleat");
        
        for (Object e : empleats) {
            JSONObject emp = (JSONObject) e;
            System.out.println(emp.get("nom") + " (" + emp.get("sou") + ")");
        }
    }
}