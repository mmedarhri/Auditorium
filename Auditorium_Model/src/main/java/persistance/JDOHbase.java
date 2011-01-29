package persistance;
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

import core.Affiliation;
import core.Audition;
import core.Demi_Journee;
import core.Ecole;
import core.Filiere;
import core.Personne;
import core.Propriete;
import core.Ressource;
import core.Salle;
import core.Session;

import datanucleusTutorial.core.User;


/**
*
* @author Mohamed MEDARHRI
*/

public class JDOHbase
{
	
	private PersistenceManagerFactory pmf;
	private PersistenceManager pm;
	private Transaction tx;
	private File propsFile;
	private final String datanucleus = "datanucleus.properties";
	private Class persistable;
	private Field[] fields;
	
	public JDOHbase()
	{
		propsFile = new File(datanucleus);
		// Create a PersistenceManagerFactory for this datastore
		pmf = JDOHelper.getPersistenceManagerFactory(propsFile);
		
	}
	
	
	private Class getClasse(Object obj)
	{
		//System.out.println("Class: "+obj.getClass().getSimpleName());
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
        Transaction tx=pm.currentTransaction();
        try
        {
        	tx.begin();
            System.out.println("Persisting "+obj.getClass().getSimpleName());
            pm.makePersistent(obj);
          
            tx.commit();
            System.out.println(obj.getClass().getSimpleName()+" have been persisted");
        }catch(Exception e)
        {
        	System.out.println("Error in persister() "+e.toString());
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
		
	}
	
	
	// Persistence ecole
	public void persisterEcole(Ecole ecole) {
		pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        int i;
        try
        {
        	tx.begin();
            System.out.println("Persisting Ecole "+ecole.getSite());
            for(i=0;i<ecole.getFilieres().size();i++)
            {
            pm.makePersistent(ecole.getFilieres().get(i));
            
            }
            pm.makePersistent(ecole);
          
            tx.commit();
            System.out.println(ecole.getSite()+" have been persisted");
        }catch(Exception e)
        {
        	System.out.println("Error in persisterEcole() "+e.toString());
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
		
	}
	
	
	
	
	// Persistence Personne
	public void persisterPersonne(Personne pers) {
		pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        int i;
        try
        {
        	tx.begin();
          System.out.println("Persisting Personne "+pers.toString());
          /*
          for(i=0;i<ecole.getFilieres().size();i++)
            {
            pm.makePersistent(ecole.getFilieres().get(i));
            
            }
            */
            pm.makePersistent(pers);
          
            tx.commit();
            System.out.println(pers.toString()+" have been persisted");
        }catch(Exception e)
        {
        	System.out.println("Error in persisterPersonne() "+e.toString());
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
		
	}
	
	// Persistence Audition
	public void persisterAudition(Audition audition) {
		pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        int i;
        try
        {
        	tx.begin();
          System.out.println("Persisting Audition "+audition.toString());
          
          //pm.makePersistent(audition.getCandidat());  
          pm.makePersistent(audition);
            
            tx.commit();
            System.out.println("audition of "+audition.getCandidat().getProprietes().getNom()+" have been persisted");
        }catch(Exception e)
        {
        	System.out.println("Error in persisterAudition() "+e.toString());
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
		
	}
	
	// Persistence Session
	public void persisterSession(Session session) {
		pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        int i;
        try
        {
        	tx.begin();
          System.out.println("Persisting Session "+session.toString());
            pm.makePersistent(session);
            
            tx.commit();
            System.out.println(session.getName()+" have been persisted");
        }catch(Exception e)
        {
        	System.out.println("Error in persisterAudition() "+e.toString());
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
		
	}
	
	
	// Persistence Propriete
	public void persisterPropriete(Propriete propriete) {
		pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        int i;
        try
        {
        	tx.begin();
          System.out.println("Persisting Ecole "+propriete.toString());
          /*
          for(i=0;i<ecole.getFilieres().size();i++)
            {
            pm.makePersistent(ecole.getFilieres().get(i));
            
            }
            */
            pm.makePersistent(propriete);
          
            tx.commit();
            System.out.println(propriete.toString()+" have been persisted");
        }catch(Exception e)
        {
        	System.out.println("Error in persisterPropriete() "+e.toString());
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
		
	}
	
	
	// Persistence Salle
	public void persisterSalle(Salle salle) {
		pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        int i;
        try
        {
        	tx.begin();
            System.out.println("Persisting Salle "+salle.toString());
           /* for(i=0;i<salle.getd.size();i++)
            {
            pm.makePersistent(ecole.getFilieres().get(i));
            
            }
            */
            pm.makePersistent(salle);
          
            tx.commit();
            System.out.println(salle.toString()+" have been persisted");
        }catch(Exception e)
        {
        	System.out.println("Error in persisterSalle() "+e.toString());
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
		
	}
	
	  // Basic Extent of all user
	
	public void getExtent(Object obj)
	{
    pm = pmf.getPersistenceManager();
    tx = pm.currentTransaction();
    try
    {
        tx.begin();
        System.out.println("Retrieving Extent for "+obj.getClass().getSimpleName());
        @SuppressWarnings("rawtypes")
		Extent e = pm.getExtent(obj.getClass(),true);
        @SuppressWarnings("rawtypes")
		Iterator iter = e.iterator();
        while (iter.hasNext())
        {
            Object object = iter.next();
              //if (object instanceof User)
            //{
            //	User b = (User)obj;
            	System.out.println("> object "+object.toString());
        //    }
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
}
	
	//vide la table User
	public void cleanOutUser()
	{
		pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Supression de tous les User ");
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(User.class,true);
            Query query=pm.newQuery(e);
            //Query query = pm.newQuery("javax.jdo.query.JDOQL", "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");
             
            System.out.println(query.toString());
          
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)query.execute();
           // System.out.println("collection is empty : "+c.isEmpty());
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            while (iter.hasNext())
            {
             User user = (User)iter.next();
             query.deletePersistentAll(user);
                
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
	}
	
	//vide la table Filiere
	public void cleanOutFiliere()
	{
		pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Supression de toutes les Filieres ");
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(Filiere.class,true);
            Query query=pm.newQuery(e);
            //Query query = pm.newQuery("javax.jdo.query.JDOQL", "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");         
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)query.execute();
           // System.out.println("collection is empty : "+c.isEmpty());
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            while (iter.hasNext())
            {
             Filiere filiere = (Filiere)iter.next();
             query.deletePersistentAll(filiere);
                
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
	}
	
	//cleanout Ecole table
	public void cleanOutEcole()
	{
		pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Supression de toutes les Ecoles ");
            @SuppressWarnings("rawtypes")
			Extent ecoles=pm.getExtent(Ecole.class,true);
            Extent filieres=pm.getExtent(Filiere.class,true);
            Query query_ecoles=pm.newQuery(ecoles);
            Query query_filieres=pm.newQuery(filieres);
            //Query query = pm.newQuery("javax.jdo.query.JDOQL", "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");
          
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)query_ecoles.execute();
            //Collection c1=(Collection)query_filieres.execute();
           // System.out.println("collection is empty : "+c.isEmpty());
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            //Iterator iter1=c1.iterator();
            while (iter.hasNext())
            {
             Ecole ecole = (Ecole)iter.next();
             query_ecoles.deletePersistentAll(ecole);               
            }
           /* while (iter1.hasNext())
            {
             Filiere filere = (Filiere)iter.next();
             query_filieres.deletePersistentAll(filere);
            
                
            }
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
	}
	
	
	public void deleteUserByLogin(String login)
	{
		pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Supression du User ayant le login "+login);
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(User.class,true);
            Query query=pm.newQuery(e, "login=="+"'"+login+"'");
            //Query query = pm.newQuery("javax.jdo.query.JDOQL", "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");
             
            System.out.println(query.toString());
          
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)query.execute();
           // System.out.println("collection is empty : "+c.isEmpty());
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            while (iter.hasNext())
            {
             User user = (User)iter.next();
             query.deletePersistentAll(user);
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
	}
	
	//update du role
	public void updateRole(String login,String role)
	{
		pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Ajout des infos du User "+login);
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(User.class,true);
            Query query=pm.newQuery(e, "login=="+"'"+login+"'");
            //Query query = pm.newQuery("javax.jdo.query.JDOQL", "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");         
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)query.execute();
           // System.out.println("collection is empty : "+c.isEmpty());
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            while (iter.hasNext())
            {
             User user = (User)iter.next();
            
             user.setRole(role);
             
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
	}
	
	
	//update du mot de passe
	public void updatePassword(String login,String password)
	{
		pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Update du mot de passe du User "+login);
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(User.class,true);
            Query query=pm.newQuery(e, "login=="+"'"+login+"'");
            //Query query = pm.newQuery("javax.jdo.query.JDOQL", "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");         
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)query.execute();
            //System.out.println("collection is empty : "+c.isEmpty());
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            while (iter.hasNext())
            {
             User user = (User)iter.next();
            
             user.setPassword(password);
             
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
	}
	
	
	//ajout d'infos supplementaire pour un user
	public void completeInfoUserByLogin(String login,String role)
	{
		pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Ajout des infos du User "+login);
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(User.class,true);
            Query query=pm.newQuery(e, "login=="+"'"+login+"'");
            //Query query = pm.newQuery("javax.jdo.query.JDOQL", "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");         
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)query.execute();
           // System.out.println("collection is empty : "+c.isEmpty());
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            while (iter.hasNext())
            {
             User user = (User)iter.next();
            
             user.setRole(role);
             
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
	}
	
	
	//ajout d'informations sur un user
	public void completeInfoUserByLogin(String login,String name,String prenom,int age,String adresse,String email)
	{
		pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Ajout des infos du User "+login);
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(User.class,true);
            Query query=pm.newQuery(e, "login=="+"'"+login+"'");
            //Query query = pm.newQuery("javax.jdo.query.JDOQL", "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");         
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)query.execute();
           // System.out.println("collection is empty : "+c.isEmpty());
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            while (iter.hasNext())
            {
             User user = (User)iter.next();
             user.setName(name);
             user.setFirstname(prenom);
             user.setEmail(email);
             user.setAddress(adresse);
             user.setAge(age);
             
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
	}
	
	// Perform some query operations
	public User getUserByLogin(String login)
	{
		User user=null;  
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("Recupere le user ayant le login "+login);
            @SuppressWarnings("rawtypes")
			Extent e=pm.getExtent(User.class,true);
            Query query=pm.newQuery(e, "login=="+"'"+login+"'");
            //Query query = pm.newQuery("javax.jdo.query.JDOQL", "SELECT FROM datanucleusTutorial.core.User WHERE login == 'ff'");
             
            System.out.println(query.toString());
          
            @SuppressWarnings("rawtypes")
			Collection c=(Collection)query.execute();
           // System.out.println("collection is empty : "+c.isEmpty());
            @SuppressWarnings("rawtypes")
			Iterator iter=c.iterator();
            while (iter.hasNext())
            {
                user = (User)iter.next();
                System.out.println(">user login  " + user.getLogin());
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
        return user;

	}
	
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
    	JDOHbase jdohb = new JDOHbase();
    	
		String password="essaim";
		String role="admin";
		User user= new User("mm", password, User.SYSADMIN);
		User user1= new User("ff", password, User.AUDITOR);
		User user2= new User("ts", password, User.SCHOOL_DIRECTION);
		User user3= new User("sd", password, User.STUDENT);
		

		Ecole werner = new Ecole(Ecole.Ensisa_Werner,Ecole.Adresse_Ensisa_Werner);
		Ecole lumiere = new Ecole(Ecole.Ensisa_Lumiere,Ecole.Adresse_Ensisa_Lumiere);
		Filiere ir = new Filiere(Filiere.IR);
		Filiere sis = new Filiere(Filiere.SIS);
		Filiere mec = new Filiere(Filiere.Mecanique);
		
		Propriete propriete = new Propriete(Propriete.Mademoiselle,
				"Ben", "Hanae", "b.hanae@gmail.com", "0617119650");
		Affiliation affiliation = new Ecole(Ecole.Ensisa_Lumiere,
				Ecole.Adresse_Ensisa_Lumiere);
	

		Salle salle1 = new Salle(Salle.sites.Lumiere.name(),
		Salle.numeros.E25.name(), Salle.floors.First.name());
		Salle salle2 = new Salle(Salle.sites.Lumiere.name(),
		Salle.numeros.E23.name(), Salle.floors.First.name());
		
		
		Date date_debut = new Date(2010, 9, 22);
		Date date_fin = new Date(2010, 10, 22);

		Demi_Journee dj = new Demi_Journee(date_debut, 1);
		
		
	//	Personne p1 = new Personne(propriete, affiliation);

    	
	/*	jdohb.persister(user);
    	jdohb.persister(user1);
    	jdohb.persister(user2);
    	jdohb.persister(user3);
    	*/
    	//jdohb.getExtent(user);
    	//jdohb.updatePassword("ts","new_password");
    	//jdohb.getUserByLogin("ts");
        //jdohb.deleteUserByLogin("ff");
    	//jdohb.cleanOutUser();
        //jdohb.cleanOutEcole();
		//jdohb.persisterEcole(werner);
		//jdohb.persisterEcole(lumiere);
		//jdohb.getExtent(werner);
		//jdohb.getExtent(lumiere); 
		/*jdohb.persister(ir);
		jdohb.persister(sis);
		jdohb.persister(mec);
		*/
		//jdohb.cleanOutFiliere();
		//jdohb.persisterSalle(salle1);
		//jdohb.persisterPropriete(propriete);
		//jdohb.persisterPersonne(p1);
		Propriete candidat = new Propriete(Propriete.Monsieur, "MEDARHRI",
				"adil", "m.medarhri@gmail.com", "0617115650");
		Affiliation aff = new Ecole("ENSISA", "mULHOUSE");
		Ressource p1 = new Personne(candidat, aff);
		
		Audition audition = new Audition((Personne)p1, dj);
		//System.out.println(audition.toString());

	
		
		/*dj8.addRessource(p4);
		dj8.addRessource(p3);
		dj8.addRessource(p2);
		dj8.addRessource(p1);
		dj8.addRessource(salle2);
		*/
	
		Demi_Journee dj1 = new Demi_Journee(new Date(2011, 6, 22),Demi_Journee.matin);
		
		Session session = new Session("test",date_debut,date_fin,affiliation);

		
		//session.addDemi_journee(dj1);
		/*session.addDemi_journee(dj2);

		session.addDemi_journee(dj3);

		session.addDemi_journee(dj4);

		session.addDemi_journee(dj5);

		session.addDemi_journee(dj6);
		session.addDemi_journee(dj7);

		session.addDemi_journee(dj8);
*/
		/*p1.reserver(dj1);
		p2.reserver(dj1);
		p3.reserver(dj1);
		p4.reserver(dj1);
*/
		session.addRessource(p1);
		session.addRessource(salle1);

		//session.addRessource(p2);
		session.addRessource(salle2);

		//session.addRessource(p3);
		
		
	  Audition audition1 = new Audition((Personne)p1,dj1);
	  audition1.setCandidat((Personne) p1);
	  session.addAudition(audition1);
		//audition1.setdebut(8, 30);
		//audition1.setfin(9, 10);

		
		/*audition1.addToJury((Personne) p2);
		audition1.addToJury((Personne) p3);
*/
		/*System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());
		System.out.println(p4.toString());
*/
		//System.out.println(session.toString());

		//jdohb.persisterAudition(audition);
		jdohb.persisterSession(session);
		
    }
}