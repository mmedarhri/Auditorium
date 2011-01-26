package com.example.calendar.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.smartgwt.client.widgets.calendar.CalendarEvent;

/*@authors Mohamed MEDARHRI
 * 
 */

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	CalendarEvent[] getNewRecords() throws IllegalArgumentException;
}
