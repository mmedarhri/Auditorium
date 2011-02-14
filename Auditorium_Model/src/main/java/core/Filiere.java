package core;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


/*@authors Mohamed MEDARHRI
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.UNSPECIFIED)

public class Filiere  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2469325702450037037L;
	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
	private String name=null;
		//Filieres de l'ecole
	public static String IR ="Informatique & Reseaux";
	public static String SIS ="Systemes & Signaux";
	public static String Textiles ="Textiles";
	public static String Mecanique ="Mecanique";
	
	//filieres de provenance
	public static String DUT ="DUT";
	public static String ATS ="ATS";
	public static String L3 ="L3";
	public static String BTS ="BTS";
			
	public Filiere(String name) {
		this.name=name;
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return  name  ;
	}

	public static void main(String[] args)
	{
		Filiere filiere = new Filiere(Filiere.Textiles);
		System.out.println(filiere.toString());
	}
	
}
