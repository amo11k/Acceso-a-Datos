import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Exercici1 {

	public static void main(String[] args) throws IOException{
		DataInputStream dataIn = new DataInputStream(new FileInputStream("dades.dat"));
		BufferedReader buffIn = new BufferedReader(new FileReader("dades.txt"));
		PrintWriter buffOut = new PrintWriter(new FileWriter("resum.txt"));
		
		try {
			;
			while (true) {
				buffOut.println(""+buffIn.readLine()+": "+dataIn.readFloat()+"m. - "+dataIn.readInt()+"kg.");
				
			}
		} catch (IOException e) {
			System.out.println("El fichero no tiene más lineas a leer");
		}finally{
			buffOut.close();
			buffIn.close();
			dataIn.close();
		}
		

		
		
	}

}
