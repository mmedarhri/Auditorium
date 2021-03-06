package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

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
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class Session {

	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
	private String name;
	@Persistent
	private GregorianCalendar date_debut;
	@Persistent
	private GregorianCalendar date_fin;
	@Persistent
	private Affiliation affiliation;
	@Persistent
	private ArrayList<Demi_Journee> demijournees;
    
	private ArrayList<Ressource> ressources;
	@Persistent
	private Audition audition;

	public Session(String name, GregorianCalendar date_debut,
			GregorianCalendar date_fin, Affiliation affiliation) {
		super();
		this.name = name;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.affiliation = affiliation;
		this.demijournees = new ArrayList<Demi_Journee>();
		this.ressources = new ArrayList<Ressource>();
	}

	public Affiliation getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GregorianCalendar getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(GregorianCalendar date_debut) {
		this.date_debut = date_debut;
	}

	public GregorianCalendar getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(GregorianCalendar date_fin) {
		this.date_fin = date_fin;
	}

	public void addDemi_journee(Demi_Journee dj) {

		int i = 0;
		boolean found = false;
		if (!demijournees.isEmpty()) {
			for (; i < demijournees.size(); i++) {
				if (demijournees.get(i).compareDemi_journee(dj)) {

					found = true;
				}
			}
		}
		if (found) {
			System.out.println("cette demi_journee existe deja");
		} else {
			demijournees.add(dj);
		}

	}

	public void removeDemi_journee(Demi_Journee dj) {
		if (demijournees.contains(dj)) {
			demijournees.remove(dj);
		} else
			System.out.println("cette demi_journee nexiste pas");
	}

	public ArrayList<Demi_Journee> getDemi_journees() {
		return demijournees;
	}

	public void addRessource(Ressource ressource) {

		ressources.add(ressource);

	}

	public void addRessources(ArrayList<Ressource> ressources) {
		this.ressources = ressources;
	}

	public ArrayList<Ressource> getRessources() {
		return ressources;
	}

	@Override
	public String toString() {
		return "Session \n[name=" + name + "\nDate_debut ="
				+ date_debut.getTime().toLocaleString() + "\nDate_fin="
				+ date_fin.getTime().toLocaleString() + "\nAffiliation="
				+ affiliation.getAffiliation() + "\nDemijournees="
				+ demijournees + "\n]";
	}

	public int getNombreDemiJournees() {
		return demijournees.size();
	}

	private Audition createAudition(Personne personne,Demi_Journee dj)

	{

		if (dj.isInIntervalle(this.date_debut, this.date_fin)) {
			return audition = new Audition(personne,dj);
			
		}

		else
			System.out.println("choisisez une autre demi_journee ");
		return null;

	}

	public static void main(String[] args) {

		GregorianCalendar date_debut = new GregorianCalendar(2010, 10, 22);

		GregorianCalendar date_fin = new GregorianCalendar(2011, 10, 22);

		Demi_Journee dj1 = new Demi_Journee(
				new GregorianCalendar(2010, 10, 22), 0);
		// dj1.setDebut(7,45);
		Demi_Journee dj2 = new Demi_Journee(
				new GregorianCalendar(2010, 11, 22), 0);
		Demi_Journee dj3 = new Demi_Journee(
				new GregorianCalendar(2010, 10, 23), 0);
		Demi_Journee dj4 = new Demi_Journee(
				new GregorianCalendar(2010, 10, 23), 1);
		Demi_Journee dj5 = new Demi_Journee(
				new GregorianCalendar(2010, 10, 24), 0);
		Demi_Journee dj6 = new Demi_Journee(
				new GregorianCalendar(2010, 10, 24), 1);
		Demi_Journee dj7 = new Demi_Journee(
				new GregorianCalendar(2010, 10, 25), 0);
		Demi_Journee dj8 = new Demi_Journee(
				new GregorianCalendar(2010, 10, 25), 1);
		
		

		Affiliation affiliation = new Ecole("Ensisa", "lumiere");

		Propriete prop1 = new Propriete(Propriete.Monsieur,"mohamed", "medarhri",
				"m.medarhri@gmail.com", "0617115650");
		Propriete prop4 = new Propriete("thomas", "sproesser");
		Propriete prop2 = new Propriete("frederic", "fondement");
		Propriete prop3 = new Propriete("patrick", "alliot");

		Ressource p1 = new Personne(prop1, affiliation);

		Ressource salle1 = new Salle(Salle.sites.Lumiere.name(),Salle.numeros.E25.name(),Salle.floors.First.name());

		Ressource p2 = new Personne(prop2, affiliation);
		Ressource salle2 = new Salle(Salle.sites.Lumiere.name(),Salle.numeros.E23.name(),Salle.floors.First.name());

		Ressource p3 = new Personne(prop3, affiliation);
		Ressource salle3 = new Salle(Salle.sites.Lumiere.name(),Salle.numeros.E32.name(),Salle.floors.Second.name());

		
		/*dj8.addRessource(p4);
		dj8.addRessource(p3);
		dj8.addRessource(p2);
		dj8.addRessource(p1);
		dj8.addRessource(salle2);
		*/
		
		Session session = new Session("test", date_debut, date_fin, affiliation);

		session.addDemi_journee(dj1);
		session.addDemi_journee(dj2);

		session.addDemi_journee(dj3);

		session.addDemi_journee(dj4);

		session.addDemi_journee(dj5);

		session.addDemi_journee(dj6);
		session.addDemi_journee(dj7);

		session.addDemi_journee(dj8);

		/*p1.reserver(dj1);
		p2.reserver(dj1);
		p3.reserver(dj1);
		p4.reserver(dj1);
*/
		session.addRessource(p1);
		session.addRessource(salle1);

		session.addRessource(p2);
		session.addRessource(salle2);

		session.addRessource(p3);
		
		Audition audition1 = session.createAudition((Personne)p1,dj3);
		audition1.setdebut(8, 30);
		audition1.setfin(9, 10);

		audition1.setCandidat((Personne) p1);
		audition1.addToJury((Personne) p2);
		audition1.addToJury((Personne) p3);

		/*System.out.println(p1.toString());
		System.out.println(p2.toString());
		System.out.println(p3.toString());
		System.out.println(p4.toString());
*/
		System.out.println(session.toString());

		//System.out.println(audition1.toString());

	}
}
