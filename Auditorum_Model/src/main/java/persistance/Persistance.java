package persistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.text.Annotation;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import core.Ecole;
import core.Filiere;
import core.Personne;
import core.Salle;

import datanucleusTutorial.core.Book;
import datanucleusTutorial.core.Product;

public class Persistance {

	private PersistenceManagerFactory pmf;
	private PersistenceManager pm;
	private Transaction tx;
	private File propsFile;
	private final String datanucleus = "datanucleus.properties";
	private Class c;
	private Field[] fields;


	
	public Persistance(Object obj)
	{
		propsFile = new File(datanucleus);
		// Create a PersistenceManagerFactory for this datastore
		pmf = JDOHelper.getPersistenceManagerFactory(propsFile);
		this.c=getClasse(obj);
		//this.fields=getAllChamps(c);
		//getNombreChamps(fields);
		getAnnotatedChamps(c);
	}

	
	
	private Class getClasse(Object obj)
	{
		System.out.println("Class: "+obj.getClass().getSimpleName());
		return obj.getClass();
	}
	
	private Field[] getAllChamps(Class c)
	{
		for (int i = 0; i < c.getDeclaredFields().length; i++) {
		      String fieldName = c.getDeclaredFields()[i].getName();
		      System.out.println("Name: " + fieldName);
		    }
		return c.getClass().getDeclaredFields();
	}
	
	private java.lang.annotation.Annotation[] getAnnotatedChamps(Class c)
	{
		for (int i = 0; i < c.getDeclaredFields().length; i++) {
		      boolean annotated = c.getDeclaredFields()[i].isAnnotationPresent(c);
		      if(annotated)
		      {
		      System.out.println("Name: " + c.getDeclaredFields()[i].getName());
		      
		      }
		    }
		return c.getClass().getAnnotations();
	}
	
	private void getNombreChamps(Field[] fields)
	{
		
		System.out.println("nombre de champs : "+fields.length);
		
	}
	
	
	// Persistence 
	public void persister(Object obj) {

		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		/*if (this.obj instanceof Salle) {
			Salle objet = (Salle) this.obj;
		}
		if (this.obj instanceof Ecole)
		{
			Ecole objet = (Ecole) this.obj;
		}
		if (this.obj instanceof Filiere) {
			Filiere objet = (Filiere) this.obj;
		}
		if (this.obj instanceof Personne)
		{
			Personne objet = (Personne) this.obj;
		}
		*/
		try {
			tx.begin();
			System.out.println("Persisting "+obj.getClass().getSimpleName());
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(this.c,true);
			
			Query q = pm.newQuery(e, "numero == '" +""+ "'");
			List results = (List)q.execute();
			if(results.isEmpty())
			{
			pm.makePersistent(this);

			tx.commit();
			System.out.println("Salle " + this.toString()
					+ " have been persisted");
		} 
			else
				System.out.println("Salle " + this.toString()
						+ " already exist");
	}
		finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		System.out.println("");
	}

	public void getpersisted() {

		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Retrieving Extent for "+c.getName());
			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(c);
			@SuppressWarnings("rawtypes")
			Iterator iter = e.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				System.out.println(">  " + obj.toString());
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
	
	
	// Clean out the database
	
	public void cleanoutTable() {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Deleting all "+c.getCanonicalName() +" from persistence");
			Query q = pm.newQuery(c);
			long numberInstancesDeleted = q.deletePersistentAll();
			System.out.println("Deleted " + numberInstancesDeleted + ":"+c.getCanonicalName());

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		System.out.println("End ");

	}
	
	public void getPesistedSalleNumero(String numero) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Executing Query for Salle number " + numero);

			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(Salle.class);
			Query q = pm.newQuery(e, "numero == '" + numero + "'");
			// q.setOrdering("price ascending");

			@SuppressWarnings("rawtypes")
			Collection c = (Collection) q.execute();

			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				System.out.println(">  " + obj.toString());

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

	public void getPesistedSallesOnFloor(String floor) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Executing Query for Salles on the " + floor
					+ " floor");

			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(Salle.class);
			Query q = pm.newQuery(e, "floor == '" + floor + "'");
			// q.setOrdering("price ascending");

			@SuppressWarnings("rawtypes")
			Collection c = (Collection) q.execute();

			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				System.out.println(">  " + obj.toString());

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

	public void updateNumeroPesistedSalle(String old_numero, String new_numero) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Executing Update Salle: " + old_numero
					+ " ; to numero " + new_numero);

			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(Salle.class);
			Query q = pm.newQuery(e, "numero == '" + old_numero + "'");

			@SuppressWarnings("rawtypes")
			Collection c = (Collection) q.execute();

			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				// Give an example of an update
				if (obj instanceof Salle) {
					Salle b = (Salle) obj;

					b.setNumer(new_numero);

				}
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

	public void updateSitePesistedSalle(String numero, String new_site) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		try {
			tx.begin();
			System.out.println("Executing Update Salle: " + numero
					+ " ; site  to " + new_site);

			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(Salle.class);
			Query q = pm.newQuery(e, "numero == '" + numero + "'");

			@SuppressWarnings("rawtypes")
			Collection c = (Collection) q.execute();

			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				// Give an example of an update
				if (obj instanceof Salle) {
					Salle b = (Salle) obj;

					b.setSite(new_site);

				}
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

	/*public void updateFloorPesistedSalle(String floor) {
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		Salle salle = (Salle)c;
		try {
			tx.begin();
			System.out.println("Executing Update Salle floor  to " + floor + " floor");

			@SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(Salle.class);
			Query q = pm.newQuery(e, "numero == '"+ salle.getNumero() + "'");

			@SuppressWarnings("rawtypes")
			Collection c = (Collection) q.execute();

			@SuppressWarnings("rawtypes")
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				// Give an example of an update
				if (obj instanceof Salle) {
					Salle b = (Salle) obj;

					b.setFloor(floor);

				}
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
*/
	
	public static void main(String args[]) throws FileNotFoundException, IOException
    {
		Ecole ecole = new Ecole(Ecole.Ensisa_Werner,
				Ecole.Adresse_Ensisa_Werner);
		Salle salle1 = new Salle(Salle.sites.Lumiere.name(),
				Salle.numeros.E25.name(), Salle.floors.First.name());
		
		Persistance persistance = new Persistance(salle1);
		
		
		//persistance.updateFloorPesistedSalle(Salle.floors.Second.name());
    }
}

