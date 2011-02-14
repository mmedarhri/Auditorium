
package com.ensisa.table.client;


import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

/*
* @author Mohamed MEDARHRI
*/
public class Schedule implements IsSerializable {

  private List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();

  public Schedule() {
  }

  public void addTimeSlot(TimeSlot timeSlot) {
    timeSlots.add(timeSlot);
  }

  public String getDescription(boolean[] daysFilter) {
    String s = null;
    for (TimeSlot timeSlot : timeSlots) {
      if (daysFilter[timeSlot.getDayOfWeek()]) {
        if (s == null) {
          s = timeSlot.getDescription();
        } else {
          s += ", " + timeSlot.getDescription();
        }
      }
    }

    if (s != null) {
      return s;
    } else {
      return "";
    }
  }

  @Override
  public String toString() {
    return getDescription(null);
  }

}
