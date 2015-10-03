package exemples;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class CrearEmpleats {

    public static void main(String[] args) throws IOException {

        DataOutputStream f = new DataOutputStream(new FileOutputStream("Empleats.dat"));

        String[] noms = {"Andreu","Bernat","Clàudia","Damià"};
        int[] departaments = {10,20,10,10};
        int[] edats = {32,28,26,40};
        double[] sous = {1000.0,1200.0,1100.0,1500.0};

        for (int i=0;i<4;i++){

            f.writeInt(i+1);
            f.writeUTF(noms[i]);
            f.writeInt(departaments[i]);
            f.writeInt(edats[i]);
            f.writeDouble(sous[i]);

        }
        f.close();

    }

}