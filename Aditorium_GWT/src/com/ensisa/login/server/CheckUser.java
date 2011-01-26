package com.ensisa.login.server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.apache.hadoop.hbase.client.HTable;


public class CheckUser {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException 
	{
		
		 HBaseConnector hbaseconnector = new HBaseConnector();
		 HTable users = hbaseconnector.getHTable("Users");
		 Map map_users= hbaseconnector.retrieveColumn(users,"essaim");
		// System.out.println("Password :"+hbaseconnector.getNom(map_users,"Infos"));
		 System.out.println("Password :"+hbaseconnector.getPassword(map_users,"Infos"));
		 System.out.println("Role :"+hbaseconnector.getRole(map_users,"Infos"));	
}
}