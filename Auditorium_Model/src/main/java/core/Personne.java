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

/*@authors Mohamed MEDARHRI
 * 
 */

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Personne extends Ressource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
	private Propriete proprietes;
	private Affiliation affiliation;
	@Persistent
	private ArrayList<Demi_Journee> disponibilites;

	public Personne(Propriete proprietes, Affiliation affiliation) {
		this.proprietes = proprietes;
		this.affiliation = affiliation;
		disponibilites = new ArrayList<Demi_Journee>();
		}
	
	public Personne(Propriete proprietes) {
		this.proprietes = proprietes;
		disponibilites = new ArrayList<Demi_Journee>();
	}

	@Override
	public String toString() {
		return "\nPersonne \n[" + proprietes.toString() + "\nAffiliation="
				+ affiliation + "\nDisponibilites="
				+ disponibilites + "\n]";
	}

	public Propriete getProprietes() {
		return proprietes;
	}

	public void setProprietes(Propriete proprietes) {
		this.proprietes = proprietes;
	}

	@Override
	public ArrayList<Demi_Journee> findDisponibilite() {

		return disponibilites;

	}

	public Personne findPersonne(Demi_Journee demi_journee) {
		return null;
	}

	@Override
	public boolean isDisponible(Demi_Journee dj) {
		return disponibilites.contains(dj);
	}

	@Override
	public boolean reserver(Demi_Journee dj) {
		disponibilites.add(dj);
		return true;

	}

	@Override
	public void liberer(Demi_Journee dj) {
		disponibilites.remove(dj);
	}


	public Affiliation getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

	public static void main(String[] args) {
		Propriete proprietes3 = new Propriete(Propriete.Mademoiselle,
				"Benbouadi", "Hanae", "b.hanae@gmail.com", "0617119650");
		Affiliation affiliation1 = new Ecole(Ecole.Ensisa_Lumiere,
				Ecole.Adresse_Ensisa_Lumiere);

		Date date1 = new Date(2011-1900, 9, 22);
		Date date2 = new Date(2011-1900, 9, 22);
		
		Demi_Journee dj_debut = new Demi_Journee(date1, Demi_Journee.apresmidi);
		Demi_Journee dj_fin = new Demi_Journee(date2, Demi_Journee.matin);

		Personne p1 = new Personne(proprietes3, affiliation1);
		p1.reserver(dj_debut);
		p1.reserver(dj_fin);
		System.out.println(p1.toString());

	}

	
}
