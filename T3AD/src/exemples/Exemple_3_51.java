package exemples;

import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Exemple_3_51 {

    public static void main(String[] args) throws ParseException, org.json.simple.parser.ParseException {
        String cadena = "{ \"p1\" : 2 , \"p2\" : 4 , \"p3\" : 6 , \"p4\" : 8 , \"p5\" : 10 }";
        
        JSONParser parser = new JSONParser();
        JSONObject arrel = (JSONObject) parser.parse(cadena);
        
        System.out.println(arrel.get("p3"));
    }
}