package com.ensisa.login.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.ensisa.login.client.User;

/**
 * 
 * @author Mohamed MEDARHRI
 */

public class JDOHbaseUser {

	private PersistenceManagerFactory pmf;
	private PersistenceManager pm;
	private Transaction tx;
	private File propsFile;
	private final String datanucleus = "datanucleus.properties";


	public JDOHbaseUser() {
		propsFile = new File(System.getProperty("user.dir")+"/"+datanucleus);
		System.out.println(propsFile.getAbsolutePath());
		// Create a PersistenceManagerFactory for this datastore
		pmf = JDOHelper.getPersistenceManagerFactory(propsFile);

	}


	// Persistence
	public void persister(Object obj) {
		pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Persisting " + obj.getClass().getSimpleName());
			pm.makePersistent(obj);

			tx.commit();
			System.out.println(obj.getClass().getSimpleName()
					+ " have been persisted");
		} catch (Exception e) {
			System.out.println("Error in persister() " + e.toString());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");

	}

	// Basic Extent of all user

	public void getExtent(Object obj) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Retrieving Extent for "
					+ obj.getClass().getSimpleName());
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(obj.getClass(), true);
			@SuppressWarnings("rawtypes")
			Iterator iter = e.iterator();
			while (iter.hasNext()) {
				Object object = iter.next();
				// if (object instanceof User)
				// {
				// User b = (User)obj;
				System.out.println("> object " + object.toString());
				// }
			}
			tx.commit();
		} catch (Exception e) {
			System.out.println("Exception thrown during retrieval of Extent : "
					+ e.getMessage());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
	}

	// vide la table User
	public void cleanOutUser() {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Supression de tous les User ");
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(User.class, true);
			Query query = pm.newQuery(e);
			// Query query = pm.newQuery("javax.jdo.query.JDOQL",
			// "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");

			System.out.println(query.toString());

			@SuppressWarnings("rawtypes")
			Collection c = (Collection) query.execute();
			// System.out.println("collection is empty : "+c.isEmpty());
			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				User user = (User) iter.next();
				query.deletePersistentAll(user);

			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
	}

	public void deleteUserByLogin(String login) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Supression du User ayant le login " + login);
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(User.class, true);
			Query query = pm.newQuery(e, "login==" + "'" + login + "'");
			// Query query = pm.newQuery("javax.jdo.query.JDOQL",
			// "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");

			System.out.println(query.toString());

			@SuppressWarnings("rawtypes")
			Collection c = (Collection) query.execute();
			// System.out.println("collection is empty : "+c.isEmpty());
			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				User user = (User) iter.next();
				query.deletePersistentAll(user);
			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
	}

	// update du role
	public void updateRole(String login, String role) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Ajout des infos du User " + login);
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(User.class, true);
			Query query = pm.newQuery(e, "login==" + "'" + login + "'");
			// Query query = pm.newQuery("javax.jdo.query.JDOQL",
			// "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");
			@SuppressWarnings("rawtypes")
			Collection c = (Collection) query.execute();
			// System.out.println("collection is empty : "+c.isEmpty());
			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				User user = (User) iter.next();

				user.setRole(role);

			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
	}

	// update du mot de passe
	public void updatePassword(String login, String password) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Update du mot de passe du User " + login);
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(User.class, true);
			Query query = pm.newQuery(e, "login==" + "'" + login + "'");
			// Query query = pm.newQuery("javax.jdo.query.JDOQL",
			// "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");
			@SuppressWarnings("rawtypes")
			Collection c = (Collection) query.execute();
			// System.out.println("collection is empty : "+c.isEmpty());
			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				User user = (User) iter.next();

				user.setPassword(password);

			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
	}

	// ajout d'infos supplementaire pour un user
	public void completeInfoUserByLogin(String login, String role) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Ajout des infos du User " + login);
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(User.class, true);
			Query query = pm.newQuery(e, "login==" + "'" + login + "'");
			// Query query = pm.newQuery("javax.jdo.query.JDOQL",
			// "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");
			@SuppressWarnings("rawtypes")
			Collection c = (Collection) query.execute();
			// System.out.println("collection is empty : "+c.isEmpty());
			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				User user = (User) iter.next();

				user.setRole(role);

			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
	}

	// ajout d'informations sur un user
	public void completeInfoUserByLogin(String login, String name,
			String prenom, String age, String adresse, String email) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Ajout des infos du User " + login);
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(User.class, true);
			Query query = pm.newQuery(e, "login==" + "'" + login + "'");
			// Query query = pm.newQuery("javax.jdo.query.JDOQL",
			// "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");
			@SuppressWarnings("rawtypes")
			Collection c = (Collection) query.execute();
			// System.out.println("collection is empty : "+c.isEmpty());
			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				User user = (User) iter.next();
				user.setName(name);
				user.setFirstname(prenom);
				user.setEmail(email);
				user.setAddress(adresse);
				user.setAge(age);

			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
	}

	// Perform some query operations
	public User getUserByLogin(String login) {
		User user = null;
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Recupere le user ayant le login " + login);
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(User.class, true);
			Query query = pm.newQuery(e, "login==" + "'" + login + "'");
			// Query query = pm.newQuery("javax.jdo.query.JDOQL",
			// "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");

			System.out.println(query.toString());

			@SuppressWarnings("rawtypes")
			Collection c = (Collection) query.execute();
			// System.out.println("collection is empty : "+c.isEmpty());
			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				user = (User) iter.next();
				System.out.println(">user login  " + user.toString());
			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
		return user;

	}

	// Perform some query operations
	public User getUser(String login, String password) {
		User user = null;
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Recupere le user ayant le login " + login);
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(User.class, true);
			Query query = pm.newQuery(e, "login==" + "'" + login
					+ "' && password==" + "'" + password + "'");
	
			@SuppressWarnings("rawtypes")
			Collection c = (Collection) query.execute();
			// System.out.println("collection is empty : "+c.isEmpty());
			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				user = (User) iter.next();
				System.out.println(">user login  " + user.toString());

			}

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
		return user;

	}

	public static void main(String args[]) throws FileNotFoundException,
			IOException {
		JDOHbaseUser jdohb = new JDOHbaseUser();
		User user = new User("mm", "essaim", User.SYSADMIN);
		User user1 = new User("sd", "essaim", User.STUDENT);
		User user2 = new User("ts", "essaim", User.SYSADMIN);
		/*
		 * jdohb.persister(user); jdohb.persister(user1);
		 * jdohb.persister(user2);
		 */
		jdohb.getUser("mm", "essaim");

	}
}