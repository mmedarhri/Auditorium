package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.Cell;
import org.apache.hadoop.hbase.util.Bytes;

import csv.CSVManager;

/*@authors Mohamed MEDARHRI
 * 
 */
public class CreateHbaseTable {
	
	private HBaseAdmin hba;
	private Configuration cf;
	private HBaseConfiguration hc;
	private HTableDescriptor ht ;
	private Put put;
	private Get get;
	private HTable table;
	private Scan scan;
	private ResultScanner scanner;
	private CSVManager csv;
	private String filename = "input.csv";
	private String path = System.getProperty("user.dir")+"/"+filename;
	
	public CreateHbaseTable(String name)
	{
		try {
		 cf = new Configuration();
		 hc = new HBaseConfiguration(cf);
		 ht = new HTableDescriptor(name);
		 
	     csv = new CSVManager(new File(path));
		
		 setHTableDescriptor(ht);
		 
			hba = new HBaseAdmin( hc );
		} catch (MasterNotRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
	
	public HBaseAdmin getHba() {
		return hba;
	}


	public void setHba(HBaseAdmin hba) {
		this.hba = hba;
	}


	public Configuration getCf() {
		return cf;
	}


	public void setCf(Configuration cf) {
		this.cf = cf;
	}


	public HBaseConfiguration getHc() {
		return hc;
	}


	public void setHc(HBaseConfiguration hc) {
		this.hc = hc;
	}


	public HTableDescriptor getHTableDescriptor() {
		return ht;
	}


	public void setHTableDescriptor(HTableDescriptor ht) {
		this.ht = ht;
	}


	public void createTable(String name){
		try{
		
		 if(!hba.tableExists(name))
		 {
			 hba.createTable( ht );
		 }
		 else
			 System.out.println("Table "+name+" already exist");
		 

		}catch(Exception e)
		{
			System.out.println("CreateHbaseTable  createTable() "+e.getMessage());
		}
	
	}
	
	
	public void addColumnFamily(HTableDescriptor ht,String name)
	{
		ht.addFamily( new HColumnDescriptor(name+":"));	
	}
	
	public void addColumnFamilyCandidats()
	{
		addColumnFamily(ht,"Nom_prenom");
		addColumnFamily(ht,"Provenance");
		addColumnFamily(ht,"Filiere");
		addColumnFamily(ht,"Date_orale");
		addColumnFamily(ht,"Heure_orale");
		addColumnFamily(ht,"Lieu_orale");
		createTable("Candidats");
		for ( int i =0;i<csv.getRowsCount();i++)
		{
			insertIntoTableCandidats(csv.getNumeroDossier(i),csv.getname(i),csv.getProvenance(i),csv.getFiliere(i),csv.getDateAudition(i),csv.getHeureAudition(i),csv.getSiteAudition(i));
		}
	}
	
	public void addColumnFamilyJury(HTableDescriptor ht,String nom_prenom,String mail,String telephone,String adresse,String affiliation)
	{
		addColumnFamily(ht,nom_prenom);
		addColumnFamily(ht,mail);
		addColumnFamily(ht,telephone);
		addColumnFamily(ht,adresse);
		addColumnFamily(ht,affiliation);
		
		
	}
	
	public void getFromTable(String tablename,String rowname , String cf, String desc, String value)
	{
		get = new Get(Bytes.toBytes(rowname));
	    Result r;
		try {
			r = table.get(get);
		
	    byte [] val = r.getValue(Bytes.toBytes(cf),
	      Bytes.toBytes(desc));
	    // If we convert the value bytes, we should get back 'Some Value', the
	    // value we inserted at this location.
	    String valueStr = Bytes.toString(val);
	    System.out.println("GET: " + valueStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scanTable(String tablename,String cf, String desc) throws IOException
	{
		 scan = new Scan();
		 try {
			table = new HTable(hc, tablename);
		
		 scan.addColumn(Bytes.toBytes(cf), Bytes.toBytes(desc));
		  scanner = table.getScanner(scan);
		   
		      // Scanners return Result instances.
		      
		      for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
		        // print out the row we found and the columns we were looking for
		        System.out.println("Found row: " + rr);
		      }
		      
		  } finally {
		      // il faut tjrs fermer les scanner quand on a finit de sen servir 
		      // cest prquoi on les mets ds la clause try/finally
		      scanner.close();
		    }
	}
	
	
	public void insertIntoTableCandidats(String rowname ,String nom_prenom, String provenance, String filiere,String date_orale,String heure_orale,String lieu_orale)
	{
		insertIntoTable("Candidats", rowname,"Nom_prenom", "Nom", nom_prenom);
		insertIntoTable("Candidats", rowname,"Nom_prenom", "Prenom", nom_prenom);
		insertIntoTable("Candidats", rowname,"Provenance", "provenance", provenance);
		insertIntoTable("Candidats", rowname,"Filiere", "filiere", filiere);
		insertIntoTable("Candidats", rowname,"Date_orale", "date_orale", date_orale);
		insertIntoTable("Candidats", rowname,"Heure_orale", "heure_orale", heure_orale);
		insertIntoTable("Candidats", rowname,"Lieu_orale", "lieu_orale", lieu_orale);
	}
	
	public void insertIntoTableJury(String rowname ,String nom_prenom, String mail, String tel,String affiliation,String adresse)
	{
		insertIntoTable("Jury", rowname,"Nom_prenom", "Nom", nom_prenom);
		insertIntoTable("Jury", rowname,"Nom_prenom", "Prenom", nom_prenom);
		insertIntoTable("Jury", rowname,"Mail", "mail", mail);
		insertIntoTable("Jury", rowname,"Tel", "tel", tel);
		insertIntoTable("Jury", rowname,"Affiliation", "affiliation", affiliation);
		insertIntoTable("Jury", rowname,"Adresse", "adresse", adresse);
		//insertIntoTable("Jury", rowname,"Lieu_orale", "lieu_orale", lieu_orale);
	}
	public void insertIntoTable(String tablename,String rowname , String cf, String desc, String value)
	{	
		try {
			table = new HTable(hc, tablename);
			put = new Put(Bytes.toBytes(rowname));
			put.add(Bytes.toBytes(cf), Bytes.toBytes(desc),Bytes.toBytes(value));
			table.put(put);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeTable(String tableName){
		try {
			hba.disableTable(tableName);
		
		    hba.deleteTable(tableName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main( String args[] ) throws IOException {
		System.out.println( "Creation de la table candidats..." );
				
		CreateHbaseTable hbt = new CreateHbaseTable("Candidats");
				
		//System.out.println( "create htable descriptor..." );
		
		String filename = "input.csv";
		String path = System.getProperty("user.dir")+"/"+filename;
		  
		CSVManager csv = new CSVManager(new File(path));
		//remove table
	//    hbt.removeTable("Candidats");
			
		System.out.println( "add column family..." );
		
		//add colonne family et cree une table candidats
		hbt.addColumnFamilyCandidats();
	/*	for ( int i =0;i<csv.getRowsCount();i++)
		{
			hbt.insertIntoTableCandidats(csv.getNumeroDossier(i),csv.getname(i),csv.getProvenance(i),csv.getFiliere(i),csv.getDateAudition(i),csv.getHeureAudition(i),csv.getSiteAudition(i));
		}
		*/
		//hbt.getFromTable("Candidats", "1123", "Provenance", "provenance", "");
		
		
		System.out.println( "done!" );

	    }

	}
