package exemples;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

public class Exemple_2_03 {
	public static void main(String[] args) throws IOException {

		FileInputStream f1 = new FileInputStream("f1.txt");
		String ent_1 = "Hola. Aquest és un byte array";
		ByteArrayInputStream f2 = new ByteArrayInputStream(ent_1.getBytes());
		SequenceInputStream f_in = new SequenceInputStream(f1, f2);
		int c = f_in.read();
		while (c != -1) {
			System.out.println((char) c);
			c = f_in.read();
		}
		f_in.close();
		f1.close();
		f2.close();
	}
}
