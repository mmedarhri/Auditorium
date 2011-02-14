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
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.InlineLabel;

/**
* Entry point classes define <code>onModuleLoad()</code>.
*/

/**
*
* @author Mohamed MEDARHRI
*/
public class HomeScreenAuditeur extends Composite {

private String username ="";
private String role ="";
private LoginScreen loginScreen;
private Jury jury;
private Label labelrole;


public HomeScreenAuditeur(String username,String role)
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
/**
* Clear whatever is on the screen
//Replace it with this home screen
*/
}



public void Homeview(String username)
{

RootPanel rootPanel = RootPanel.get();
rootPanel.clear();
rootPanel.setSize("380", "360");
rootPanel.setStyleName("gwt-RichTextToolbar .gwt-ToggleButton-down");
rootPanel.setStylePrimaryName("body");

Hyperlink hyperlink = new Hyperlink("Home !", false, "newHistoryToken");
rootPanel.add(hyperlink, 10, 42);

Label lblAuditorium = new Label("Auditeur");
rootPanel.add(lblAuditorium, 214, 10);
lblAuditorium.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
lblAuditorium.setStyleName("gwt-RichTextToolbar-new");
lblAuditorium.setSize("231px", "40px");

FlexTable flexTable = new FlexTable();
rootPanel.add(flexTable, 490, 10);
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

TabPanel tabPanel = new TabPanel();
rootPanel.add(tabPanel, 10, 76);
tabPanel.setSize("640px", "414px");

AbsolutePanel absolutePanelCalendrier = new AbsolutePanel();
tabPanel.add(absolutePanelCalendrier, "Disponibilités Calendrier", false);
absolutePanelCalendrier.setSize("645px", "475px");

Calendar calendar = new Calendar();
calendar.setSize("495px", "295px");
calendar.setWorkdayStart("7");
calendar.setWorkdayEnd("18");
calendar.setShowWorkday(true);
calendar.setShowDatePickerButton(true);
calendar.setShowDateChooser(false);
absolutePanelCalendrier.add(calendar, 0, 10);

AbsolutePanel absolutePanel = new AbsolutePanel();
tabPanel.add(absolutePanel, "Auditions", false);
absolutePanel.setSize("645px", "274px");

InlineLabel inlineLabel = new InlineLabel("Liste des auditions :");
absolutePanel.add(inlineLabel, 10, 21);
}
}