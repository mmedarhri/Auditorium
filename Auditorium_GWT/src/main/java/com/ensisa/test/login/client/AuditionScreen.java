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
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;

/**
* Entry point classes define <code>onModuleLoad()</code>.
*/
public class AuditionScreen extends Composite {

private String username ="";
private String role ="";
private LoginScreen loginScreen;
private Jury jury;
private Label labelrole;
private Statistiques statistiques;

public AuditionScreen(String username,String role)
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
calendar.setSize("450px", "300px");
calendar.setShowWorkday(true);
calendar.setShowDateChooser(false);
calendar.setShowDatePickerButton(true);
calendar.setWorkdayStart("7");
calendar.setWorkdayEnd("18");

rootPanel.add(calendar, 0, 0);

Label lblSession = new Label("Audition");
rootPanel.add(lblSession, 76, 0);
lblSession.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
lblSession.setStyleName("gwt-RichTextToolbar-new");
lblSession.setSize("194px", "40px");

FlexTable flexTable = new FlexTable();
rootPanel.add(flexTable, 287, 0);
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

Hyperlink Home = new Hyperlink("Home", false, "newHistoryToken");
rootPanel.add(Home, 10, 42);
AbsolutePanel absolutePanel = new AbsolutePanel();
rootPanel.add(absolutePanel, 10, 63);
absolutePanel.setSize("430px", "227px");
Label label_1 = new Label("Date");
absolutePanel.add(label_1, 10, 8);
label_1.setSize("27px", "27px");
DateBox dateBox = new DateBox();
absolutePanel.add(dateBox, 103, 8);
RadioButton radioButton = new RadioButton("new name", "New radio button");
radioButton.setHTML("matin");
absolutePanel.add(radioButton, 103, 41);
RadioButton radioButton_1 = new RadioButton("new name", "New radio button");
radioButton_1.setHTML("apres - midi");
absolutePanel.add(radioButton_1, 103, 67);
Label label = new Label("Candidat");
absolutePanel.add(label, 9, 127);
Label label_2 = new Label("Jury");
absolutePanel.add(label_2, 192, 125);
ListBox listBox_jury = new ListBox();
absolutePanel.add(listBox_jury, 238, 125);
listBox_jury.setVisibleItemCount(5);
ListBox listBox_candidats = new ListBox();
absolutePanel.add(listBox_candidats, 95, 127);
listBox_candidats.setVisibleItemCount(5);
Button button = new Button("Creer");
absolutePanel.add(button, 369, 193);
}
}