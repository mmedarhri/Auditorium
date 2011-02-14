package core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

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
public class Salle extends Ressource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
	protected String id = null;
	@Persistent
	private String numero = null;
	@Persistent
	private String floor = null;
	@Persistent
	private String site;
	@Persistent
	private ArrayList<Demi_Journee> disponibilites;
	
	public static enum floors {
		Rd, First, Second
	};

	public static enum sites {
		Lumiere, Werner
	};

	public static enum numeros {
		E25, E23, E32
	};

	public Salle(String site, String numero, String floor) {
		super();
		this.site = sites.valueOf(site).name();
		this.numero = numeros.valueOf(numero).name();
		this.floor = floors.valueOf(floor).name();
		this.id=site+numero+floor;
		disponibilites = new ArrayList<Demi_Journee>();
		
	}

	public String getNumero() {
		return numero;
	}

	public void setNumer(String numero) {
		this.numero = numero;
	}

	public String getSite() {
		if (this.site == sites.Lumiere.name())
			return "Lumiere";
		else
			return "Werner";
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Override
	public ArrayList<Demi_Journee> findDisponibilite() {

		return disponibilites;
	}

	@Override
	public boolean isDisponible(Demi_Journee dj) {
		int i = 0;
		boolean disponible = false;
		if (!disponibilites.isEmpty()) {
			for (; i < disponibilites.size(); i++) {
				if (disponibilites.get(i).compareDemi_journee(dj)) {

					disponible = true;
				}
			}
		}
		return disponible;
	}

	@Override
	public boolean reserver(Demi_Journee dj) {
		int i = 0;
		boolean found = false;
		if (!disponibilites.isEmpty()) {
			for (; i < disponibilites.size(); i++) {
				if (disponibilites.get(i).compareDemi_journee(dj)) {
					// if ((disponibilites.get(i).getDebut()==debut) &&
					// (disponibilites.get(i).getFin()==fin)) {
					found = true;
					// }
				}
			}
		}
		if (found) {
			System.out.println("cette salle est deja reservee");
			return false;
		} else {
			disponibilites.add(dj);
			return true;
		}
	}

	@Override
	public void liberer(Demi_Journee dj) {
		disponibilites.remove(dj);

	}

	@Override
	public String toString() {
		return "[site=" + getSite() + ",numero=" + numero + ", floor="
		+ floor + "\nDisponibilites=" + disponibilites + "]";
	}
	
	public static void main(String args[]) throws FileNotFoundException,
	IOException {

		Salle salle1 = new Salle(Salle.sites.Lumiere.name(),
				Salle.numeros.E25.name(), Salle.floors.First.name());
		/*Salle salle2 = new Salle(Salle.sites.Lumiere.name(),
				Salle.numeros.E23.name(), Salle.floors.First.name());
		Salle salle3 = new Salle(Salle.sites.Lumiere.name(),
				Salle.numeros.E32.name(), Salle.floors.Second.name());
			*/
		 System.out.println(salle1.toString());
		

	}
}
