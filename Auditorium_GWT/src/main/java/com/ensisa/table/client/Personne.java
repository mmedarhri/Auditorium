
package com.ensisa.table.client;


import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Personne. cette class va etre serialisé avec les RPC
 * calls.
 */
/*
* @author Mohamed MEDARHRI
*/
public abstract class Personne implements IsSerializable {

  private String description = "DESC";

  private String name;

  public Personne() {
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public abstract String getSchedule(boolean[] daysFilter);

  public void setDescription(String description) {
    this.description = description;
  }

  public void setName(String name) {
    this.name = name;
  }
}
