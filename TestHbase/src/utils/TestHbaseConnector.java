/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HTable;

/**
 *
 * @author mmedarhri
 */
public class TestHbaseConnector {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws IOException {
        HBaseConnector hbaseconnector = new HBaseConnector();
        /*HTable candidats = hbaseconnector.getHTable("Candidats");
        Map mapcandidats= hbaseconnector.retrieveColumn(candidats, "1");
        System.out.println("Nom :"+hbaseconnector.getNom(mapcandidats,"MP"));

        System.out.println("Prenom :"+hbaseconnector.getPrenom(mapcandidats,"MP"));
    System.out.println("numero de dossier :"+hbaseconnector.getNum_Dossier(mapcandidats,"MP"));

        System.out.println("Date audition :"+hbaseconnector.getDate_Audition(mapcandidats,"MP"));
   System.out.println("Heure audition :"+hbaseconnector.getHeure_Audition(
           mapcandidats,"MP"));
  */
        HTableDescriptor[] list =hbaseconnector.listTables();
        HTable ht = hbaseconnector.getHTable("Candidats");
        Map<String,String> map = hbaseconnector.retrieveColumn(ht,"1");
        //System.out.println(hbaseconnector.getCellrowValue(ht,"1","Enseignant_Lumiere:mail")); 
        System.out.println("Column for row 1 is Empty ? "+map.isEmpty());
      //  System.out.println("value of Enseignant_Lumiere "+map.get
        System.out.println("value of Candidats EQUIV: "+hbaseconnector.getCellValue(map,"EQUIV"));
        System.out.println("value of Enseignant_Werner: "+hbaseconnector.getCellValue(map,"Enseignant_Werner:nom"));
        
        // System.out.println("value of Enseignant_Lumiere:name "+map.get("Enseignant_Lumiere:name"));
  }

}
