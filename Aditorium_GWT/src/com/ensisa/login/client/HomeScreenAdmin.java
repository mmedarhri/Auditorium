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
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuItemSeparator;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HomeScreenAdmin extends Composite {
	
	private String username ="";
	private String role ="";
	private LoginScreen loginScreen;
	private Jury jury;
	private Label labelrole;
	private Statistiques statistiques;

	public HomeScreenAdmin(String username,String role)
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
		
		Label lblAuditorium = new Label("Auditorium ");
		rootPanel.add(lblAuditorium, 214, 10);
		lblAuditorium.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblAuditorium.setStyleName("gwt-RichTextToolbar-new");
		lblAuditorium.setSize("231px", "40px");
		
		Hyperlink hyperlink = new Hyperlink("Home !", false, "newHistoryToken");
		rootPanel.add(hyperlink, 10, 56);
		hyperlink.setSize("42px", "15px");
		
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
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		rootPanel.add(absolutePanel, 10, 85);
		absolutePanel.setSize("195px", "21px");
		
		MenuBar menuBar = new MenuBar(false);
		menuBar.setTitle("TEST");
		absolutePanel.add(menuBar, 0, 0);
		MenuBar menuBar_3 = new MenuBar(true);
		
		MenuItem menuItem = new MenuItem("Session", false, menuBar_3);
		
		MenuItem menuItem_3 = new MenuItem("Creer une session", false, (Command) null);
		menuBar_3.addItem(menuItem_3);
		menuBar.addItem(menuItem);
		
		MenuBar menuBar_1 = new MenuBar(false);
		absolutePanel.add(menuBar_1, 146, 0);
		MenuBar menuBar_4 = new MenuBar(true);
		
		MenuItem menuItem_1 = new MenuItem("Auditions", false, menuBar_4);
		
		MenuItem menuItem_4 = new MenuItem("Creer une audition", false, (Command) null);
		menuBar_4.addItem(menuItem_4);
		
		MenuItemSeparator separator = new MenuItemSeparator();
		menuBar_4.addSeparator(separator);
		
		MenuItem menuItem_5 = new MenuItem("Liste des auditions", false, (Command) null);
		menuBar_4.addItem(menuItem_5);
		
		MenuItemSeparator separator_1 = new MenuItemSeparator();
		menuBar_4.addSeparator(separator_1);
		
		MenuItem menuItem_6 = new MenuItem("Modifier une audition", false, (Command) null);
		menuBar_4.addItem(menuItem_6);
		menuBar_1.addItem(menuItem_1);
		
		DateBox dateBox = new DateBox();
		rootPanel.add(dateBox, 494, 65);
	}
}
