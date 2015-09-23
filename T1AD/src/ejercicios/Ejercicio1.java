package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio1 {
	public static void main(String[] args) throws IOException {
		int opt = 0;
		int c = 0;
		File f = new File("/");
		File[] lista = new File[(int) f.list().length];
		lista = f.listFiles();
		while (opt != -1){
			if (f.isDirectory()) {
				System.out.println("Llista de fitxers i directoris del directori " + f.getCanonicalPath());
				System.out.println("---------------------------------------------------");
				System.out.println(c + "- Directori pare");
				c++;
				for (File e : f.listFiles()) {
					if (e.isFile()) {
						// VAMOS POR LOS APENDS
						System.out.println(c + "- " + e.getName() + " <fichero> " + e.length() + "bytes");
					}
					if (e.isDirectory())
						System.out.println(c + "- " + e.getName() + " <Directori>");
					c++;
				}
				System.out.println("Tria una opció. Trie -1 per a ixir del programa");
				c = 0;
				String ent = new BufferedReader(new InputStreamReader(System.in)).readLine();
				opt = Integer.parseInt(ent);
				if (opt == -1)
					break;
				if ((opt == 0) || (opt > lista.length)) {
					String s = f.getAbsolutePath();
					
					if (s == "/") {
						f = new File("/");
						lista = f.listFiles();
					} else {
						File padre = f.getParentFile();
						f = padre;
						lista = f.listFiles();
					}
				} else {
					f = lista[opt - 1];
					lista = f.listFiles();
				}

			} else {
				System.out.println("No és un directori");
				f = new File("/");
				lista = f.listFiles();
			}
		}
		System.out.println("El programa s'ha tancat correctament");
		System.exit(0);

	}
}