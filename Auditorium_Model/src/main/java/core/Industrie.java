package core;

import java.util.ArrayList;


/*@authors Mohamed MEDARHRI
 * 
 */

public class Industrie extends Affiliation {



	private ArrayList<Filiere> filieres;
	private String name="Industrie";
	private String address;
	private String affiliation;
	
	public Industrie(String name,String address) {
		this.name=name;
		this.affiliation="Industrie";
		this.address=address;
		this.filieres= new ArrayList<Filiere>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addFiliere(Filiere filiere)
	{
		filieres.add(filiere);
	}
	public void removeFiliere(Filiere filiere)
	{
		filieres.remove(filiere);
	}
	
	public String getFilieresNames()
	{
		int i;
		StringBuffer buff=new StringBuffer();
		for(i=0;i<filieres.size();i++)
		{
		  buff.append(filieres.get(i).toString());
		}
		return buff.toString();
		
	}
	
	
	@Override
	public void setAffiliation(String name) {
		
		this.name="Industrie";
		
	}
	
	

	public ArrayList<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(ArrayList<Filiere> filieres) {
		this.filieres = filieres;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getAffiliation() {
		// TODO Auto-generated method stub
		return affiliation;
	}

	public static void main(String[] args)
	{
		Industrie Peugeot = new Industrie("PG","mulh");
		Filiere auto = new Filiere("AUTO");
		Peugeot.addFiliere(auto);
		System.out.println(Peugeot.getFilieresNames());
	}
	
}
