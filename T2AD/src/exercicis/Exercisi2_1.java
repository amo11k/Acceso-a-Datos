package exercicis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

public class Exercisi2_1 {

	public static void transformaImatge(File f, String opcio) throws IOException {
		File f2 = new File("file.bmp");
		File temp;
		FileInputStream in = new FileInputStream(f);
		FileOutputStream out = new FileOutputStream(f2);
		int num, valor;
		String extension;
		String path;

		// Comprueba si el fichero existe
		if (f.exists()) {
			
			//Salto de los primeros 54b
			for(int i=0; i<54 ;i++){
				out.write(in.read());
			}
			// Comprueba si el fichero es de la extension .bmp
			if (f.getName().toString().matches(".+.bmp")) {
				// Comprueba la opcion pasada al método
				switch (opcio) {
				case "n":
					num = in.read();
					while (num != -1) {
						num = 255 - num;
						out.write(num);
						num = in.read();
					}
					temp = new File(f.getName().toString().replace(".", "_n."));
					f2.renameTo(temp);
					break;

				case "o":
					num = in.read();
					while (num != -1) {
						num = num / 2;
						out.write(num);
						num = in.read();
					}
					temp = new File(f.getName().toString().replace(".", "_o."));
					f2.renameTo(temp);
					break;
				case "bn":
					num = in.read();
					while (num != -1) {
						valor = num;
						for (int i = 0; i < 2; i++) {
							valor += in.read();
						}
						for (int i = 0; i < 3; i++) {
							out.write(valor / 3);
						}
						num = in.read();
					}
					temp = new File(f.getName().toString().replace(".", "_bn."));
					f2.renameTo(temp);
					break;
				default:
					System.out.println("Opció invàlida");
					break;
				}

			} else {
				System.out.println("El fitxer no es d'extensió bmp");
			}
		} else{
			System.out.println("El arxiu no existeix");
		}
			
		in.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {

		File f = new File("/home/alumnes/1CFSL/alu53381650f/workspace/T2AD/Penyagolosa.bmp");
		transformaImatge(f, "n");
		transformaImatge(f, "o");
		transformaImatge(f, "bn");

	}
}
