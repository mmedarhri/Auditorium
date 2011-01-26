package core;

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


public class CheckUser {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException 
	{
		
		 HBaseConnector hbaseconnector = new HBaseConnector();
		 HTable users = hbaseconnector.getHTable("Users");
		 Map map_users= hbaseconnector.retrieveColumn(users,"essaim");
		 System.out.println("Password :"+hbaseconnector.getPassword(map_users,"Infos"));
		 System.out.println("Role :"+hbaseconnector.getRole(map_users,"Infos"));
		
		
	
}
}