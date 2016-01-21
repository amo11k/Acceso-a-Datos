package empleats;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Prova1_1 {
    public static void main(String[] args) {
        ObjectContainer bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"Empleats.db4o");
        Empleat e = new Empleat("22222222b","Berta",10,35,1700,null,null,null,null,null);
        Empleat f = new Empleat("33333333c","Clàudia",20,37,1500,null,null,null,null,null);
        
        //les dades més complicades les introduïm de forma especial
        e.setAdreca(new Adreca("C/ Enmig, 7","12001","Castelló"));
        String[] corr = {"alu22222222b@ieselcaminas.org","berta@gmail.com"};
        e.setCorreus_e(corr);
        Telefon[] tels = {new Telefon(true,"666555444"),new Telefon(false,"964223344")};
        e.setTelefons(tels);
        
        f.setAdreca(new Adreca("C/ de Dalt, 7",null,"Borriana"));
        String[] corr2 = {"alu33333333c@ieselcaminas.org"};
        f.setCorreus_e(corr2);
        
        bd.store(e);
        bd.store(f);
        
        bd.close();
    }
}