package com.ensisa.login.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Login implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */

	private static Login singleton;

    public static Login get() {
	return singleton;
    }

		
	/**
	 * This is the entry point method.
	 */
    public void onModuleLoad() {
        singleton=this;
        setLoginScreen();
    }
   
    private void setLoginScreen()	{
        //Create the Login screen
        LoginScreen scrLogin=new LoginScreen();
        //Attach it to the root panel
        RootPanel.get().add(scrLogin);
    }
    
    
    /**
     * Method to set the home page
     * @param user
     */
     public void setHomeScreen(User user)    {
       
    	 System.out.println("Home screen of "+user.getRole());
    	 if(user.getRole().equalsIgnoreCase("super-admin"))
    	 {
    		 HomeScreenSuperAdmin homeScreen =new HomeScreenSuperAdmin(user.getUserName(),user.getRole());
    		 
    	 }
    	 if(user.getRole().equalsIgnoreCase("admin"))
    	 {
    		 HomeScreenAdmin homeScreen=new HomeScreenAdmin(user.getUserName(),user.getRole());
    	 }
    	 if(user.getRole().equalsIgnoreCase("auditeur"))
    	 {
    		 HomeScreenAuditeur homeScreen=new HomeScreenAuditeur(user.getUserName(),user.getRole());
    		 
    	 }
    	 if(user.getRole().equalsIgnoreCase("candidat"))
    	 {
    		 HomeScreenCandidat homeScreen=new HomeScreenCandidat(user.getUserName(),user.getRole());
    		 
    	 }
     }
    
   }
  