package com.ensisa.test.login.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.layout.FlowLayout;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;


/**
* Entry point classes define <code>onModuleLoad()</code>.
*/

/**
*
* @author Mohamed MEDARHRI
*/
public class Jury implements EntryPoint {

private DateBox dateBox;
private Date currentDate;
private HomeScreenSuperAdmin homeScreen;


public void JuryView()
{
RootPanel rootPanel = RootPanel.get();
rootPanel.clear();
Label lblJuryCreation = new Label("Jury Composition");
lblJuryCreation.setDirection(Direction.LTR);
lblJuryCreation.setStyleName("gwt-RichTextToolbar-new");
lblJuryCreation.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
rootPanel.add(lblJuryCreation, 121, 10);
lblJuryCreation.setSize("330px", "15px");

PushButton pushButton = new PushButton("Home");
pushButton.addClickHandler(new ClickHandler() {
public void onClick(ClickEvent event) {
//setHomeScreen(user);
//back to homescreen

}
});

FlexTable flexTable = new FlexTable();
flexTable.setStyleName("gwt-TabBar");
flexTable.setBorderWidth(0);
rootPanel.add(flexTable, 489, 10);
flexTable.setSize("185px", "68px");

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

Button button = new Button("Logout");
flexTable.setWidget(1, 1, button);
button.setSize("83", "25");
rootPanel.add(pushButton, 10, 62);
pushButton.setSize("62px", "15px");

DateBox dateBox_1 = new DateBox();
dateBox_1.setValue(new Date(1296331130719L));
rootPanel.add(dateBox_1, 485, 86);
dateBox_1.setSize("177px", "15px");
dateBox_1.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));

TabPanel tabPanel = new TabPanel();
rootPanel.add(tabPanel, 10, 130);
tabPanel.setSize("660px", "237px");

AbsolutePanel Jury1Item = new AbsolutePanel();
tabPanel.add(Jury1Item, "Jury I", false);
Jury1Item.setSize("659px", "217px");

InlineLabel LabelNombreAuditeurs = new InlineLabel("Selectionnez le nombre d'auditeurs :");
Jury1Item.add(LabelNombreAuditeurs, 0, 10);

RadioButton radioButton2 = new RadioButton("2", "2");
Jury1Item.add(radioButton2, 225, 10);

RadioButton radioButton_3 = new RadioButton("3", "3");
Jury1Item.add(radioButton_3, 225, 36);

RadioButton radioButton_4 = new RadioButton("4", "4");
Jury1Item.add(radioButton_4, 225, 64);

InlineLabel inlineLabel = new InlineLabel("Auditeurs disponibles");
Jury1Item.add(inlineLabel, 8, 90);
inlineLabel.setSize("136px", "15px");

InlineLabel inlineLabel_20 = new InlineLabel("Auditeurs selectionés");
Jury1Item.add(inlineLabel_20, 150, 90);
inlineLabel_20.setSize("136px", "15px");

ListBox listBox_7 = new ListBox();
Jury1Item.add(listBox_7, 38, 115);
listBox_7.setVisibleItemCount(5);

ListBox listBox_8 = new ListBox();
Jury1Item.add(listBox_8, 182, 115);
listBox_8.setSize("62px", "85px");

Button button_CreerJury1 = new Button("Create");
Jury1Item.add(button_CreerJury1, 319, 180);
button_CreerJury1.setSize("71px", "20px");

AbsolutePanel Jury2Item = new AbsolutePanel();
tabPanel.add(Jury2Item, "Jury II", false);
Jury2Item.setSize("659px", "217px");

InlineLabel inlineLabel_1 = new InlineLabel("Selectionnez le nombre d'auditeurs :");
Jury2Item.add(inlineLabel_1, 0, 0);
inlineLabel_1.setSize("210px", "15px");

RadioButton radioButton = new RadioButton("2", "2");
Jury2Item.add(radioButton, 225, 0);
radioButton.setSize("29px", "22px");

RadioButton radioButton_1 = new RadioButton("3", "3");
Jury2Item.add(radioButton_1, 225, 26);
radioButton_1.setSize("29px", "22px");

RadioButton radioButton_2 = new RadioButton("4", "4");
Jury2Item.add(radioButton_2, 225, 54);
radioButton_2.setSize("29px", "22px");

