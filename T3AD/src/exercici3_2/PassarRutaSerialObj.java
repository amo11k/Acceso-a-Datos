package exercici3_2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PassarRutaSerialObj {

	public static void main(String[] args) throws IOException {
		DataInputStream f = new DataInputStream(new FileInputStream("Ruta.dat"));
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Ruta.obj"));
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
		out.writeObject(r);
		out.close();
		

	}

}
