package com.ensisa.login.client;


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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DisponibiliteScreen extends Composite {
	
	private String username ="";
	private String role ="";
	private LoginScreen loginScreen;
	private Jury jury;
	private Label labelrole;
	private Statistiques statistiques;

	public DisponibiliteScreen(String username,String role)
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

	   public void setLoginScreen()    {
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
		
		Label lblHome = new Label("Home !");
		lblHome.setStyleName("h1");
		rootPanel.add(lblHome, 10, 44);
		
		Label lblAuditorium = new Label("Disponibilites");
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
		
		Calendar calendar = new Calendar();
		calendar.setSize("430px", "315px");
		calendar.setShowWorkday(true);
		calendar.setShowDateChooser(false);
		calendar.setShowDatePickerButton(true);
		calendar.setWorkdayStart("7");
		calendar.setWorkdayEnd("18");

		rootPanel.add(calendar, 10, 65);
	}
}
