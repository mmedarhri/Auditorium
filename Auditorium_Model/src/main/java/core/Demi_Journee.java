package core;

import java.io.Serializable;
import java.util.Date;

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
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy=DiscriminatorStrategy.NONE)
public class Demi_Journee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int matin = 0;
	public static final int apresmidi = 1;
	private int demi_journee;
    private Date debut_matin;
    private Date debut_apresmidi;
    private Date fin_matin;
    private Date fin_apresmidi;
	private Date date;
	@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UUIDSTRING)
	protected String id;
	


	public Demi_Journee(Date date, int demi_journee) {
		this.date = date;
		this.demi_journee = demi_journee;
		setDebut_DemiJournee(demi_journee);
		setFin_DemiJournee(demi_journee);
	}


	
	public boolean isInIntervalle(Date date_debut,
			Date date_fin) {
		if ((this.date.before(date_debut)) && (this.date.before(date_fin))
				|| (this.date.equals(date_debut))
				|| (this.date.equals(date_fin))) {
			return true;
		} else
			return false;
	}

	public String getDemi_journee() {
		if (this.demi_journee == matin)
			return "Matin";
		else
			return "Apres-midi";
	}

	public void setDemi_journee(int demi_journee) {
		this.demi_journee = demi_journee;
	}

	public int getDemijournee(){
		if(this.demi_journee==Demi_Journee.matin)
		{
			return matin;
		}
		else 
			return apresmidi;
		
	}

	public int getApresmidi() {
		return apresmidi;
	}
	

	@Override
	public String toString() {
		return "Demi_Journee [demi_journee -> " + getDemi_journee() + ", date=" + this.getDebut(this.demi_journee).toString() + "]";
	}

	@SuppressWarnings("deprecation")
	public void setDebut_DemiJournee(int dj )
	{
		this.debut_matin =new Date();
		this.debut_apresmidi =new Date();
		
		if(dj==Demi_Journee.matin)
		{
			this.debut_matin.setHours(8);
			this.debut_matin.setMinutes(15);
			this.debut_matin.setSeconds(0);
		}
		else{
			this.debut_apresmidi.setHours(13);
			this.debut_apresmidi.setMinutes(15);
			this.debut_apresmidi.setSeconds(0);
		}
	}

	
	@SuppressWarnings("deprecation")
	public void setFin_DemiJournee(int dj )
	{
		this.fin_matin =new Date();
		this.fin_apresmidi =new Date();
		
		if(dj==Demi_Journee.matin)
		{
			this.fin_matin.setHours(11);
			this.fin_matin.setMinutes(45);
			this.fin_matin.setSeconds(0);
		}
		else{
			this.fin_apresmidi.setHours(18);
			this.fin_apresmidi.setMinutes(0);
			this.fin_apresmidi.setSeconds(0);
			
		}
	}

	
	public void setTime(int hours, int minutes) {
		this.date.setHours(hours);
		this.date.setMinutes(minutes);
		this.date.setSeconds(0);

	}
	
	public Date getDebut_matin() {
		return debut_matin;
	}
	
	public Date getDebut(int dj) {
		if(dj==Demi_Journee.matin)
		{
			return debut_matin;
		}
		else{
			return debut_apresmidi;
			
		}
	}

	
	public Date getFin(int dj) {
		if(dj==Demi_Journee.matin)
		{
			return fin_matin;
		}
		else{
			return fin_apresmidi;
			
		}
	}


	
	public Date getFin_matin() {
		return fin_matin;
	}
	
	public Date getDebut_apresmidi() {
		return debut_apresmidi;
	}

	
	public Date getFin_apresmidi() {
		return fin_apresmidi;
	}

	public boolean compareDemi_journee(Demi_Journee dj) {
		if ((this.date.before(dj.date))
				|| (this.date.after(dj.date)) || !(this.getDemi_journee().equals(dj.getDemi_journee())))
			return false;
		else
			return true;

	}

	
	public static void main(String[] args) {
		
        @SuppressWarnings("deprecation")
		Date date = new Date(2011-1900, 9, 22);
        		
        Date date1 = new Date(2011-1900, 9, 22);
        Date date2 = new Date(2011-1900, 9, 22);
		
		Demi_Journee dj1 = new Demi_Journee(date, Demi_Journee.apresmidi);
		
		
		System.out.println(dj1.toString());

	   System.out.println(dj1.isInIntervalle(date1, date2));
		
	//	System.out.println(dj1.compareDemi_journee(dj2));

	}

}
