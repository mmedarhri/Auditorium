package com.example.calendar.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.hadoop.hbase.client.HTable;

import com.ensisa.login.server.HBaseConnector;


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