package utils;

import java.util.Set;

import org.apache.hadoop.hbase.hbql.client.HBqlException;
import org.apache.hadoop.hbase.hbql.client.HConnection;
import org.apache.hadoop.hbase.hbql.client.HConnectionManager;
import org.apache.hadoop.hbase.hbql.client.HMapping;
import org.apache.hadoop.hbase.hbql.client.HRecord;
import org.apache.hadoop.hbase.hbql.client.HResultSet;
import org.apache.hadoop.hbase.hbql.client.HStatement;

/*@authors Mohamed MEDARHRI
 * 
 */

public class HbqlSimpleQuery {

	
	
	public static void main(String[] args) throws HBqlException 
	{
		
		// Get a connection with an HTablePool size of 10
        HConnectionManager.setMaxPoolReferencesPerTablePerConnection(10);
        HConnection conn = HConnectionManager.newConnection();
       
        HStatement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE table12 (f1(), f3()) IF NOT tableexists('table12')");

        conn.execute("CREATE TEMP MAPPING sch8 FOR TABLE table12"
                     + "("
                     + "keyval key, "
                     + "f1 ("
                     + "    val1 string alias val1, "
                     + "    val2 string alias val2 "
                     + "), "
                     + "f3 ("
                     + "    val1 int alias val5, "
                     + "    val2 int alias val6 "
                     + "))");
        
        Set<HMapping> mappings = conn.getAllMappings();
        for (HMapping mapping : mappings)
            System.out.println(mapping.getMappingName());

        HResultSet<HRecord> rs = conn.executeQuery("select * from sch8");
        System.out.println(rs.toString());
        for (HRecord rec : rs) {
            int val5 = (Integer)rec.getCurrentValue("val5");
            int val6 = (Integer)rec.getCurrentValue("val6");
            String val1 = (String)rec.getCurrentValue("val1");
            String val2 = (String)rec.getCurrentValue("val2");

            System.out.print("val5: " + val5);
            System.out.print(", val6: " + val6);
            System.out.print(", val1: " + val1);
            System.out.println(", val2: " + val2);
        }

        stmt.execute("DISABLE TABLE table12");
        stmt.execute("DROP TABLE table12");
        stmt.close();

        conn.close();
		
		/*}catch(Exception e)
		{
			System.out.println("HbqlTest "+e.getMessage());
		}
		
*/
	}
}
