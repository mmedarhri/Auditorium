package core;

import java.util.ArrayList;

/*@authors Mohamed MEDARHRI
 * */

public abstract class Ressource {

	protected String name = null;

	
	public abstract ArrayList<Demi_Journee> findDisponibilite();
	public abstract boolean isDisponible(Demi_Journee dj);
	public abstract boolean reserver(Demi_Journee dj);
	public abstract void liberer(Demi_Journee dj);
	
	
	
}

			