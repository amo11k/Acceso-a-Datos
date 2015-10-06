package exercisi3_1;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Llegir_Ruta_Serial {

	public static void main(String[] args) throws IOException {
		DataInputStream f = new DataInputStream(new FileInputStream("Ruta.dat"));
		int punts = 1;

		while (f.available() > 0) {

			System.out.println("Ruta: " + f.readUTF());
			System.out.println("Desnivell: " + f.readInt());
			System.out.println("Desnivell acumulat: " + f.readInt());
			System.out.println("Número de punts: " + f.readInt());
			for (int i = 1; i <= 5; i++) {
				System.out.println(
						"Punt " + punts + ": " + f.readUTF() + "(" + f.readDouble() + ", " + f.readDouble() + ")");
				punts++;
			}

		}
		f.close();

	}

}
