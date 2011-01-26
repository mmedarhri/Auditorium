package utils;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.hbql.client.HBatch;
import org.apache.hadoop.hbase.hbql.client.HConnection;
import org.apache.hadoop.hbase.hbql.client.HConnectionManager;
import org.apache.hadoop.hbase.hbql.client.HPreparedStatement;
import org.apache.hadoop.hbase.hbql.client.HRecord;
import org.apache.hadoop.hbase.hbql.client.HResultSet;
import org.apache.hadoop.hbase.hbql.client.Util;




import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HbaseCalendarData {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException 
	{
		
		 HBaseConnector hbaseconnector = new HBaseConnector();
		 HTable users = hbaseconnector.getHTable("cal");
		 Map map_users= hbaseconnector.retrieveColumn(users,"1");
		
		 System.out.println("Title :"+hbaseconnector.getName(map_users,"title"));
		 System.out.println("date start :"+hbaseconnector.getDatestart(map_users,"start"));
		 System.out.println("date end :"+hbaseconnector.getDateend(map_users,"end"));
			
		
	
}
}