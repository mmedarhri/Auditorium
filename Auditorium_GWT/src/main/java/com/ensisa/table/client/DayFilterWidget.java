
package com.ensisa.table.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * permet de filtrer les jours disponibles dans la table dynamique
 */
public class DayFilterWidget extends Composite {

  private class DayCheckBox extends CheckBox {
    public final int day;

    public DayCheckBox(String caption, int day) {
      super(caption);

      this.day = day;

      addClickHandler(dayCheckBoxHandler);

      setValue(calendar.getDayIncluded(day));
    }
  }

  private class DayCheckBoxHandler implements ClickHandler {
    public void onClick(ClickEvent event) {
      onClick((DayCheckBox) event.getSource());
    }

    public void onClick(DayCheckBox dayCheckBox) {
      calendar.setDayIncluded(dayCheckBox.day, dayCheckBox.getValue());
    }
  }

  private final AuditionCalendarWidget calendar;

  private final VerticalPanel outer = new VerticalPanel();

  private final DayCheckBoxHandler dayCheckBoxHandler = new DayCheckBoxHandler();

  public DayFilterWidget(AuditionCalendarWidget calendar) {
    this.calendar = calendar;
    initWidget(outer);
    setStyleName("DynaTable-DayFilterWidget");
    outer.add(new DayCheckBox("Lundi", 0));
    outer.add(new DayCheckBox("Mardi", 1));
    outer.add(new DayCheckBox("Mercredi", 2));
    outer.add(new DayCheckBox("Jeudi", 3));
    outer.add(new DayCheckBox("Vendredi", 4));
  

    Button buttonAll = new Button("All", new ClickHandler() {
      public void onClick(ClickEvent event) {
        setAllCheckBoxes(true);
      }
    });

    Button buttonNone = new Button("None", new ClickHandler() {
      public void onClick(ClickEvent event) {
        setAllCheckBoxes(false);
      }
    });

    HorizontalPanel hp = new HorizontalPanel();
    hp.setHorizontalAlignment(HasAlignment.ALIGN_CENTER);
    hp.add(buttonAll);
    hp.add(buttonNone);

    outer.add(hp);
    outer.setCellVerticalAlignment(hp, HasAlignment.ALIGN_BOTTOM);
    outer.setCellHorizontalAlignment(hp, HasAlignment.ALIGN_CENTER);
  }

  private void setAllCheckBoxes(boolean checked) {
    for (int i = 0, n = outer.getWidgetCount(); i < n; ++i) {
      Widget w = outer.getWidget(i);
      if (w instanceof DayCheckBox) {
        ((DayCheckBox) w).setValue(checked);
        dayCheckBoxHandler.onClick((DayCheckBox) w);
      }
    }
  }
}
