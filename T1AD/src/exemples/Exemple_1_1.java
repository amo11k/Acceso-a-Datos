package exemples;

import java.io.File;

public class Exemple_1_1 {
	public static void main(String[] args) {
		File f = new File("/home");
		System.out.println("Llista de fitxers i directoris del directori actual");
		System.out.println("---------------------------------------------------");
		for (String e : f.list())
			System.out.println(e);
	}
}