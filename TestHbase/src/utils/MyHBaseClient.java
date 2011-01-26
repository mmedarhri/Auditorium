package utils;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

/*@authors Mohamed MEDARHRI
 * 
 */
public class MyHBaseClient {

	public static void main(String[] args) throws IOException {
	    //ON a besoin dun objet configuration pour dire au client ou se connecter
		//qd on cree un hbaseconfiguration , il lit tout ce quon a defini ds le hbase-site.xml et hbase-default.xml
		//si il les trouve dans le classpath
	    
	    HBaseConfiguration config = new HBaseConfiguration();

	    
	    //Ceci va instantier un objet htable qui va nous connecter à la table Candidats
	    HTable table = new HTable(config, "Candidats");

	    // To add to a row, use Put.  A Put constructor takes the name of the row
	    //pour ajouter une ligne , on utilise put , un put constructeur prend le nom de la ligne que lon veut inserer 
	    //à la table comme un tableau de byte. dans hbase , la class bytes à pour utilite de convetir tous les types java en tableau de bytes.
	    //Ici on est entrain de convertir le string "nomdeligne" en un tableau de bytes , pour lutiliser comme une cle pour la ligne
	    //pour un update.
	    //une fois on a un put on peut setter le nom des colonnes que lon veut updater ds la ligne, le timestamp que lin 
	    //veut utiliser pour lupdate etc
	    //si il nya pas de timestamp le server specifie le temps courant pour les modifs
	    
	    Put p = new Put(Bytes.toBytes("8234567"));

	    //Pour setter la valeur que lon veut updater ds le ligne "nameligne", il faut specifier
	    //la famille de colonne , le qualifier de colonne, et la valeur de la cellule que lon veut updater ds la table
	    //lA FAMILLE DE colonne doit exister ds la table shema, le qualifire peut etre nimporte .. tout doit etre convetrtit en tableau de byte
	    
	    //Lets pretend the table
	    // 'myLittleHBaseTable' was created with a family 'myLittleFamily'.
	  
	    p.add(Bytes.toBytes("Nom"), Bytes.toBytes("nom"),
	      Bytes.toBytes("Medarhri"));

	    //Ensuit un push ds la table des updates qui on ete fait 
	   
	    table.put(p);

	    // Maintenant pour recuperer les datas qui on ete ecrite . les valeurs de retour sont des instances de Result
	    //on general un result est un objet qui va packager le retour de hbase ds la form que lon souhaite.
	    Get g = new Get(Bytes.toBytes("8234567"));
	    Result r = table.get(g);
	    byte [] value = r.getValue(Bytes.toBytes("Nom"),
	      Bytes.toBytes("nom"));
	    // If we convert the value bytes, we should get back 'Some Value', the
	    // value we inserted at this location.
	    String valueStr = Bytes.toString(value);
	    System.out.println("GET: " + valueStr);

	    // Parfois on ne sait pas quelle ligne on cherche, dans ce cas on utilise
	    //un scanner . ceci nous donne une  interface pour iterer sur le contenu de la table.
	    //pour obtenir un scanner , mme procedure que pour un put . 
	    
	    Scan s = new Scan();
	    s.addColumn(Bytes.toBytes("Nom"), Bytes.toBytes("nom"));
	    ResultScanner scanner = table.getScanner(s);
	    try {
	      // Scanners return Result instances.
	      // Now, for the actual iteration. One way is to use a while loop like so:
	      for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
	        // print out the row we found and the columns we were looking for
	        System.out.println("Found row: " + rr);
	      }

	      // The other approach is to use a foreach loop. Scanners are iterable!
	      // for (Result rr : scanner) {
	      //   System.out.println("Found row: " + rr);
	      // }
	    } finally {
	      // il faut tjrs fermer les scanner quand on a finit de sen servir 
	      // cest prquoi on les mets ds la clause try/finally
	      scanner.close();
	    }
	  }
	}


