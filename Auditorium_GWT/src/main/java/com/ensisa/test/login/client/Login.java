package com.ensisa.test.login.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
* Entry point classes define <code>onModuleLoad()</code>.
*/

/**
*
* @author Mohamed MEDARHRI
*/
public class Login implements EntryPoint {
/**
* The message displayed to the user when the server cannot be reached or
* returns an error.
*/

private static Login singleton;
private LoginScreen scrLogin;
private HomeScreenAdmin homeScreenAdmin;
private HomeScreenSuperAdmin homeScreensuperAdmin;
private HomeScreenAuditeur homeScreenAuditeur;

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
   
    private void setLoginScreen() {
        //Create the Login screen
        scrLogin=new LoginScreen();
        //Attach it to the root panel
        RootPanel.get().add(scrLogin);
    }
    
    
    /**
* Method to set the home page
* @param user
*/
     public void setHomeScreen(User user) {
 
    	 if(user.getRole().equalsIgnoreCase(User.SYSADMIN))
     {
     homeScreensuperAdmin =new HomeScreenSuperAdmin(user.getLogin(),user.getRole());
    
     }
     if(user.getRole().equalsIgnoreCase(User.SCHOOL_DIRECTION))
     {
     homeScreenAdmin=new HomeScreenAdmin(user.getLogin(),user.getRole());
     }
     if(user.getRole().equalsIgnoreCase(User.AUDITOR))
     {
     homeScreenAuditeur=new HomeScreenAuditeur(user.getLogin(),user.getRole());
    
     }   
     
     }
    
   }