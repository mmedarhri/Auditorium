package core;
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

public class CreateHbase {

	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{

        // Get a connection to HBase
        HConnection conn = HConnectionManager.newConnection();

        // CREATE TEMP MAPPING
        conn.execute("CREATE TEMP MAPPING demo1 FOR TABLE example1"
                     + "("
                     + "keyval KEY, "
                     + "f1 ("
                     + "  val1 STRING ALIAS val1, "
                     + "  val2 INT ALIAS val2, "
                     + "  val3 STRING DEFAULT 'This is a default value' "
                     + "))");

        // Clean up table
        if (!conn.tableExists("example1"))
            conn.execute("CREATE TABLE example1 (f1()) ");
        else
            conn.execute("DELETE FROM demo1");

        // Add some records using an INSERT stmt
        HPreparedStatement stmt = conn.prepareStatement("INSERT INTO demo1 " +
                                                        "(keyval, val1, val2, f1:val3) VALUES " +
                                                        "(ZEROPAD(:key, 10), :val1, :val2, DEFAULT)");

        for (int i = 0; i < 5; i++) {
            stmt.setParameter("key", i);
            stmt.setParameter("val1", "Value: " + i);
            stmt.setParameter("val2", i);
            stmt.execute();
        }

        // Add some other records using the Record interface
        final HBatch<HRecord> batch = conn.newHBatch();
        for (int i = 5; i < 10; i++) {
            HRecord rec = conn.getMapping("demo1").newHRecord();
            rec.setCurrentValue("keyval", Util.getZeroPaddedNonNegativeNumber(i, 10));
            rec.setCurrentValue("val1", "Value: " + i);
            rec.setCurrentValue("f1:val2", i);
            batch.insert(rec);
        }
        batch.apply();

        // Query the records just added
        HResultSet<HRecord> records = conn.executeQuery("SELECT * FROM demo1");

        for (HRecord rec : records) {
            System.out.println("Key = " + rec.getCurrentValue("keyval"));
            System.out.println("f1:val1 = " + rec.getCurrentValue("val1"));
            System.out.println("f1:val2 = " + rec.getCurrentValue("f1:val2"));
            System.out.println("f1:val3 = " + rec.getCurrentValue("f1:val3"));
        }

        stmt.close();

  }
}