package exemples;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EmpleatsAleatori2 {

    public static void main(String[] args) throws IOException {

        RandomAccessFile f = new RandomAccessFile("Empleats2.dat","rw");
        Scanner sc = new Scanner(System.in);
        System.out.println("Quin registre? (-1 per a eixir): ");
        int num = sc.nextInt();
        while (num != -1) {

            f.seek(32*(num-1));
            System.out.println("Núm.: " + f.readInt());
            System.out.println("Nom: " + f.readUTF());
            System.out.println("Depart: " + f.readInt());
            System.out.println("Edat: " + f.readInt());
            System.out.println("Sou: " + f.readDouble());
            System.out.println();
            System.out.println("Quin registre? (-1 per a eixir): ");
            num = sc.nextInt();

        }
        f.close();

    }

}