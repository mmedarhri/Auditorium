package com.ensisa.login.server;

import javax.servlet.http.HttpSession;
import com.ensisa.login.client.MyService;
import com.ensisa.login.client.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MyServiceImpl extends RemoteServiceServlet implements MyService {

	private static final String USER_SESSION = "GWTAppUser";
	private JDOHbaseUser jdoUser;

	private static final long serialVersionUID = 1;

	public void setUserInSession(User user) {
		HttpSession session = getThreadLocalRequest().getSession();
		session.setAttribute(USER_SESSION, user);
	}

	public User getUserFromSession() {
		HttpSession session = getThreadLocalRequest().getSession();
		return (User) session.getAttribute(USER_SESSION);
	}

	public User connectToUserTable(String username, String password) {
		User user = null;
		jdoUser = new JDOHbaseUser();
		try {
			user = jdoUser.getUser(username, password);
			return user;
		} catch (Exception e) {
			System.out.println("connectToUserTable() error " + e.toString());
			return null;
		}

	}

	public User checkLogin(String userName, String password) {

		User user = connectToUserTable(userName, password);
		if ((user != null)) {
			System.out.println("Bonjour " + user.getLogin());
			System.out.println("Vous etes connecte en tant que :"
					+ user.getRole());

			System.out.println("password " + user.getPassword());
			setUserInSession(user);
			return user;
		} else
			System.out.println("user is null");
		return null;

	}

}