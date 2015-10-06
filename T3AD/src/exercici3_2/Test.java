package exercici3_2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		DataInputStream f = new DataInputStream(new FileInputStream("Ruta.dat"));
		int punts;
		Ruta r = new Ruta();
		while (f.available() > 0) {
			r.setNom(f.readUTF());
			r.setDesnivell(f.readInt());
			r.setDesnivellAcumulat(f.readInt());
			punts = f.readInt();
			for (int i = 1; i <= punts; i++) {
				r.addPunt(f.readUTF(), f.readDouble(), f.readDouble());
			}

		}
		f.close();
		r.mostraRuta();
	}

}
