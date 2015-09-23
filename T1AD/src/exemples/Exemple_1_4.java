package exemples;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exemple_1_4 {
    public static void main(String[] args) throws IOException {
        System.out.println("Introdueix un directori:");
        String ent = new BufferedReader(new InputStreamReader(System.in))
                .readLine();
        File f = new File(ent);
        if (f.exists()){
            if (f.isDirectory()){
                System.out.println("Llista de fitxers i directoris del directori " + f.getCanonicalPath());
                System.out.println("---------------------------------------------------");
                for (File e : f.listFiles()) {
                    if (e.isFile())
                        System.out.println(e.getName() + " <fichero> " + e.length());
                    if (e.isDirectory())
                        System.out.println(e.getName() + " <Directori>");
                }
            }
            else
                System.out.println("No és un directori");
        }
        else
            System.out.println("No existeix el directori");
        System.out.println(f.getFreeSpace());
    }
}

