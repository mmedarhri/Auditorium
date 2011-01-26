package annotatedObject;

import java.sql.SQLException;

import org.apache.hadoop.hbase.hbql.client.HBatch;
import org.apache.hadoop.hbase.hbql.client.HConnection;
import org.apache.hadoop.hbase.hbql.client.HConnectionManager;
import org.apache.hadoop.hbase.hbql.client.HPreparedStatement;
import org.apache.hadoop.hbase.hbql.client.HResultSet;
import org.apache.hadoop.hbase.hbql.client.Util;

/*@authors Mohamed MEDARHRI
 * 
 */

public class ExempleAnnotatedObject {

	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		  // Get a connection to HBase
        HConnection conn = HConnectionManager.newConnection();

        conn.execute("CREATE TEMP MAPPING demo2 FOR TABLE example2"
                     + "("
                     + "keyval KEY, "
                     + "f1 ("
                     + "  val1 STRING ALIAS val1, "
                     + "  val2 INT ALIAS val2, "
                     + "  val3 STRING ALIAS val3 DEFAULT 'This is a default value' "
                     + "))");

        // Clean up table
        if (!conn.tableExists("example2"))
            conn.execute("CREATE TABLE example2 (f1())");
        else
            conn.execute("DELETE FROM demo2");

        // Add some records using an INSERT stmt
        HPreparedStatement stmt = conn.prepareStatement("INSERT INTO demo2 " +
                                                        "(keyval, val1, val2, val3) VALUES " +
                                                        "(ZEROPAD(:key, 10), :val1, :val2, DEFAULT)");

        for (int i = 0; i < 5; i++) {
            stmt.setParameter("key", i);
            stmt.setParameter("val1", "Value: " + i);
            stmt.setParameter("val2", i);
            stmt.execute();
        }

        // Add some other records using an AnnotatedExample object
        final HBatch<AnnotatedExample> batch = conn.newHBatch();
        for (int i = 5; i < 10; i++) {
            AnnotatedExample obj = new AnnotatedExample();
            obj.keyval = Util.getZeroPaddedNonNegativeNumber(i, 10);
            obj.val1 = "Value: " + i;
            obj.val2 = i;
            batch.insert(obj);
        }
        batch.apply();

        // Query the records just added
        HResultSet<AnnotatedExample> records = conn.executeQuery("SELECT * FROM demo2", AnnotatedExample.class);

        for (AnnotatedExample rec : records) {
            System.out.println("Key = " + rec.keyval);
            System.out.println("f1:val1 = " + rec.val1);
            System.out.println("f1:val2 = " + rec.val2);
            System.out.println("f1:val3 = " + rec.val3);
        }

        conn.close();


	}
}
