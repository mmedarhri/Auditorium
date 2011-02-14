package com.ensisa.hbase.client;


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

private String username ="";

public BDDScreenManager(String username)
{
this.username=username;

BDDManagerview(username);
}



public String getUsername() {
return username;
}


public void setUsername(String username) {
this.username = username;
}



public void BDDManagerview(String username)
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

InlineLabel inlineLabel_14 = new InlineLabel("Upload Candidats to DB");
UploadCandidats.add(inlineLabel_14, 0, 26);

FileUpload fileUpload_1 = new FileUpload();
UploadCandidats.add(fileUpload_1, 167, 25);

Button button_2 = new Button("Upload");
UploadCandidats.add(button_2, 167, 79);

AbsolutePanel AddUser = new AbsolutePanel();
tabPanel.add(AddUser, "Add User", false);
AddUser.setSize("493px", "155px");

InlineLabel inlineLabel = new InlineLabel("Login");
AddUser.add(inlineLabel, 0, 0);
inlineLabel.setSize("31px", "15px");

TextBox textBox = new TextBox();
AddUser.add(textBox, 100, 0);
textBox.setSize("159px", "27px");

InlineLabel inlineLabel_1 = new InlineLabel("Password");
AddUser.add(inlineLabel_1, 3, 33);
inlineLabel_1.setSize("57px", "15px");

PasswordTextBox passwordTextBox = new PasswordTextBox();
AddUser.add(passwordTextBox, 100, 33);
passwordTextBox.setSize("159px", "27px");

InlineLabel inlineLabel_2 = new InlineLabel("Role");
AddUser.add(inlineLabel_2, 0, 72);
inlineLabel_2.setSize("26px", "15px");

ListBox listBox = new ListBox();
AddUser.add(listBox, 100, 72);
listBox.setSize("165px", "23px");

Button button = new Button("Validate");
AddUser.add(button, 100, 121);
button.setSize("67px", "24px");

AbsolutePanel UserInfos = new AbsolutePanel();
tabPanel.add(UserInfos, "User Information", false);
UserInfos.setSize("494px", "217px");

ListBox listBox_1 = new ListBox();
listBox_1.setVisibleItemCount(5);
UserInfos.add(listBox_1, 10, 10);
listBox_1.setSize("58px", "126px");

InlineLabel inlineLabel_3 = new InlineLabel("Nom");
UserInfos.add(inlineLabel_3, 74, 10);
inlineLabel_3.setSize("111px", "15px");

TextBox textBox_2 = new TextBox();
UserInfos.add(textBox_2, 175, 10);
textBox_2.setSize("147px", "3px");

InlineLabel inlineLabel_4 = new InlineLabel("Prénom");
UserInfos.add(inlineLabel_4, 70, 44);

TextBox textBox_3 = new TextBox();
UserInfos.add(textBox_3, 175, 39);

InlineLabel inlineLabel_5 = new InlineLabel("E-mail");
UserInfos.add(inlineLabel_5, 70, 79);

TextBox textBox_1 = new TextBox();
UserInfos.add(textBox_1, 175, 72);

InlineLabel inlineLabel_6 = new InlineLabel("Telephone");
UserInfos.add(inlineLabel_6, 70, 110);

TextBox textBox_4 = new TextBox();
UserInfos.add(textBox_4, 175, 105);

InlineLabel inlineLabel_7 = new InlineLabel("Adresse");
UserInfos.add(inlineLabel_7, 70, 142);

TextBox textBox_5 = new TextBox();
UserInfos.add(textBox_5, 175, 138);

InlineLabel inlineLabel_8 = new InlineLabel("Fonction");
UserInfos.add(inlineLabel_8, 74, 180);

ListBox listBox_2 = new ListBox();
UserInfos.add(listBox_2, 179, 168);
listBox_2.setSize("142px", "23px");

AbsolutePanel UpdateUser = new AbsolutePanel();
tabPanel.add(UpdateUser, "Update User", false);
UpdateUser.setSize("494px", "217px");

PasswordTextBox passwordTextBox_1 = new PasswordTextBox();
UpdateUser.add(passwordTextBox_1, 175, 40);

PasswordTextBox passwordTextBox_2 = new PasswordTextBox();
UpdateUser.add(passwordTextBox_2, 175, 79);

InlineLabel inlineLabel_9 = new InlineLabel("User selected");
UpdateUser.add(inlineLabel_9, 70, 10);

InlineLabel inlineLabel_13 = new InlineLabel("New InlineLabel");
UpdateUser.add(inlineLabel_13, 175, 10);

ListBox listBox_3 = new ListBox();
listBox_3.setVisibleItemCount(5);
UpdateUser.add(listBox_3, 10, 10);
listBox_3.setSize("58px", "126px");

InlineLabel inlineLabel_10 = new InlineLabel("Old Password");
UpdateUser.add(inlineLabel_10, 70, 44);

InlineLabel inlineLabel_11 = new InlineLabel("New Password");
UpdateUser.add(inlineLabel_11, 70, 79);

InlineLabel inlineLabel_12 = new InlineLabel("Role");
UpdateUser.add(inlineLabel_12, 70, 110);

ListBox comboBox = new ListBox();
UpdateUser.add(comboBox, 175, 110);
comboBox.setSize("151px", "23px");

Button button_1 = new Button("Update");
UpdateUser.add(button_1, 175, 171);


}
}