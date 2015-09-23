package exemples;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Exemple_1_2 {
	public static void main(String[] args) throws IOException {
		
		
		
		System.out.println("Introdueix un directori:");
		String ent = new BufferedReader(new InputStreamReader(System.in)).readLine();
		File f = new File(ent);
		System.out.println("Llista de fitxers i directoris del directori " + ent);
		System.out.println("---------------------------------------------------");
		for (String e : f.list())
			System.out.println(e);
	}
}