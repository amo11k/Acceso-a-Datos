package exemples;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;
import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class transformarBicicas {

	public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
        Reader r_json = new FileReader("bicicas.json");

        JSONArray estacions = (JSONArray) ((JSONObject) ((JSONArray ) new JSONParser().parse(r_json)).get(0)).get("ocupacion");
        
        JSONArray destEstacions = new JSONArray();
        
        for (Object est : estacions){
            JSONObject e = (JSONObject) est;
            LinkedHashMap destE = new LinkedHashMap();
            destE.put("num", e.get("id"));
            destE.put("nom", e.get("punto"));
            destE.put("llocs", e.get("puestos"));
            destE.put("ocupats", e.get("ocupados"));
            int lliures = Integer.parseInt(e.get("puestos").toString())-Integer.parseInt(e.get("ocupados").toString());
            destE.put("lliures", lliures );
            destEstacions.add(destE);
        }
        
        JSONObject bicicas = new JSONObject();
        bicicas.put("bicicas",destEstacions);
        
        Writer w_json = new FileWriter("bicicas3.json");
        w_json.write(bicicas.toJSONString());
        w_json.close();
    }
}
