package exemples;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Exemple_3_52 {

    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
        Reader r_json = new FileReader("parells.json");
        
        JSONParser parser = new JSONParser();
        JSONObject arrel = (JSONObject) parser.parse(r_json);
        
        System.out.println(arrel.get("p5"));
    }

}