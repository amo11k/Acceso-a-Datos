package exemples;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exemple_2_12 {

	public static void main(String[] args) throws IOException {
		FileInputStream f_in = new FileInputStream("f2.txt");
		FileOutputStream f_out = new FileOutputStream("f4.txt");

		byte[] buffer = new byte[20];
		int num = f_in.read(buffer);
		while (num != -1) {
			f_out.write(buffer, 0, num);
			num = f_in.read(buffer);
		}
		f_in.close();
		f_out.close();
	}
}