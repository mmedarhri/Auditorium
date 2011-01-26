package com.example.calendar.client;

import java.util.Date;
import java.util.Map;

import org.apache.hadoop.hbase.client.HTable;

import com.ensisa.login.client.MyService;
import com.ensisa.login.client.MyServiceAsync;
import com.ensisa.login.client.User;
import com.ensisa.login.server.HBaseConnector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.smartgwt.client.widgets.calendar.CalendarEvent;

/*@authors Mohamed MEDARHRI
 * 
 */

public class CalendarData {
	
private static CalendarEvent[] records;


public  CalendarEvent[] getRecords() {
    if (records == null) {
    	
        getNewRecords();
    }
    return records;
}

private static GreetingServiceAsync getService() {
	System.out.println("getservice");
	GreetingServiceAsync svc = (GreetingServiceAsync) GWT.create(GreetingService.class);
	   ServiceDefTarget endpoint = (ServiceDefTarget) svc;
	   System.out.println("endpoint "+GWT.getModuleBaseURL() + "GreetingService");
	   endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "GreetingService");
	 
	   return svc;
	 }

//cette methode se connecte a la base et met a jour des donnes
public  boolean setNewRecords()
{
	try{
		
		return true;
}catch(Exception e)
{
	System.out.println("setNewRecords error"+e.toString());
	return false;
}
	
	
	
}

//Cette methode se connecte à la base et recupere les events
@SuppressWarnings({ "deprecation", "unchecked" })
public void getNewRecords() {
	 System.out.println("get records from DB");
	 
	 /**
	     * Async call to the server 
	     */
	    
		@SuppressWarnings("rawtypes")
		AsyncCallback callback = new AsyncCallback() {

			@Override
			public void onFailure(Throwable caught) {
				
				System.out.println("failure");
				
			}

			@Override
			public void onSuccess(Object result) {
				//User user = (User) result;
		           
		        
		              
				System.out.println("success");
				
			}
			
		};
	 
		 getService().getNewRecords(callback);

}


}
