/**********************************************************************
Copyright (c) 2003 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package datanucleusTutorial.essaie;
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
import datanucleusTutorial.core.Book;
import datanucleusTutorial.core.Product;
import datanucleusTutorial.core.User;





/**
 * Controlling application for the DataNucleus Tutorial using JDO.
 * Relies on the user defining a file "datanucleus.properties" to be in the CLASSPATH
 * and to include the JDO properties for the DataNucleus PersistenceManager.
 *
 * @version $Revision: 1.1 $
 **/
public class TestHbase
{
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
    	
 
    File propsFile = new File("datanucleus.properties");
    	
    	
    	 // Create a PersistenceManagerFactory for this datastore
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(propsFile);

        System.out.println("DataNucleus AccessPlatform with JDO");
        System.out.println("===================================");

        // Persistence of a Product and a Book.
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
        	tx.begin();
            System.out.println("Persisting products");
            Product product = new Product("Sony Discman","A standard discman from Sony",200.00);
            Book book = new Book("Lord of the Rings by Tolkien","The classic story",49.99,"JRR Tolkien", "12345678", "MyBooks Factory");
            
           pm.makePersistent(product);
           pm.makePersistent(book);
 
            tx.commit();
            System.out.println("Product and Book have been persisted");
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

        // Basic Extent of all Products
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Retrieving Extent for Products");
            @SuppressWarnings("rawtypes")
			Extent e = pm.getExtent(Product.class, true);
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
        try
        {
            tx.begin();
            System.out.println("Executing Query for Products with price below 150.00");
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(Book.class,true);
            Query q=pm.newQuery(e, "author == Tolkien");
            System.out.println(q.toString());
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
                if (obj instanceof User)
                {
                	User b = (User)obj;
                    b.setName("FONDEMENT");
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

        // Clean out the database
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Deleting all products from persistence");
            Query q = pm.newQuery(Product.class);
            Query q1 = pm.newQuery(Book.class);
            Query q2 = pm.newQuery(User.class);

           /* long numberInstancesDeleted = q.deletePersistentAll();
            long numberInstancesDeleted1 = q1.deletePersistentAll();
            long numberInstancesDeleted2 = q2.deletePersistentAll();
            System.out.println("Deleted " + numberInstancesDeleted + " products");
            System.out.println("Deleted " + numberInstancesDeleted2 + " Users");

            System.out.println("Deleted " + numberInstancesDeleted1 + " book");
*/
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
        System.out.println("End of Tutorial");  
    	
    }
}