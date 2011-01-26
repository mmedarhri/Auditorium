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
		
		Label lblHome = new Label("Home !");
		lblHome.setStyleName("h1");
		rootPanel.add(lblHome, 10, 44);
		
		Label lblAuditorium = new Label("Auditorium ");
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
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setBorderWidth(2);
		rootPanel.add(verticalPanel, 10, 65);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setTitle("Liens");
		verticalPanel.setStylePrimaryName("gwt-DialogBox");
		verticalPanel.setStyleName("gwt-DisclosurePanel .header");
		verticalPanel.setSize("130px", "317px");
		
		Button buttonCalendrier = new Button("Button Calendrier");
		buttonCalendrier.setSize("83", "36px");
		buttonCalendrier.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Calendrier cliked");
				}
		});
		buttonCalendrier.setText("Calendrier");
		verticalPanel.add(buttonCalendrier);
		verticalPanel.setCellVerticalAlignment(buttonCalendrier, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setCellHorizontalAlignment(buttonCalendrier, HasHorizontalAlignment.ALIGN_CENTER);
		
		Button btnJury = new Button("Jury");
		btnJury.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Jury cliked");
			    jury= new Jury();	
			    jury.onModuleLoad();
			}
		});
		verticalPanel.add(btnJury);
		btnJury.setSize("83px", "36px");
		verticalPanel.setCellVerticalAlignment(btnJury, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setCellHorizontalAlignment(btnJury, HasHorizontalAlignment.ALIGN_CENTER);
		
		Button buttonSalles = new Button("Salles");
		buttonSalles.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Salles cliked");
			}
		});
		verticalPanel.add(buttonSalles);
		buttonSalles.setSize("83px", "36px");
		verticalPanel.setCellVerticalAlignment(buttonSalles, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setCellHorizontalAlignment(buttonSalles, HasHorizontalAlignment.ALIGN_CENTER);
		
		Button buttonStatistiques = new Button("Statistiques");
		buttonStatistiques.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				statistiques= new Statistiques();
				statistiques.onModuleLoad();
			}
		});
		verticalPanel.add(buttonStatistiques);
		buttonStatistiques.setSize("83px", "36px");
		verticalPanel.setCellVerticalAlignment(buttonStatistiques, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setCellHorizontalAlignment(buttonStatistiques, HasHorizontalAlignment.ALIGN_CENTER);
		
		Button buttonCandidats = new Button("Candidats");
		buttonCandidats.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Candidats cliked");
			}
		});
		verticalPanel.add(buttonCandidats);
		buttonCandidats.setSize("83px", "36px");
		verticalPanel.setCellHorizontalAlignment(buttonCandidats, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setCellVerticalAlignment(buttonCandidats, HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button buttonBDD = new Button("BDD Manager");
		buttonBDD.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("BDD Manager cliked");
			}
		});
		verticalPanel.add(buttonBDD);
		buttonBDD.setSize("83px", "34px");
		verticalPanel.setCellVerticalAlignment(buttonBDD, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setCellHorizontalAlignment(buttonBDD, HasHorizontalAlignment.ALIGN_CENTER);
		
		DateBox dateBox = new DateBox();
		rootPanel.add(dateBox, 494, 65);
	}
}
