package com.ensisa.login.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.i18n.client.HasDirection.Direction;

public class LoginScreen extends Composite {
    
	private PasswordTextBox passwordTextBox;
	private TextBox textBox ;
	
	public LoginScreen() {
		{
			VerticalPanel verticalPanel = new VerticalPanel();
			verticalPanel.setSpacing(100);
			verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
			initWidget(verticalPanel);
			verticalPanel.setSize("420px", "320px");
			{
				InlineLabel nlnlblAuditoriumAuthentification = new InlineLabel("Auditorium authentification");
				nlnlblAuditoriumAuthentification.setStyleName("gwt-RichTextToolbar-new");
				nlnlblAuditoriumAuthentification.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
				nlnlblAuditoriumAuthentification.setDirection(Direction.LTR);
				verticalPanel.add(nlnlblAuditoriumAuthentification);
				nlnlblAuditoriumAuthentification.setSize("472px", "39px");
			}
			{
				FlexTable flexTable = new FlexTable();
				verticalPanel.add(flexTable);
				flexTable.setSize("403px", "153px");
				{
					InlineLabel nlnlblSignUpTo = new InlineLabel("Sign up to your account please");
					flexTable.setWidget(0, 1, nlnlblSignUpTo);
					nlnlblSignUpTo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
					nlnlblSignUpTo.setDirection(Direction.LTR);
					nlnlblSignUpTo.setStyleName("sendButton");
					nlnlblSignUpTo.setSize("472px", "39px");
				}
				{
					Label label = new Label("Username:");
					label.setStyleName("gwt-Label-Login");
					flexTable.setWidget(1, 0, label);
				}
				{
					textBox = new TextBox();
					flexTable.setWidget(1, 1, textBox);
				}
				{
					Label label = new Label("Password:");
					label.setStyleName("gwt-Label-Login");
					flexTable.setWidget(2, 0, label);
				}
				{
					passwordTextBox = new PasswordTextBox();
					flexTable.setWidget(2, 1, passwordTextBox);
				}
				{
					CheckBox checkBox = new CheckBox("Remember me on this computer");
					checkBox.setStyleName("serverResponseLabelError");
					flexTable.setWidget(3, 1, checkBox);
				}
				{
					Button button = new Button("New button");
					button.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							if(passwordTextBox.getText().isEmpty() && textBox.getText().isEmpty())
							{
								Window.alert("Username and Password cannot be empty ! , please retry");
							}
							else
							{
								checkLogin(textBox.getText(),passwordTextBox.getText());
							}
						}
					});
					button.setText("Sign In");
					flexTable.setWidget(4, 1, button);
				}
				flexTable.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_MIDDLE);
			}
		}
	}
	
	
	
	private void setErrorText(String errorMessage)	{
	  //	lblError.setText(errorMessage);
	    }
    
    private MyServiceAsync getService() {
 	   MyServiceAsync svc = (MyServiceAsync) GWT.create(MyService.class);
 	   ServiceDefTarget endpoint = (ServiceDefTarget) svc;
 	   System.out.println("endpoint "+GWT.getModuleBaseURL() + "MyService");
 	   endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "MyService");
 	 
 	   return svc;
 	 }
    
    
    
    /*
	  * This method is called when the button is clicked
	  */
	 
	  @SuppressWarnings("rawtypes")
	private void checkLogin(String userName,String password) {
	    System.out.println("Checking login for "+userName);
	 
	    /**
	     * Async call to the server to check for login
	     */
	    
		AsyncCallback callback = new AsyncCallback() {
	       public void onSuccess(Object result) {
	           User user = (User) result;
	           
	           if (user != null) {
	               setErrorText("");
	               System.out.println(user.getUserName());
	           
	               // The user is authenticated, Set the home screen
	                Login.get().setHomeScreen(user);
	           } 
	           else {
	        	  // setErrorText("Invalid UserName or Password, retry please!");
		           Window.alert("Invalid UserName or Password, retry please!");
	        	  textBox.setText("");
	              passwordTextBox.setText("");
	               System.out.println("user is null");
	                }
	           System.out.println("Success");
	       }
	 
	       public void onFailure(Throwable ex) {
	            setErrorText("Error "+ex.getMessage());
	            System.out.println("Error "+ex.getMessage());
	            System.out.println("Failure");
	       }
	    };
	 
	    getService().checkLogin(userName, password,callback);
	}

}
