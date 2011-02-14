package com.ensisa.test.login.server;

import javax.servlet.http.HttpSession;


import com.ensisa.test.login.client.MyService;
import com.ensisa.test.login.client.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MyServiceImpl extends RemoteServiceServlet implements MyService {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final String USER_SESSION = "GWTAppUser";
private JDOHbaseUser jdoUser;





public void setUserInSession(User user) {
HttpSession session = getThreadLocalRequest().getSession();
session.setAttribute(USER_SESSION, user);
}

public User getUserFromSession() {
HttpSession session = getThreadLocalRequest().getSession();
return (User) session.getAttribute(USER_SESSION);
}


public User connectToUserTable(String username, String password) {
 jdoUser = new JDOHbaseUser();
try {
	return jdoUser.getUser(username, password);
 
} catch (Exception e) {
 e.printStackTrace();
return null;
}
}

public User checkLogin(String userName, String password) {
User user = connectToUserTable(userName, password);
if ((user != null)) {
setUserInSession(user);
return user;
} else
System.out.println("user is null");
return null;

}
public static void main(String[] args)
{
	MyServiceImpl impl = new  MyServiceImpl();
	User user = impl.checkLogin("mm", "essaim");
	System.out.println(user);
}

}