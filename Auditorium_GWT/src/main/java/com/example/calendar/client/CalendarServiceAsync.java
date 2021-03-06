package com.example.calendar.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.calendar.CalendarEvent;


/*@authors Mohamed MEDARHRI
 * 
 */

/**
 * The async counterpart of <code>CalendarService</code>.
 */
public interface CalendarServiceAsync {
	public void getNewRecords(AsyncCallback<CalendarEvent[]> callback)
			throws IllegalArgumentException;
}
