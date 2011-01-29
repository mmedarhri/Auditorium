
package core.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import core.Affiliation;
import core.Ecole;
import core.Personne;
import core.Propriete;
import core.Ressource;
import core.Salle;

import datanucleusTutorial.core.Book;
import datanucleusTutorial.core.Product;


public class TestHbase
{
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
    	
 
    File propsFile = new File("datanucleus.properties");
    	
    	
      // Create a PersistenceManagerFactory for this datastore
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(propsFile);

        System.out.println("DataNucleus AccessPlatform with JDO");
        System.out.println("===================================");

        // Persistence de Personne et salle
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
        	tx.begin();
            System.out.println("Persisting Personne");
            Product product = new Product("Sony Discman","A standard discman from Sony",200.00);
            Book book = new Book("Lord of the Rings by Tolkien","The classic story",49.99,"JRR Tolkien", "12345678", "MyBooks Factory");
            
            Propriete proprietes = new Propriete(Propriete.Monsieur,
    				"Medarhri", "mohamed", "m.medarhri@gmail.com", "0617115650");
    		Affiliation affiliation = new Ecole(Ecole.Ensisa_Lumiere, Ecole.Adresse_Ensisa_Lumiere);

            Ressource personne = new Personne(proprietes, affiliation);
            Ressource salle = new Salle(Salle.sites.Lumiere.name(),Salle.numeros.E25.name(),Salle.floors.First.name());
            
            //pm.makePersistent(personne);
            pm.makePersistent(salle);
 
            tx.commit();
            System.out.println("Personne and Salle have been persisted");
        }
        finally
        {
            if (tx.isActive())
            {
            	tx.rollback();
            }
            pm.close();
        }
        System.out.println("");

        // Basic Extent of all Personne
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Retrieving Extent for Personne");
            @SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(Salle.class, true);
            @SuppressWarnings("rawtypes")
			Iterator iter = e.iterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                System.out.println(">  " + obj);
            }
            tx.commit();
        }
        catch (Exception e)
        {
            System.out.println("Exception thrown during retrieval of Extent : " + e.getMessage());
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        System.out.println("");

        // Perform some query operations
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
     /*   try
        {
            tx.begin();
            System.out.println("Executing Query for Personne with price below 150.00");
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(Product.class,true);
            Query q=pm.newQuery(e, "price < 150.00");
            q.setOrdering("price ascending");
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)q.execute();
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                System.out.println(">  " + obj);

                // Give an example of an update
                if (obj instanceof Book)
                {
                    Book b = (Book)obj;
                    b.setDescription("This book has been reduced in price!");
                }
            }

            tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        System.out.println("");
/*
        // Clean out the database
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Deleting all products from persistence");
            Query q = pm.newQuery(Product.class);
            long numberInstancesDeleted = q.deletePersistentAll();
            System.out.println("Deleted " + numberInstancesDeleted + " products");

            tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
*/
        System.out.println("");
        System.out.println("End of Tutorial");  
    	
    }
}