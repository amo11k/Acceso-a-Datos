package exemples;

import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Exemple_3_54 {

    public static void main(String[] args) throws ParseException, org.json.simple.parser.ParseException {
        String cadena = "[ 5 , 7 , 8 , 7 ]";
        
        JSONParser parser = new JSONParser();
        JSONArray arrel = (JSONArray) parser.parse(cadena);
        
        for (int i=0; i<arrel.size();i++)
            System.out.println(arrel.get(i));
    }
}