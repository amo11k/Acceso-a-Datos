package exemples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Exemple_2_51 {

	public static void main(String[] args) throws IOException {
		BufferedReader f_ent = new BufferedReader(new FileReader("f7_ent.txt"));
		PrintWriter f_eix = new PrintWriter(new FileWriter("f7_eix.txt"));
		String cad = f_ent.readLine();
		int i = 0;
		while (cad != null) {
			i++;
			f_eix.println(i + ".- " + cad);
			cad = f_ent.readLine();
		}
		f_eix.close();
		f_ent.close();
	}
}