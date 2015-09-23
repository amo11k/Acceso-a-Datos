package exemples;
import java.io.StringReader;
import java.io.IOException;

public class Exemple_2_22 {
    public static void main(String[] args) throws IOException {

        String ent = "Hola. Aquest és un String normal i corrent";
        StringReader f_in = new StringReader(ent);
        int c = f_in.read();
        while (c != -1) {
            System.out.println((char) c);
            c = f_in.read();
        }
        f_in.close();
    }
}