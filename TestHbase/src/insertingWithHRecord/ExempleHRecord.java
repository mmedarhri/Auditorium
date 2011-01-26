package insertingWithHRecord;

import java.sql.SQLException;

import org.apache.hadoop.hbase.hbql.client.HBatch;
import org.apache.hadoop.hbase.hbql.client.HBqlException;
import org.apache.hadoop.hbase.hbql.client.HConnection;
import org.apache.hadoop.hbase.hbql.client.HConnectionManager;
import org.apache.hadoop.hbase.hbql.client.HPreparedStatement;
import org.apache.hadoop.hbase.hbql.client.HRecord;
import org.apache.hadoop.hbase.hbql.client.HResultSet;
import org.apache.hadoop.hbase.hbql.client.Util;

/*@authors Mohamed MEDARHRI
 * 
 */

public class ExempleHRecord {

	private HConnection conn;
	private HPreparedStatement stmt;
	HResultSet<HRecord> records;
		
	
	
	public ExempleHRecord() {
		
		getConnection();
		
	}
	public HResultSet<HRecord> getRecords() {
		return records;
	}
	public void setRecords(HResultSet<HRecord> records) {
		this.records = records;
	}
	public HPreparedStatement getStmt() {
		return stmt;
	}
	public void setStmt(HPreparedStatement stmt) {
		this.stmt = stmt;
	}
	public HConnection getConn() {
		return conn;
	}
	public void setConn(HConnection conn) {
		this.conn = conn;
	}
	public HConnection getConnection()
	{
		try {
			conn = HConnectionManager.newConnection();
			setConn(conn);
			return conn;
		} catch (HBqlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public HResultSet<HRecord> querryRecords(String tablemapping)
	{
		try {
			records = getConn().executeQuery("SELECT * FROM "+tablemapping);
			setRecords(records);
			return records;
		} catch (HBqlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	public void createTempMapping(String tablename,String mappingname,String cf1,String cf2,String cf3,String cf4,String cf5,String cf6,String nom,String prenom,String provenance,String filiere,String date_orale,String heure_orale,String lieu_orale )
	{
		try {
			getConn().execute("CREATE TEMP MAPPING "+mappingname+" FOR TABLE "+ tablename+ "("
			        + "keyval KEY, "
			        + cf1+" ("
			        + nom+" STRING ALIAS nom, "
			        + prenom+" STRING ALIAS prenom),"+cf2+" ("
			        + provenance+" STRING ALIAS provenance),"+cf3+" ("
			        + filiere+" STRING ALIAS filiere),"+cf4+" ("
			        + date_orale+" STRING ALIAS date_orale),"+cf5+" ("
			        + heure_orale+" STRING ALIAS heure_orale),"+cf6+" ("
			        + lieu_orale+" STRING ALIAS lieu_orale"+"))");
		

   // Clean up table
   if(!conn.tableExists(tablename))
       conn.execute("CREATE TABLE "+tablename+" (" +cf1+"(), "+cf2+"(), "+cf3+"(), "+cf4+"(), "+cf5+"(), "+cf6+"())");
   
       else       conn.execute("DELETE FROM "+ mappingname);
   
   addRecordWithInsert(mappingname, prenom, provenance, filiere, date_orale, heure_orale, lieu_orale);
   
		} catch (HBqlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HPreparedStatement addRecordWithInsert(String mappingname,String nom,String provenance,String filiere,String date_orale,String heure_orale,String lieu_orale)
	{
		try {
			stmt = getConn().prepareStatement("INSERT INTO "+mappingname+
			        " (keyval,"+nom+","+provenance+","+filiere+","+date_orale+","+heure_orale+","+lieu_orale+") VALUES " +
			        "(ZEROPAD(:key, 1), :"+nom+",:"+provenance+",:"+filiere+",:"+date_orale+",:"+heure_orale+",:"+lieu_orale+")");
			//stmt.close();
			  return stmt;
		} catch (HBqlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		 // Get a connection to HBase
      //   conn = HConnectionManager.newConnection();

        // CREATE TEMP MAPPING
   /*     conn.execute("CREATE TEMP MAPPING demo FOR TABLE Candidats"
                     + "("
                     + "keyval KEY, "
                     + "nom_prenom_provenance ("
                     + "  nom STRING ALIAS nom, "
                     + "  prenom STRING ALIAS prenom, "
                     + "  provenance STRING ALIAS provenance "
                     + "))");

        // Clean up table
        if (!conn.tableExists("Candidats"))
            conn.execute("CREATE TABLE Candidats (nom_prenom_provenance()) ");
        else
            conn.execute("DELETE FROM demo");
*/
        // Add some records using an INSERT stmt
       /* HPreparedStatement stmt = conn.prepareStatement("INSERT INTO demo " +
                                                        "(keyval, nom, prenom,provenance) VALUES " +
                                                        "(ZEROPAD(:key, 1), :nom, :prenom, :provenance)");
*/
      /*  for (int i = 0; i < 5; i++) {
            stmt.setParameter("key", i);
            stmt.setParameter("nom", "Value: " + "test"+i);
            stmt.setParameter("prenom", "Value: "+"test"+i);
            stmt.setParameter("provenance","Value: "+"test"+i);
            stmt.execute();
        }
*/
        // Add some other records using the Record interface
       /* final HBatch<HRecord> batch = conn.newHBatch();
        for (int i = 5; i < 10; i++) {
            HRecord rec = conn.getMapping("demo").newHRecord();
            rec.setCurrentValue("keyval", Util.getZeroPaddedNonNegativeNumber(i, 10));
            rec.setCurrentValue("nom", "Value: test"+i);
            rec.setCurrentValue("nom_prenom_provenance:provenance", "test"+i);
            batch.insert(rec);
        }
        batch.apply();
	*/
        // Query the records just added
      //  HResultSet<HRecord> records = conn.executeQuery("SELECT * FROM demo");

		ExempleHRecord hrecord = new ExempleHRecord();
		hrecord.createTempMapping("candidats","test", "cf1", "cf2", "cf3", "cf4", "cf5", "cf6", "nom", "prenom", "provenance", "filiere", "date_orale", "heure_orale", "lieu_orale");
		
		HResultSet<HRecord> records = hrecord.querryRecords("test");
		
        for (HRecord rec : records) {
            System.out.println("Key = " + rec.getCurrentValue("keyval"));
            System.out.println("nom_prenom_provenance:nom = " + rec.getCurrentValue("nom"));
           // System.out.println("nom_prenom_provenance:prenom = " + rec.getCurrentValue("nom_prenom_provenance:prenom"));
          //  System.out.println("nom_prenom_provenance:provenance = " + rec.getCurrentValue("nom_prenom_provenance:provenance"));
        }

     //   stmt.close();

	}
}
