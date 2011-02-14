package csv;

import java.io.File;
import java.io.FileNotFoundException;

/*@authors Mohamed MEDARHRI
 * 
 */
public class TestCSVManager {

	public static void main(String[] args)
	{	try {
		String name_csv = "output.csv";
		String path_csv = System.getProperty("user.dir")+"/"+name_csv;
		 System.out.println("path_csv :"+path_csv);
		  
		CSVManager csv = new CSVManager(new File(path_csv));
        int row =2;
		
		 System.out.println("row count :"+csv.getRowsCount());
		 String nom = csv.getname(row);
		 String prenom = csv.getprenom(row);
		 String provenance = csv.getProvenance(row);
		 String filiere_provenance = csv.getFiliereProvenance(row);
		 String filiere= csv.getFiliere(row);
		 String date_audition = csv.getDateAudition(row);
		 String heure_audition = csv.getHeureAudition(row);
		 String site_audition = csv.getSiteAudition(row);
		 
		for(int i=2;i<csv.getRowsCount();i++)
		 {
		 System.out.println("row :"+i);
		 System.out.println("nom :"+csv.getname(i));
		 System.out.println("prenom :"+csv.getprenom(i));
		 System.out.println("provenance :"+csv.getProvenance(i));
		 System.out.println("filiere de provenance :"+csv.getFiliereProvenance(i));
		 System.out.println("filiere Ensisa :"+csv.getFiliere(i));
		 System.out.println("date_audition :"+csv.getDateAudition(i));
		 System.out.println("heure_audition :"+csv.getHeureAudition(i));
		 System.out.println("site_audition :"+csv.getSiteAudition(i));
		 
		// csv.createAndPersitPersonnes();
		 
		 }
		
		 //System.out.println("nbr de personnes :"+csv.getNbrPersonnes());

		 
		 /*System.out.println("row 3 name "+nom);
		 System.out.println("row 3 numero-dossier "+numero_dossier);
		 System.out.println("row 3 provenance "+provenance);
		 System.out.println("row 3 filiere "+filiere);
		 System.out.println("row 3 date-audition "+date_audition);
		 System.out.println("row 3 heure-audition "+heure_audition);
		 System.out.println("row 3 site-audition "+site_audition);
		*/
		 //}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
