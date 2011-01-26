package utils;

import org.apache.hadoop.hbase.hbql.client.AsyncExecutorManager;
import org.apache.hadoop.hbase.hbql.client.HBqlException;
import org.apache.hadoop.hbase.hbql.client.HConnection;
import org.apache.hadoop.hbase.hbql.client.HConnectionManager;
import org.apache.hadoop.hbase.hbql.client.HRecord;
import org.apache.hadoop.hbase.hbql.client.HStatement;
import org.apache.hadoop.hbase.hbql.client.QueryFuture;
import org.apache.hadoop.hbase.hbql.client.QueryListenerAdapter;

/*@authors Mohamed MEDARHRI
 * 
 */

public class HbqlAsynchronousQuery {

	public static void main(String[] args) 
	{
		try{
		 HConnection conn = HConnectionManager.newConnection();

	        HStatement stmt = conn.createStatement();
	        stmt.execute("CREATE TABLE table12 (f1(), f3()) IF NOT tableexists('table12')");

	        stmt.execute("CREATE TEMP MAPPING sch9 FOR TABLE table12"
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

	        if (!AsyncExecutorManager.asyncExecutorExists("async1"))
	            AsyncExecutorManager.newAsyncExecutor("async1", 1, 10, Long.MAX_VALUE);

	        conn.setAsyncExecutorName("async1");

	        QueryFuture future;
							future = stmt.executeQueryAsync("select * from sch9",
				                                            new QueryListenerAdapter<HRecord>() {
				                                                public void onEachRow(final HRecord rec) throws HBqlException {
				                                                    int val5 = (Integer)rec.getCurrentValue("val5");
				                                                    int val6 = (Integer)rec.getCurrentValue("val6");
				                                                    String val1 = (String)rec.getCurrentValue("val1");
				                                                    String val2 = (String)rec.getCurrentValue("val2");

				                                                    System.out.print("val5: " + val5);
				                                                    System.out.print(", val6: " + val6);
				                                                    System.out.print(", val1: " + val1);
				                                                    System.out.println(", val2: " + val2);
				                                                }

				                                                public void onException(final ExceptionSource source,
				                                                                        final HBqlException e) {
				                                                    e.printStackTrace();
				                                                }
				                                            });
			

	        
	            future.await();
	        
		
	        stmt.close();

	        conn.close();

		} catch (HBqlException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        catch (InterruptedException e) {
	            e.printStackTrace();
	        }

		
		}
	
}
