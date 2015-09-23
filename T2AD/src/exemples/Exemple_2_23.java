package exemples;

import java.io.FileReader;
import java.io.IOException;

public class Exemple_2_23 {
	public static void main(String[] args) throws IOException {

		FileReader f_in = new FileReader("f2.txt");
		char[] buffer = new char[30];
		int n = f_in.read(buffer);
		while (n != -1) {
			for (int i = 0; i < n; i++)
				System.out.print(buffer[i]);
			System.out.println("");
			n = f_in.read(buffer);
		}
		f_in.close();
	}
}