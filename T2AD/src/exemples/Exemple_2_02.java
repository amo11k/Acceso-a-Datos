package exemples;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Exemple_2_02 {
	public static void main(String[] args) throws IOException {

		String ent_1 = "Hola. Aquest és un byte array";
		byte[] ent = ent_1.getBytes();
		ByteArrayInputStream f_in = new ByteArrayInputStream(ent);
		int c = f_in.read();
		while (c != -1) {
			System.out.println((char) c);
			c = f_in.read();
		}
		f_in.close();
	}
}