package com.ensisa.login.client;

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


/**
 * Entry point classes define <code>onModuleLoad()</code>.
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
		rootPanel.add(pushButton, 10, 62);
		pushButton.setSize("62px", "15px");
		
		DateBox dateBox_1 = new DateBox();
		dateBox_1.setValue(new Date(1296331130719L));
		rootPanel.add(dateBox_1, 409, 62);
		dateBox_1.setSize("118px", "15px");
		dateBox_1.setFormat(new DefaultFormat(DateTimeFormat.getShortDateFormat()));
		
		@SuppressWarnings("deprecation")
		DisclosurePanel disclosurePanel = new DisclosurePanel("Jury n° I", false);
		rootPanel.add(disclosurePanel, 10, 91);
		disclosurePanel.setSize("231px", "90px");
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		disclosurePanel.setContent(decoratorPanel);
		decoratorPanel.setSize("271px", "204px");
		
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
     public void setHomeScreen(User user)    {
       
    	 //switch case sur le role
    	  homeScreen=new HomeScreenSuperAdmin(user.getUserName(),user.getRole());
    	 //homeScreen.setUsername();
    	 
    	 
         /**
         * Clear whatever is on the screen
         
       RootPanel.get().clear();
         
        //Replace it with this home screen
         
       RootPanel.get().add(homeScreen);
       
       */
     }
}
