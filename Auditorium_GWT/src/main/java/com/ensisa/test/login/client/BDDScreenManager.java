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
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
* Entry point classes define <code>onModuleLoad()</code>.
*/

/**
*
* @author Mohamed MEDARHRI
*/
public class BDDScreenManager extends Composite {

private User user ;

public BDDScreenManager(User user)
{
this.user=user;

BDDManagerview(user);
}



public String getUsername() {
return user.getName();
}


public void setUsername(String username) {
user.setLogin(username);
}



public void BDDManagerview(User user)
{

RootPanel rootPanel = RootPanel.get();
rootPanel.clear();
rootPanel.setStyleName("gwt-RichTextToolbar .gwt-ToggleButton-down");
rootPanel.setStylePrimaryName("body");

Label lblAuditorium = new Label("Auditorium Manager");
rootPanel.add(lblAuditorium, 75, 10);
lblAuditorium.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
lblAuditorium.setStyleName("gwt-RichTextToolbar-new");
lblAuditorium.setSize("370px", "30px");

FlexTable flexTable = new FlexTable();
flexTable.setStyleName("gwt-TabBar");
flexTable.setBorderWidth(0);
rootPanel.add(flexTable, 571, 10);
flexTable.setSize("163px", "47px");

Label label = new Label("Welcome ");
label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
label.setDirection(Direction.RTL);
flexTable.setWidget(0, 0, label);
label.setSize("78px", "15px");

Label label_1 = new Label("<dynamic>");
label_1.setStyleName("gwt-CheckBox-Login");
label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
label_1.setDirection(Direction.RTL);
flexTable.setWidget(0, 1, label_1);
label_1.setWidth("75px");

Button button_3 = new Button("Logout");
flexTable.setWidget(1, 1, button_3);
button_3.setSize("83", "25");

TabPanel tabPanel = new TabPanel();
rootPanel.add(tabPanel, 10, 46);
tabPanel.setSize("495px", "237px");
tabPanel.getTabBar().addTab("User");

AbsolutePanel UploadCandidats = new AbsolutePanel();
tabPanel.add(UploadCandidats, "Upload Candidats", false);
UploadCandidats.setSize("493px", "217px");

InlineLabel nlnlblUploadCandidats = new InlineLabel("Upload Candidats");
UploadCandidats.add(nlnlblUploadCandidats, 0, 26);

FileUpload fileUpload_1 = new FileUpload();
fileUpload_1.setName("fileupload");
UploadCandidats.add(fileUpload_1, 167, 25);

Button button_2 = new Button("Upload");
button_2.addClickHandler(new ClickHandler() {
	public void onClick(ClickEvent event) {
		
		UploadAndPersistCandidats();
	}		
	
		});
UploadCandidats.add(button_2, 167, 79);

}



public void UploadAndPersistCandidats()
{
	/*if()
	{
	Window.alert("Username and Password cannot be empty ! , please retry");
	}
	else
	{
    Window.alert("Username and Password cannot be empty ! , please retry");
		
	}
*/
}



}

