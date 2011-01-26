package core;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/*@authors Mohamed MEDARHRI
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
@Discriminator(strategy=DiscriminatorStrategy.NONE)

public class Ecole extends Affiliation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
	protected String site=null;
	@Persistent
	private String adress=null;
	@Persistent
	private String name = "Ecole";
	@Persistent
	private List<Filiere> filieres=null;


	public static String Ensisa_Lumiere = "Ensisa_Lumiere";
	public static String Ensisa_Werner = "Ensisa_Werner";
	public static String Adresse_Ensisa_Lumiere = "12 rue des Frères Lumière 68093 MULHOUSE CEDEX";
	public static String Adresse_Ensisa_Werner = "11 rue Alfred Werner 68093 MULHOUSE CEDEX";

	public Ecole(String site, String adress) {

		this.site = site;
		this.adress = adress;
		this.filieres = new ArrayList<Filiere>();
		setFilieres(site);
		
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(ArrayList<Filiere> filieres) {
		this.filieres = filieres;
	}

	public String getSite() {
		if (this.site == "Ensisa_Lumiere")
			return "Ensisa Lumiere";
		else
			return "Ensisa Werner";
	}

	public String getAdress() {

		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void addFiliere(Filiere filiere) {
		this.filieres.add(filiere);
	}

	public void setFilieres(String site) {
		 
		if (this.site == Ensisa_Lumiere) {
			this.addFiliere(new Filiere(Filiere.IR));
			this.addFiliere(new Filiere(Filiere.SIS));
		} else {
			this.addFiliere(new Filiere(Filiere.Textiles));
			this.addFiliere(new Filiere(Filiere.Mecanique));
		}
	}

	public void removeFiliere(Filiere filiere) {
		filieres.remove(filiere);
	}

	public String getFilieresNames() {
		int i;
		StringBuffer buff = new StringBuffer();
		for (i = 0; i < filieres.size(); i++) {
			buff.append(filieres.get(i).toString()+"\n");
		}
		return buff.toString();

	}

	@Override
	public void setAffiliation(String name) {

		this.name = name;

	}

	@Override
	public String getAffiliation() {

		return this.name;
	}

	@Override
	public String toString() {
		return "Ecole [site=" + site + ", adress=" + adress + ", affiliation="
				+ name + ", filieres=" + filieres + "]";
	}

	
	public static void main(String[] args) {

		Affiliation ecole = new Ecole(Ecole.Ensisa_Werner,Ecole.Adresse_Ensisa_Werner);
		
	    Ecole ecole2 = new Ecole(Ecole.Ensisa_Werner, Ecole.Adresse_Ensisa_Werner);
		//System.out.println(ecole.getFilieresNames());
		System.out.println(ecole2.getFilieresNames());
		System.out.println(ecole.toString());
		

	}


}
