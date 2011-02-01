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
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.DateChooser;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.smartgwt.client.widgets.menu.Menu;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Frame;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HomeScreenSuperAdmin extends Composite {
	
	private String username ="";
	private String role ="";
	private LoginScreen loginScreen;
	private Jury jury;
	private Label labelrole;

	public HomeScreenSuperAdmin(String username,String role)
	{
		this.username=username;
		this.role=role;
		Homeview(username,role);
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

	
		
		public void Homeview(String username,String role)
		{
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		rootPanel.setSize("380", "360");
		rootPanel.setStyleName("gwt-RichTextToolbar .gwt-ToggleButton-down");
		rootPanel.setStylePrimaryName("dialogVPanel");
		
		Hyperlink hyperlink = new Hyperlink("Home !", false, "newHistoryToken");
		rootPanel.add(hyperlink, 10, 35);
		
		Label lblAuditorium = new Label("Auditorium ");
		rootPanel.add(lblAuditorium, 214, 10);
		lblAuditorium.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblAuditorium.setStyleName("gwt-RichTextToolbar-new");
		lblAuditorium.setSize("231px", "40px");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		rootPanel.add(absolutePanel, 10, 64);
		absolutePanel.setSize("360px", "21px");
		
		MenuBar menuBar = new MenuBar(false);
		menuBar.setTitle("TEST");
		absolutePanel.add(menuBar, 0, 0);
		MenuBar menuBar_2 = new MenuBar(true);
		
		MenuItem menuItem_6 = new MenuItem("Session", false, menuBar_2);
		
		MenuItem menuItem = new MenuItem("Creer une session", false, (Command) null);
		menuBar_2.addItem(menuItem);
		
		MenuItemSeparator separator_1 = new MenuItemSeparator();
		menuBar_2.addSeparator(separator_1);
		
		MenuItem menuItem_7 = new MenuItem("Statistiques", false, (Command) null);
		menuBar_2.addItem(menuItem_7);
		menuBar.addItem(menuItem_6);
		
		MenuBar menuBar_1 = new MenuBar(false);
		absolutePanel.add(menuBar_1, 146, 0);
		MenuBar menuBar_6 = new MenuBar(true);
		
		MenuItem menuItem_1 = new MenuItem("Auditions", false, menuBar_6);
		
		MenuItem menuItem_8 = new MenuItem("Creer une audition", false, (Command) null);
		menuBar_6.addItem(menuItem_8);
		
		MenuItemSeparator separator_2 = new MenuItemSeparator();
		menuBar_6.addSeparator(separator_2);
		
		MenuItem menuItem_9 = new MenuItem("Liste des auditions", false, (Command) null);
		menuBar_6.addItem(menuItem_9);
		
		MenuItemSeparator separator_3 = new MenuItemSeparator();
		menuBar_6.addSeparator(separator_3);
		
		MenuItem menuItem_10 = new MenuItem("Modifier une audition", false, (Command) null);
		menuBar_6.addItem(menuItem_10);
		menuBar_1.addItem(menuItem_1);
		
		MenuBar menuBar_5 = new MenuBar(false);
		absolutePanel.add(menuBar_5, 280, 0);
		menuBar_5.setSize("80px", "19px");
		MenuBar menuBar_4 = new MenuBar(true);
		
		MenuItem menuItem_2 = new MenuItem("BdD Manager", false, menuBar_4);
		
		MenuItem menuItem_4 = new MenuItem("Gestion des Candidats", false, (Command) null);
		menuBar_4.addItem(menuItem_4);
		
		MenuItemSeparator separator = new MenuItemSeparator();
		menuBar_4.addSeparator(separator);
		
		MenuItem menuItem_5 = new MenuItem("Gestion des Salles", false, (Command) null);
		menuBar_4.addItem(menuItem_5);
		menuBar_5.addItem(menuItem_2);
		
		FlexTable flexTable = new FlexTable();
		rootPanel.add(flexTable, 490, 10);
		flexTable.setStyleName("gwt-TabBar");
		flexTable.setBorderWidth(0);
		flexTable.setSize("163px", "47px");
		
		Label lblWelcome = new Label("Welcome ");
		lblWelcome.setDirection(Direction.RTL);
		lblWelcome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
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
		
		Label lblConnectedAs = new Label("connected as");
		lblConnectedAs.setDirection(Direction.RTL);
		lblConnectedAs.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		flexTable.setWidget(1, 0, lblConnectedAs);
		
		labelrole = new Label(role);
		labelrole.setStyleName("gwt-Labelrole");
		flexTable.setWidget(1, 1, labelrole);
		flexTable.setWidget(2, 1, buttonlogout);
		buttonlogout.setSize("83", "25");
		flexTable.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(2, 1, HasVerticalAlignment.ALIGN_BOTTOM);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		
		DateChooser dateChooser = new DateChooser();
		rootPanel.add(dateChooser, 500, 97);
	}
	public Label getrole() {
		return labelrole;
	}
}
