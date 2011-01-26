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
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
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
		rootPanel.setSize("380", "360");
		rootPanel.setStyleName("gwt-RichTextToolbar .gwt-ToggleButton-down");
		rootPanel.setStylePrimaryName("body");
		
		Label lblAuditorium = new Label("Auditorium ");
		rootPanel.add(lblAuditorium, 214, 10);
		lblAuditorium.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblAuditorium.setStyleName("gwt-RichTextToolbar-new");
		lblAuditorium.setSize("231px", "40px");
		
		FlowPanel flowPanel = new FlowPanel();
		flowPanel.setStyleName("gwt-DialogBox");
		rootPanel.add(flowPanel, 56, 101);
		flowPanel.setSize("360px", "30px");
		
		FileUpload fileUpload = new FileUpload();
		flowPanel.add(fileUpload);
		fileUpload.setSize("276px", "23px");
		
		Label label = new Label("");
		flowPanel.add(label);
		
		com.smartgwt.client.widgets.Button button = new com.smartgwt.client.widgets.Button("Upload");
		rootPanel.add(button, 56, 141);
	}
}
