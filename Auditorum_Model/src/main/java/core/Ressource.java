package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

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

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
 */
public abstract class Ressource {

	//@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UUIDHEX)
	protected String name = null;

	
	public abstract ArrayList<Demi_Journee> findDisponibilite();
	public abstract boolean isDisponible(Demi_Journee dj);
	public abstract boolean reserver(Demi_Journee dj);
	public abstract void liberer(Demi_Journee dj);
	
	
	
}

			