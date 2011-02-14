package com.ensisa.test.login.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Statistiques implements EntryPoint {
	
	
	public void StatistiquesView()
	{
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		AbsolutePanel absolutePanel = new AbsolutePanel();
		rootPanel.add(absolutePanel, 10, 10);
		absolutePanel.setSize("430px", "280px");
		Label lblAusditoriumStatistiques = new Label("Auditorium Statistiques");
		lblAusditoriumStatistiques.setSize("136px", "16px");
		lblAusditoriumStatistiques.setStyleName("gwt-DisclosurePanel .header");
		absolutePanel.add(lblAusditoriumStatistiques, 147, 48);
	
	}
	
	public void onModuleLoad() {
		
		StatistiquesView();
	}
}
