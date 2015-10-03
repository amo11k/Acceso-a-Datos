package exemples;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ConsultarEmpleats {

    public static void main(String[] args) throws IOException {

        DataInputStream f = new DataInputStream(new FileInputStream("Empleats.dat"));

        while (f.available()>0){

            System.out.println("Número: " + f.readInt());
            System.out.println("Nom: " + f.readUTF());
            System.out.println("Depart: " + f.readInt());
            System.out.println("Edat: " + f.readInt());
            System.out.println("Sou: " + f.readDouble());
            System.out.println();

        }
        f.close();

    }

}