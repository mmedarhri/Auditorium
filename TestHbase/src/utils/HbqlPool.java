package utils;

import org.apache.hadoop.hbase.hbql.client.HBqlException;
import org.apache.hadoop.hbase.hbql.client.HConnection;
import org.apache.hadoop.hbase.hbql.client.HConnectionPool;
import org.apache.hadoop.hbase.hbql.client.HConnectionPoolManager;
import org.apache.hadoop.hbase.hbql.client.HRecord;
import org.apache.hadoop.hbase.hbql.client.HResultSet;
import org.apache.hadoop.hbase.hbql.client.HStatement;
import org.apache.hadoop.hbase.hbql.client.QueryExecutorPoolManager;

/*@authors Mohamed MEDARHRI
 * 
 */

public class HbqlPool {

	public static void main(String[] args) 
	{
		try {
		// For each connection in a connection pool, assign an HTablePool max size of 25 references per table
        HConnectionPoolManager.setMaxPoolReferencesPerTablePerConnection(25);

        // Create connection pool with max of 25 connections and prime it with 5 initial connections
        HConnectionPool connectionPool = HConnectionPoolManager.newConnectionPool(5, 25);

        // Create Query Executor Pool named execPool if it doesn't already exist.
        if (!QueryExecutorPoolManager.queryExecutorPoolExists("execPool"))
            QueryExecutorPoolManager.newQueryExecutorPool("execPool", 5, 5, 10, Long.MAX_VALUE, true, 100);

        // Take a connection from the connection pool
        HConnection conn;
		
			conn = connectionPool.takeConnection();
		
        // Assign the connection a query executor pool name to use for queries
        conn.setQueryExecutorPoolName("execPool");

        // Do something with the connection

        HStatement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE table12 (f1(), f3()) IF NOT tableexists('table12')");

        stmt.execute("CREATE TEMP MAPPING sch8 FOR TABLE table12"
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
        HResultSet<HRecord> rs = stmt.executeQuery("select * from sch8");

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
        stmt.close();
        // Close the connection to release it back to the connection pool
        conn.close();

		} catch (HBqlException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    }
}
