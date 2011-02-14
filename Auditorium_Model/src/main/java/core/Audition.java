package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/*
 * @authors Mohamed MEDARHRI
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Audition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
	protected  Personne candidat= null;
	
	@Persistent
	private Salle salle;
	
	@Persistent
	private Demi_Journee demi_journee;
	@Persistent
	private ArrayList<Personne> jury;
	
	
	public Audition(Personne candidat,Demi_Journee demi_journee) {
		this.demi_journee = demi_journee;
		this.candidat=candidat;
		jury = new ArrayList<Personne>();
	}

	public void reserverSalle(Salle salle) {
		if (salle.isDisponible(this.demi_journee)) {
			this.salle = salle;

		} else {
			salle.reserver(this.demi_journee);
			this.salle = salle;
		}
	}

	public ArrayList<Personne> getJury() {
		return jury;
	}

	public void setJury(ArrayList<Personne> jury) {
		this.jury = jury;
	}

	public void addToJury(Personne personne) {
		
		jury.add(personne);
	
	}


	public void setdebut(int heure, int minutes) {
	  this.demi_journee.setTime(heure, minutes);
	}
	
	public void setfin(int heure, int minutes) {
		  this.demi_journee.setTime(heure, minutes);
		}

	public void setCandidat(Personne personne) {
		this.candidat = personne;
	}

	public Demi_Journee getDemi_journee() {
		return demi_journee;
	}

	public void setDemi_journee(Demi_Journee demi_journee) {
		this.demi_journee = demi_journee;
	}

	public Salle getSalle() {
		return salle;
	}

	public Personne getCandidat() {
		return candidat;
	}

	@Override
	public String toString() {
		return "Audition"+"\n"+"[ Audition de " + candidat.getProprietes().getTitre()
				+ " " + candidat.getProprietes().getNom() + " "+candidat.getProprietes().getPrenom() +"\nSalle "
				+ salle + "\n"+demi_journee.toString()+ "\nJury" + jury
				+ "\ndébut de l'audition à " + demi_journee.getDebut(this.demi_journee.getDemijournee()) + "h"+ "\nfin de l'audition à " + demi_journee.getFin(this.demi_journee.getDemijournee())+ "h" + "\n]";
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Date date_debut = new Date(2010, 9, 22);
		Date date_fin = new Date(2010, 10, 22);

		Demi_Journee dj1 = new Demi_Journee(date_debut, Demi_Journee.matin);
		Demi_Journee dj2 = new Demi_Journee(date_debut, Demi_Journee.apresmidi);
		
		Propriete candidat1 = new Propriete(Propriete.Monsieur, "medarhri",
				"mohamed", "m.medarhri@gmail.com", "0617115650");
		Propriete prp12 = new Propriete(Propriete.Monsieur, "thomas",
				"sproesser", "thomas.sproesser@gmail.com", "0617115650");
		Propriete prp3 = new Propriete(Propriete.Monsieur, "frederic",
				"fondement", "frederic.fondement@gmail.com", "0617115650");
		Propriete prp4 = new Propriete(Propriete.Monsieur, "marc",
				"peronne", "marc.peronne@gmail.com", "0617115650");

		
	
		
		Affiliation aff1 = new Ecole("lycee vaucanson", "Tours");
		Affiliation aff12 = new Ecole("ENSISA", "mULHOUSE");
		Affiliation aff3 = new Ecole("ENSISA", "mULHOUSE");
		Affiliation aff4 = new Ecole("ENSISA", "mULHOUSE");
		Affiliation aff5 = new Industrie("Peugeot", "Vesoul");

		Ressource p1 = new Personne(candidat1, aff1);
		
		// p1.reserver(dj1);
		// p1.liberer(dj);
		// System.out.println(p1.isDisponible(dj));
		//Ressource p2 = new Personne(candidat2, aff12);
		// p2.reserver(dj1);

		Ressource p12 = new Personne(prp12, aff12);
		// p2.reserver(dj1);

		Ressource p3 = new Personne(prp3, aff3);
		// p3.reserver(dj1);

		Ressource p4 = new Personne(prp4, aff4);
		// p4.reserver(dj1);

		Audition audition1 = new Audition((Personne)p1,dj1);
		//Audition audition2 = new Audition((Personne)p2,dj1);
		
		audition1.setdebut(8, 45);
	    audition1.setfin(9, 30);

		/*audition2.setdebut(8, 45);
		audition2.setfin(9, 30);
		*/
		Ressource salle = new Salle(Salle.sites.Lumiere.name(),Salle.numeros.E25.name(),Salle.floors.First.name());

		audition1.addToJury((Personne) p12);
		audition1.addToJury((Personne) p3);
		audition1.addToJury((Personne) p4);
		
		
		audition1.reserverSalle((Salle) salle);

		System.out.println(audition1.toString());

		/*
	    audition2.setCandidat((Personne) p2);
		audition2.addToJury((Personne) p12);
		audition2.addToJury((Personne) p3);
		audition2.addToJury((Personne) p4);
	
		audition2.reserverSalle((Salle)salle);
		
		System.out.println(audition2.toString());
		*/

	}

}
