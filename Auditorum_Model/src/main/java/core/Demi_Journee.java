package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*@authors Mohamed MEDARHRI
 * 
 */

public class Demi_Journee extends GregorianCalendar {

	public static final int matin = 0;
	public static final int apresmidi = 1;
	private int demi_journee;
    private GregorianCalendar debut;
    private GregorianCalendar fin;
	private GregorianCalendar date;

	public Demi_Journee(GregorianCalendar date, int demi_journee) {
		this.date = date;
		this.demi_journee = demi_journee;
		setDebut(demi_journee);
	}


	
	public boolean isInIntervalle(GregorianCalendar date_debut,
			GregorianCalendar date_fin) {
		if ((this.date.after(date_debut)) && (this.date.before(date_fin))
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

	public int getMatin() {
		return matin;
	}

	public int getApresmidi() {
		return apresmidi;
	}

	@Override
	public String toString() {
		return "Demi_Journee [demi_journee -> " + getDemi_journee() + ", date=" + this.date.getTime().toString() + "]";
	}

	public void setDebut(int dj )
	{
		if(dj==this.matin)
		{
			setDebut(8,0);
		}
		else{
			
			setDebut(13,0);
		}
	}
	public void setDebut(int hours, int minutes) {
		this.date.getGregorianChange().setHours(hours);
		this.date.getGregorianChange().setMinutes(minutes);
		
		
	}

	public GregorianCalendar setFin(int hours, int minutes) {
		return fin=new GregorianCalendar(this.getGregorianChange().getYear(),this.getGregorianChange().getMonth(), this.getGregorianChange().getDay(), hours, minutes);
}

	
	public GregorianCalendar getDebut() {
		return debut;
	}

	
	public GregorianCalendar getFin() {
		return fin;
	}

	public boolean compareDemi_journee(Demi_Journee dj) {
		if ((this.date.before(dj.date))
				|| (this.date.after(dj.date)) || !(this.getDemi_journee().equals(dj.getDemi_journee())))
			return false;
		else
			return true;

	}

	
	public static void main(String[] args) {
		GregorianCalendar date1 = new GregorianCalendar(2011, 9, 22);
        
			
		/*GregorianCalendar date2 = new GregorianCalendar(2009, 2, 22);
		
		GregorianCalendar date3 = new GregorianCalendar(2011, 2, 22);
		*/
		
		Demi_Journee dj1 = new Demi_Journee(date1, Demi_Journee.matin);
		
		//Demi_Journee dj2 = new Demi_Journee(date1, Demi_Journee.apresmidi);
		
		//Demi_Journee dj3 = new Demi_Journee(date3, Demi_Journee.apresmidi);
		
       // System.out.println(date1.getGregorianChange().getMinutes());
		System.out.println(dj1.toString());

	 //	System.out.println(dj1.isInIntervalle(date1, date3));
		
	//	System.out.println(dj1.compareDemi_journee(dj2));

	}

}
