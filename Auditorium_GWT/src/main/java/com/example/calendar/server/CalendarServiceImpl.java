package com.example.calendar.server;

import java.util.Date;
import java.util.Map;

import org.apache.hadoop.hbase.client.HTable;

import com.ensisa.test.login.client.User;
import com.example.calendar.client.CalendarService;
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
public class CalendarServiceImpl extends RemoteServiceServlet implements
		CalendarService {
	
	private Map map_users;
	private static CalendarEvent[] records;
	private static Date today = new Date();
	private static int year = today.getYear();
	private static int month = today.getMonth();
	


	
	



	public CalendarEvent[] getNewRecords() throws IllegalArgumentException {
		System.out.println("records ");
		
		return records;
	}
				
        

}
