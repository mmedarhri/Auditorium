
package com.ensisa.table.client;

/*
* @author Mohamed MEDARHRI
*/
public class Candidat extends Personne {

  private Schedule auditionschedule = new Schedule();

  public Schedule getClassSchedule() {
    return auditionschedule;
  }

  @Override
  public String getSchedule(boolean[] daysFilter) {
    return auditionschedule.getDescription(daysFilter);
  }
}
