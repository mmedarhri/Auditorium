package com.ensisa.table.client;


/*
* @author Mohamed MEDARHRI
*/
public interface DynaTableDataProvider {

  /**
   * An interface allow a widget to accept or report failure when a row data
   * is issued for update.
   */
  interface RowDataAcceptor {
    void accept(int startRow, String[][] rows);
    void failed(Throwable caught);
  }

  void updateRowData(int startRow, int maxRows, RowDataAcceptor acceptor);
}