InlineLabel inlineLabel_2 = new InlineLabel("Auditeurs disponibles");
Jury2Item.add(inlineLabel_2, 8, 80);
inlineLabel_2.setSize("136px", "15px");

InlineLabel inlineLabel_3 = new InlineLabel("Auditeurs selectionés");
Jury2Item.add(inlineLabel_3, 150, 80);
inlineLabel_3.setSize("136px", "15px");

ListBox listBox = new ListBox();
listBox.setVisibleItemCount(5);
Jury2Item.add(listBox, 38, 105);
listBox.setSize("62px", "85px");

ListBox listBox_9 = new ListBox();
Jury2Item.add(listBox_9, 182, 105);
listBox_9.setSize("62px", "85px");

Button button_1 = new Button("Create");
Jury2Item.add(button_1, 319, 170);
button_1.setSize("71px", "20px");

AbsolutePanel Jury3Item = new AbsolutePanel();
tabPanel.add(Jury3Item, "Jury III", false);
Jury3Item.setSize("658px", "261px");

InlineLabel inlineLabel_4 = new InlineLabel("Selectionnez le nombre d'auditeurs :");
Jury3Item.add(inlineLabel_4, 0, 0);
inlineLabel_4.setSize("210px", "15px");

RadioButton radioButton_5 = new RadioButton("2", "2");
Jury3Item.add(radioButton_5, 225, 0);
radioButton_5.setSize("29px", "22px");

RadioButton radioButton_6 = new RadioButton("3", "3");
Jury3Item.add(radioButton_6, 225, 26);
radioButton_6.setSize("29px", "22px");

RadioButton radioButton_7 = new RadioButton("4", "4");
Jury3Item.add(radioButton_7, 225, 54);
radioButton_7.setSize("29px", "22px");

InlineLabel inlineLabel_5 = new InlineLabel("Auditeurs disponibles");
Jury3Item.add(inlineLabel_5, 8, 80);
inlineLabel_5.setSize("136px", "15px");

InlineLabel inlineLabel_6 = new InlineLabel("Auditeurs selectionés");
Jury3Item.add(inlineLabel_6, 150, 80);
inlineLabel_6.setSize("136px", "15px");

ListBox listBox_1 = new ListBox();
listBox_1.setVisibleItemCount(5);
Jury3Item.add(listBox_1, 38, 105);
listBox_1.setSize("62px", "85px");

ListBox listBox_2 = new ListBox();
Jury3Item.add(listBox_2, 182, 105);
listBox_2.setSize("62px", "85px");

Button button_2 = new Button("Create");
Jury3Item.add(button_2, 319, 170);
button_2.setSize("71px", "20px");

AbsolutePanel Jury4Item = new AbsolutePanel();
tabPanel.add(Jury4Item, "Jury IV", false);
Jury4Item.setSize("659px", "217px");

InlineLabel inlineLabel_7 = new InlineLabel("Selectionnez le nombre d'auditeurs :");
Jury4Item.add(inlineLabel_7, 0, 0);
inlineLabel_7.setSize("210px", "15px");

RadioButton radioButton_8 = new RadioButton("2", "2");
Jury4Item.add(radioButton_8, 225, 0);
radioButton_8.setSize("29px", "22px");

RadioButton radioButton_9 = new RadioButton("3", "3");
Jury4Item.add(radioButton_9, 225, 26);
radioButton_9.setSize("29px", "22px");

RadioButton radioButton_10 = new RadioButton("4", "4");
Jury4Item.add(radioButton_10, 225, 54);
radioButton_10.setSize("29px", "22px");

InlineLabel inlineLabel_8 = new InlineLabel("Auditeurs disponibles");
Jury4Item.add(inlineLabel_8, 8, 80);
inlineLabel_8.setSize("136px", "15px");

InlineLabel inlineLabel_9 = new InlineLabel("Auditeurs selectionés");
Jury4Item.add(inlineLabel_9, 150, 80);
inlineLabel_9.setSize("136px", "15px");

ListBox listBox_3 = new ListBox();
listBox_3.setVisibleItemCount(5);
Jury4Item.add(listBox_3, 38, 105);
listBox_3.setSize("62px", "85px");

ListBox listBox_4 = new ListBox();
Jury4Item.add(listBox_4, 182, 105);
listBox_4.setSize("62px", "85px");

Button button_3 = new Button("Create");
Jury4Item.add(button_3, 319, 170);
button_3.setSize("71px", "20px");

