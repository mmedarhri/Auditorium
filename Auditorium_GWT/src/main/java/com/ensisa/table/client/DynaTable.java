package com.ensisa.table.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The entry point class 
 */
public class DynaTable implements EntryPoint {

  public void onModuleLoad() {
    
    RootPanel slot = RootPanel.get("calendar");
    if (slot != null) {
      AuditionCalendarWidget calendar = new AuditionCalendarWidget(15);
      slot.add(calendar);

      slot = RootPanel.get("days");
      if (slot != null) {
        DayFilterWidget filter = new DayFilterWidget(calendar);
        slot.add(filter);
      }
    }
  }
}
