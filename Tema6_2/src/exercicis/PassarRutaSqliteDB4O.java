package exercicis;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import empleats.Empleat;
import util.bd.GestionarRutesBD;
import util.bd.Ruta;

public class PassarRutaSqliteDB4O {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestionarRutesBD gestionSQLITE;
		ArrayList<Ruta> llistat;
		ObjectContainer bd;
		
		gestionSQLITE = new GestionarRutesBD();
		llistat = gestionSQLITE.llistat();
		bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Rutes.db4o");
		
		for (Ruta e: llistat){
			bd.store(e);
		}
		bd.close();
		bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Rutes.db4o");
		ObjectSet<Ruta> llista = bd.queryByExample(new Ruta());
		
		for (Ruta e: llista){
			System.out.println(e.getNom()+" te "+e.getLlistaDePunts().size()+" punts.");
		}
		
		bd.close();

	}

}
