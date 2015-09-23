package exemples;

import java.io.File;
import java.io.IOException;

public class Exemple_1_3 {

	public static void main(String[] args) throws IOException {
		
		//Métodos para obtener rutas en File
		File f = new File("fitxers/../fitxers/f1.txt");
		System.out.println("Nom del fitxer: " + f.getName());
		System.out.println("Ruta del fitxer: " + f.getPath());
		System.out.println("Ruta absoluta del fitxer: " + f.getAbsolutePath());
		System.out.println("Ruta canònica del fitxer: " + f.getCanonicalPath());
	}
}