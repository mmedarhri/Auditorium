package core;

import java.io.File;
import java.io.Serializable;
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
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/*@authors Mohamed MEDARHRI
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
public class Propriete  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
	protected String id;
	 
	@Persistent
	private String nom = null;
	@Persistent
	private String prenom = null;
	@Persistent
	private String mail;
	@Persistent
	private String tel;
	@Persistent
	private int titre;
	
	public final static int Monsieur = 0;
	public final static int Madame =1;
	public final static int Mademoiselle =2;
		
	public Propriete(int titre,String nom, String prenom, String mail, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.tel = tel;
		this.titre=titre;
		this.id=nom+prenom;
		
	}
	
	public Propriete(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.id=nom+prenom;
		}
	
	public String getNom() {
		return nom.toUpperCase();
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTitre() {
		switch (titre) {
		case 0: return "Mr";
		case 1: return "Mdme";
		case 2: return "Mlle";
		default:
			return "Unkown";
		}
			
	}

	public void setTitre(int titre) {
		this.titre = titre;
	}

	@Override
	public String toString() {
		return "Proprietes [" +getTitre()+", "+"nom=" + nom + ", prenom=" + prenom + ", mail="
				+ mail + ", tel=" + tel + "]";
	}
	
		
	public static void main(String[] args) {
		Propriete propriete3 = new Propriete(Propriete.Mademoiselle,
				"Ben", "Hanae", "b.hanae@gmail.com", "0617119650");
		System.out.println(propriete3.toString());
		
}
}
