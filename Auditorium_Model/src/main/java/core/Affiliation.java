package core;

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
@Inheritance(strategy=InheritanceStrategy.UNSPECIFIED)
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
 */
public abstract class Affiliation {


	//@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
	protected String name;
	 
	public abstract void setAffiliation(String name);
	public abstract String getAffiliation();
	
}
