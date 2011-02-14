
package com.ensisa.table.client;


import com.google.gwt.user.client.rpc.IsSerializable;

/*
* @author Mohamed MEDARHRI
*/
public class TimeSlot implements IsSerializable, Comparable<TimeSlot> {

  private static final transient String[] DAYS = new String[] {
      "Lun", "Mar", "Mer", "Jeu", "Ven"};

  private int endMinutes;

  private int startMinutes;

  private int zeroBasedDayOfWeek;

  public TimeSlot() {
  }

  public TimeSlot(int zeroBasedDayOfWeek, int startMinutes, int endMinutes) {
    this.zeroBasedDayOfWeek = zeroBasedDayOfWeek;
    this.startMinutes = startMinutes;
    this.endMinutes = endMinutes;
  }

  public int compareTo(TimeSlot o) {
    if (zeroBasedDayOfWeek < o.zeroBasedDayOfWeek) {
      return -1;
    } else if (zeroBasedDayOfWeek > o.zeroBasedDayOfWeek) {
      return 1;
    } else {
      if (startMinutes < o.startMinutes) {
        return -1;
      } else if (startMinutes > o.startMinutes) {
        return 1;
      }
    }

    return 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TimeSlot)) {
      return false;
    }
    return compareTo((TimeSlot) obj) == 0;
  }
  
  public int getDayOfWeek() {
    return zeroBasedDayOfWeek;
  }

  public String getDescription() {
    return DAYS[zeroBasedDayOfWeek] + " " + getHrsMins(startMinutes) + "-"
        + getHrsMins(endMinutes);
  }

  public int getEndMinutes() {
    return endMinutes;
  }

  public int getStartMinutes() {
    return startMinutes;
  }

  @Override
  public int hashCode() {
    return endMinutes + 7 * startMinutes + 31 * zeroBasedDayOfWeek;
  }

  public void setDayOfWeek(int zeroBasedDayOfWeek) {
    if (0 <= zeroBasedDayOfWeek && zeroBasedDayOfWeek < 5) {
      this.zeroBasedDayOfWeek = zeroBasedDayOfWeek;
    } else {
      throw new IllegalArgumentException("day must be in the range 0-4");
    }
  }

  public void setEndMinutes(int endMinutes) {
    this.endMinutes = endMinutes;
  }

  public void setStartMinutes(int startMinutes) {
    this.startMinutes = startMinutes;
  }

  private String getHrsMins(int mins) {
    int hrs = mins / 60;
    if (hrs > 12) {
      hrs -= 12;
    }

    int remainder = mins % 60;

    return hrs + ":"
        + (remainder < 10 ? "0" + remainder : String.valueOf(remainder));
  }
}
