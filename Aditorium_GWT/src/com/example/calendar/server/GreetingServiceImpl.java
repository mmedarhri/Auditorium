package com.example.calendar.server;

import java.util.Date;
import java.util.Map;


import com.ensisa.login.client.User;
import com.example.calendar.client.GreetingService;
import com.example.calendar.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.smartgwt.client.widgets.calendar.CalendarEvent;


/*@authors Mohamed MEDARHRI
 * 
 */

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	private static CalendarEvent[] records;
	private static Date today = new Date();
	private static int year = today.getYear();
	private static int month = today.getMonth();
	


	/*public Map connectToCalendarTable(String tablename,String row)
	{
		 hbaseconnector = new HBaseConnector();
		 table = hbaseconnector.getHTable(tablename);
		 if(hbaseconnector.retrieveColumn(table,row)!=null)
		 {
			 map_users=hbaseconnector.retrieveColumn(table,row);
			 System.out.println("map_users not null");
			 return map_users;
		 }
		 else
			 System.out.println("map_users is null");
			 return null;
		 
	}
	
	*/



	@Override
	public CalendarEvent[] getNewRecords() throws IllegalArgumentException {
		System.out.println("records ");
		
		return records;
	}
				
        

}
