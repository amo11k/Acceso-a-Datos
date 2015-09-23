package exercicis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

public class Exercisi2_1 {

	public static void transformaImatge(File f, String opcio) throws IOException {
		File f2 = new File("/home/alumnes/1CFSL/alu53381650f/workspace/T2AD/PenyagolosaResult.bmp");
		String extension;
		String path;
		FileInputStream in;
		FileOutputStream out;

		// Comprueba si el fichero existe
		if (f.exists()) {
			// Comprueba si el fichero es de la extension .bmp
			path = f.getAbsolutePath();
			extension = path.substring(path.lastIndexOf("."));
			if (extension.equals(".bmp")) {
				in = new FileInputStream(f);
				out = new FileOutputStream(f2);

				// Comprueba la opcion pasada al método
				switch (opcio) {
				case "n":
					break;
				case "o":
					break;
				case "bn":
					break;
				default:
					System.out.println("Opció invàlida");
					break;
				}

			} else {
				System.out.println("El fitxer no es d'extensió bmp");
			}
		} else
			System.out.println("El arxiu no existeix");
		// in.close();
		// out.close();
	}

	public static void main(String[] args) throws IOException {

		File f = new File("/home/alumnes/1CFSL/alu53381650f/workspace/T2AD/Penyagolosa.bmp");
		transformaImatge(f, "bn");

	}
}
