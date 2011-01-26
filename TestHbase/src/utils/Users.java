package utils;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class Users {

	
	public static void main(String[] args) throws IOException {
	    //ON a besoin dun objet configuration pour dire au client ou se connecter
		//qd on cree un hbaseconfiguration , il lit tout ce quon a defini ds le hbase-site.xml et hbase-default.xml
		//si il les trouve dans le classpath
	    
	    HBaseConfiguration config = new HBaseConfiguration();
   
	    //Ceci va instantier un objet htable qui va nous connecter à la table Candidats
	    HTable table_users = new HTable(config, "Users");
	    Scan s = new Scan();
	   // s.addColumn(Bytes.toBytes("Nom"), Bytes.toBytes("nom"));
	    ResultScanner scanner = table_users.getScanner(s);
	    try {
	      // Scanners return Result instances.
	      // Now, for the actual iteration. One way is to use a while loop like so:
	      for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
	        // print out the row we found and the columns we were looking for
	        System.out.println("Found row: " + rr);
	      }
	    }
	    finally {
		      // il faut tjrs fermer les scanner quand on a finit de sen servir 
		      // cest prquoi on les mets ds la clause try/finally
		      scanner.close();
		    }
		  }
		}