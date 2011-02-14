package com.ensisa.test.login.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
* Entry point classes define <code>onModuleLoad()</code>.
*/
public class SessionScreen extends Composite {

private String username ="";
private String role ="";
private LoginScreen loginScreen;
private Jury jury;
private Label labelrole;
private Statistiques statistiques;

public SessionScreen(String username,String role)
{
this.username=username;
this.role=role;
Homeview(username);
}



public String getUsername() {
return username;
}


public void setUsername(String username) {
this.username = username;
}

public void setLoginScreen() {
RootPanel.get().clear();
loginScreen=new LoginScreen();
RootPanel.get().add(loginScreen);
}

public void Homeview(String username)
{

RootPanel rootPanel = RootPanel.get();
rootPanel.clear();
rootPanel.setSize("380", "360");
rootPanel.setStyleName("gwt-RichTextToolbar .gwt-ToggleButton-down");
rootPanel.setStylePrimaryName("body");

Calendar calendar = new Calendar();
calendar.setSize("450px", "315px");
calendar.setShowWorkday(true);
calendar.setShowDateChooser(false);
calendar.setShowDatePickerButton(true);
calendar.setWorkdayStart("7");
calendar.setWorkdayEnd("18");

rootPanel.add(calendar, 0, 0);

Hyperlink Home = new Hyperlink("Home", false, "newHistoryToken");
rootPanel.add(Home, 10, 42);

Label lblSession = new Label("Session");
rootPanel.add(lblSession, 80, 10);
lblSession.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
lblSession.setStyleName("gwt-RichTextToolbar-new");
lblSession.setSize("231px", "40px");

FlexTable flexTable = new FlexTable();
rootPanel.add(flexTable, 287, 10);
flexTable.setStyleName("gwt-TabBar");
flexTable.setBorderWidth(0);
flexTable.setSize("163px", "47px");

Label lblWelcome = new Label("Welcome ");
lblWelcome.setDirection(Direction.RTL);
lblWelcome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
flexTable.setWidget(0, 0, lblWelcome);
lblWelcome.setSize("78px", "15px");

Label logged = new Label(username);
logged.setStyleName("gwt-CheckBox-Login");
logged.setDirection(Direction.RTL);
logged.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
flexTable.setWidget(0, 1, logged);
logged.setWidth("75px");
flexTable.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
flexTable.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);

Button buttonlogout = new Button("Logout");
buttonlogout.addClickHandler(new ClickHandler() {
public void onClick(ClickEvent event) {
setLoginScreen();

}
});
flexTable.setWidget(1, 1, buttonlogout);
buttonlogout.setSize("83", "25");
flexTable.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);
flexTable.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_BOTTOM);
AbsolutePanel absolutePanel = new AbsolutePanel();
rootPanel.add(absolutePanel, 10, 80);
absolutePanel.setSize("430px", "210px");
Label label = new Label("Nom de la session");
absolutePanel.add(label, 10, 10);
Label label_1 = new Label("Debut");
label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
label_1.setDirection(Direction.RTL);
label_1.setDirectionEstimator(false);
absolutePanel.add(label_1, 21, 40);
label_1.setSize("34px", "30px");
DateBox dateBox = new DateBox();
absolutePanel.add(dateBox, 70, 43);
dateBox.setSize("147px", "15px");
Label label_2 = new Label("Fin");
absolutePanel.add(label_2, 20, 91);
DateBox dateBox_1 = new DateBox();
absolutePanel.add(dateBox_1, 70, 91);
Button button = new Button("Creer");
absolutePanel.add(button, 369, 176);
}
}