package com.ensisa.table.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Composite;

/*
* @author Mohamed MEDARHRI
*/
public class AuditionCalendarWidget extends Composite {

  /**
   * A data provider that bridges the provides row level updates from the data
   * available through a <@link AuditionCalendarService>.
   */
  public class CalendarProvider implements DynaTableDataProvider {

    private final AuditionCalendarServiceAsync calService;

    private int lastMaxRows = -1;

    private Personne[] lastPeople;

    private int lastStartRow = -1;

    public CalendarProvider() {
      // Initialize the service.
      //
      calService = (AuditionCalendarServiceAsync) GWT.create(AuditionCalendarService.class);

      // By default, we assume we'll make RPCs to a servlet, but see
      // updateRowData(). There is special support for canned RPC responses.
      // (Which is a totally demo hack, by the way :-)
      // 
      ServiceDefTarget target = (ServiceDefTarget) calService;

      // Use a module-relative URLs to ensure that this client code can find
      // its way home, even when the URL changes (as might happen when you
      // deploy this as a webapp under an external servlet container).
      String moduleRelativeURL = GWT.getModuleBaseURL() + "calendar";
      target.setServiceEntryPoint(moduleRelativeURL);
    }

    public void updateRowData(final int startRow, final int maxRows,
        final RowDataAcceptor acceptor) {
      // Check the simple cache first.
      //
      if (startRow == lastStartRow) {
        if (maxRows == lastMaxRows) {
          // Use the cached batch.
          //
          pushResults(acceptor, startRow, lastPeople);
          return;
        }
      }

      // Fetch the data remotely.
      //
      calService.getPeople(startRow, maxRows, new AsyncCallback<Personne[]>() {
        public void onFailure(Throwable caught) {
          acceptor.failed(caught);
        }

        public void onSuccess(Personne[] result) {
          lastStartRow = startRow;
          lastMaxRows = maxRows;
          lastPeople = result;
          pushResults(acceptor, startRow, result);
        }

      });
    }

    private void pushResults(RowDataAcceptor acceptor, int startRow,
        Personne[] people) {
      String[][] rows = new String[people.length][];
      for (int i = 0, n = rows.length; i < n; i++) {
        Personne personne = people[i];
        rows[i] = new String[3];
        rows[i][0] = personne.getName();
        rows[i][1] = personne.getDescription();
        rows[i][2] = personne.getSchedule(daysFilter);
      }
      acceptor.accept(startRow, rows);
    }
  }

  private final CalendarProvider calProvider = new CalendarProvider();

  private final boolean[] daysFilter = new boolean[] {
      true, true, true, true, true, true, true};

  private final DynaTableWidget dynaTable;

  private Command pendingRefresh;

  public AuditionCalendarWidget(int visibleRows) {
    String[] columns = new String[] {"Name", "Description", "Schedule"};
    String[] styles = new String[] {"name", "desc", "sched"};
    dynaTable = new DynaTableWidget(calProvider, columns, styles, visibleRows);
    initWidget(dynaTable);
  }

  protected boolean getDayIncluded(int day) {
    return daysFilter[day];
  }

  @Override
  protected void onLoad() {
    dynaTable.refresh();
  }

  protected void setDayIncluded(int day, boolean included) {
    if (daysFilter[day] == included) {
      // No change.
      //
      return;
    }

    daysFilter[day] = included;
    if (pendingRefresh == null) {
      pendingRefresh = new Command() {
        public void execute() {
          pendingRefresh = null;
          dynaTable.refresh();
        }
      };
      DeferredCommand.addCommand(pendingRefresh);
    }
  }
}
