package exemples;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EmpleatsAleatori {

    public static void main(String[] args) throws IOException{

        RandomAccessFile f = new RandomAccessFile("Empleats.dat","r");
        f.seek(56);
        System.out.println("Núm.: "+f.readInt());
        System.out.println("Nom: "+f.readUTF());
        System.out.println("Depart: "+f.readInt());
        System.out.println("Edat: "+f.readInt());
        System.out.println("Sou: "+f.readDouble());
        f.close();

    }

}