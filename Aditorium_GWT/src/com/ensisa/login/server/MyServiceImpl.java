package com.ensisa.login.server;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.hadoop.hbase.client.HTable;


import com.ensisa.*;
import com.ensisa.login.client.MyService;
import com.ensisa.login.client.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MyServiceImpl extends RemoteServiceServlet implements
MyService {

private static final String USER_SESSION = "GWTAppUser";
private  final String USER_TABLE = "Users";
private HBaseConnector hbaseconnector;
private HTable users;
private Map map_users;
private static final long serialVersionUID = 1;
private String role;

public void setUserInSession(User user) {
HttpSession session = getThreadLocalRequest().getSession();
session.setAttribute(USER_SESSION, user);
}

public User getUserFromSession() {
HttpSession session = getThreadLocalRequest().getSession();
return (User) session.getAttribute(USER_SESSION);
}

@SuppressWarnings("rawtypes")
public Map connectToUserTable(String username,String password)
{
	 hbaseconnector = new HBaseConnector();
	 users = hbaseconnector.getHTable(USER_TABLE);
	 if(hbaseconnector.retrieveColumn(users,username)!=null)
	 {
		 map_users=hbaseconnector.retrieveColumn(users,username);
		 System.out.println("map_users not null");
		 return map_users;
	 }
	 else
		 System.out.println("map_users is null");
		 return null;
	 
}

public User checkLogin(String userName, String password) {
	
	if((connectToUserTable(userName,password)!=null))
	{
		System.out.println("Bonjour "+userName); 
		System.out.println("Vous etes connecte en tant que :"+hbaseconnector.getRole(map_users,"Infos"));
		 	
    System.out.println("password "+password);
    role = hbaseconnector.getRole(map_users,"Infos");
	User user = new User();
    user.setUser(userName, password);
    user.setRole(role);
    setUserInSession(user);
   return user;
} else
	 System.out.println("user is null");
   return null;

}

}