AbsolutePanel JuryItem5 = new AbsolutePanel();
tabPanel.add(JuryItem5, "Jury V", false);
JuryItem5.setSize("659px", "243px");

InlineLabel inlineLabel_10 = new InlineLabel("Selectionnez le nombre d'auditeurs :");
JuryItem5.add(inlineLabel_10, 0, 0);
inlineLabel_10.setSize("210px", "15px");

RadioButton radioButton_11 = new RadioButton("2", "2");
JuryItem5.add(radioButton_11, 225, 0);
radioButton_11.setSize("29px", "22px");

RadioButton radioButton_12 = new RadioButton("3", "3");
JuryItem5.add(radioButton_12, 225, 26);
radioButton_12.setSize("29px", "22px");

RadioButton radioButton_13 = new RadioButton("4", "4");
JuryItem5.add(radioButton_13, 225, 54);
radioButton_13.setSize("29px", "22px");

InlineLabel inlineLabel_11 = new InlineLabel("Auditeurs disponibles");
JuryItem5.add(inlineLabel_11, 8, 80);
inlineLabel_11.setSize("136px", "15px");

InlineLabel inlineLabel_12 = new InlineLabel("Auditeurs selectionés");
JuryItem5.add(inlineLabel_12, 150, 80);
inlineLabel_12.setSize("136px", "15px");

ListBox listBox_5 = new ListBox();
listBox_5.setVisibleItemCount(5);
JuryItem5.add(listBox_5, 38, 105);
listBox_5.setSize("62px", "85px");

ListBox listBox_6 = new ListBox();
JuryItem5.add(listBox_6, 182, 105);
listBox_6.setSize("62px", "85px");

Button button_4 = new Button("Create");
JuryItem5.add(button_4, 319, 170);
button_4.setSize("71px", "20px");

AbsolutePanel JuryItem6 = new AbsolutePanel();
tabPanel.add(JuryItem6, "Jury VI", false);
JuryItem6.setSize("659px", "217px");

InlineLabel inlineLabel_13 = new InlineLabel("Selectionnez le nombre d'auditeurs :");
JuryItem6.add(inlineLabel_13, 0, 0);
inlineLabel_13.setSize("210px", "15px");

RadioButton radioButton_14 = new RadioButton("2", "2");
JuryItem6.add(radioButton_14, 225, 0);
radioButton_14.setSize("29px", "22px");

RadioButton radioButton_15 = new RadioButton("3", "3");
JuryItem6.add(radioButton_15, 225, 26);
radioButton_15.setSize("29px", "22px");

RadioButton radioButton_16 = new RadioButton("4", "4");
JuryItem6.add(radioButton_16, 225, 54);
radioButton_16.setSize("29px", "22px");

InlineLabel inlineLabel_14 = new InlineLabel("Auditeurs disponibles");
JuryItem6.add(inlineLabel_14, 8, 80);
inlineLabel_14.setSize("136px", "15px");

InlineLabel inlineLabel_15 = new InlineLabel("Auditeurs selectionés");
JuryItem6.add(inlineLabel_15, 150, 80);
inlineLabel_15.setSize("136px", "15px");

ListBox listBox_10 = new ListBox();
listBox_10.setVisibleItemCount(5);
JuryItem6.add(listBox_10, 38, 105);
listBox_10.setSize("62px", "85px");

ListBox listBox_11 = new ListBox();
JuryItem6.add(listBox_11, 182, 105);
listBox_11.setSize("62px", "85px");

Button button_5 = new Button("Create");
JuryItem6.add(button_5, 319, 170);
button_5.setSize("71px", "20px");

Label labelJuryID = new Label("JuryID");
labelJuryID.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

dateBox = new DateBox();
currentDate = new Date();
dateBox.setVisible(true);
dateBox.setTitle("Calendar");
dateBox.setValue(currentDate);
}

public void onModuleLoad() {
JuryView();
}

/**
* Method to set the home page
* @param user
*/
     public void setHomeScreen(User user) {
       
     //switch case sur le role
     homeScreen=new HomeScreenSuperAdmin(user.getLogin(),user.getRole());
     //homeScreen.setUsername();
    
    
         /**
* Clear whatever is on the screen
RootPanel.get().clear();
//Replace it with this home screen
RootPanel.get().add(homeScreen);
*/
     }
}