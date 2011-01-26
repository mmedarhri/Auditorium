package com.example.calendar.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.calendar.Calendar;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

/*@authors Mohamed MEDARHRI
 * 
 */

public class Calendrier implements EntryPoint {
	  public void onModuleLoad() {
	       RootPanel.get().add(getViewPanel());
	    }

	    public Canvas getViewPanel() {
	      Calendar calendar = new Calendar();
	      CalendarData cd= new CalendarData();
	     // calendar.setData(cd.getRecords());
	      return calendar;
	      
	    }
	}