package csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Vector;

import bdDManager.JDOHbase;

import core.Personne;
import core.Propriete;

/*@authors Mohamed MEDARHRI
 * 
 */

public class CSVManager {

   private int m_rowsCount;
   private int m_colsCount;
   private int column_numerodossier=0;
   private int column_nom_prenom=1;
   private int column_provenance=2;
   private int column_filiere=3;
   private int column_date_audition=8;
   private int column_heure_audition=9;
   private int column_lieu_audition=11;
   private final int row_initiale=1; //contient les familles de column nom , prenom ...
   public int getM_rowsCount() {
	return m_rowsCount;
}

public void setM_rowsCount(int m_rowsCount) {
	this.m_rowsCount = m_rowsCount;
}

public int getM_colsCount() {
	return m_colsCount;
}

public void setM_colsCount(int m_colsCount) {
	this.m_colsCount = m_colsCount;
}

public int getColumn_numerodossier() {
	return column_numerodossier;
}

public void setColumn_numerodossier(int column_numerodossier) {
	this.column_numerodossier = column_numerodossier;
}

public int getColumn_nom_prenom() {
	return column_nom_prenom;
}

public void setColumn_nom_prenom(int column_nom_prenom) {
	this.column_nom_prenom = column_nom_prenom;
}

public int getColumn_provenance() {
	return column_provenance;
}

public void setColumn_provenance(int column_provenance) {
	this.column_provenance = column_provenance;
}

public int getColumn_filiere() {
	return column_filiere;
}

public void setColumn_filiere(int column_filiere) {
	this.column_filiere = column_filiere;
}

public int getColumn_date_audition() {
	return column_date_audition;
}

public void setColumn_date_audition(int column_date_audition) {
	this.column_date_audition = column_date_audition;
}

public int getColumn_heure_audition() {
	return column_heure_audition;
}

public void setColumn_heure_audition(int column_heure_audition) {
	this.column_heure_audition = column_heure_audition;
}

public int getColumn_lieu_audition() {
	return column_lieu_audition;
}

public void setColumn_lieu_audition(int column_lieu_audition) {
	this.column_lieu_audition = column_lieu_audition;
}

public Propriete getPropriete() {
	return propriete;
}

public void setPropriete(Propriete propriete) {
	this.propriete = propriete;
}

public Personne getPersonne() {
	return personne;
}

public void setPersonne(Personne personne) {
	this.personne = personne;
}

public ArrayList<Personne> getPersonnes() {
	return personnes;
}

public void setPersonnes(ArrayList<Personne> personnes) {
	this.personnes = personnes;
}

public Vector getM_fileContent() {
	return m_fileContent;
}

public void setM_fileContent(@SuppressWarnings("rawtypes") Vector m_fileContent) {
	this.m_fileContent = m_fileContent;
}

public int getRow_initiale() {
	return row_initiale;
}

private Propriete propriete;
   private JDOHbase jdohb;
   private Personne personne;
   private ArrayList<Personne> personnes;
   
   @SuppressWarnings("rawtypes")
private Vector m_fileContent;
   private final static char CELL_SEPARATOR = ',';

   /**
    * Method CSVFile.
    * @param path le chemin du fichier � parser.
    * @throws FileNotFoundException si le fichier sp�cifi� n'existe pas.
    */
   @SuppressWarnings("rawtypes")
public CSVManager(File file) throws FileNotFoundException {
      m_fileContent = new Vector();
      FileReader fileReader = new FileReader(file.getAbsolutePath());
      readFromFile(fileReader);
      fitVectorsToSize();
      personnes = new ArrayList<Personne>();
      jdohb = new JDOHbase();
   }

   /**
    * Method CSVFile.
    * @param reader un reader dans lequel on lit le fichier CSV.
    */
   public CSVManager(Reader reader) {
      m_fileContent = new Vector();
      readFromFile(reader);
      fitVectorsToSize();
   }

   private void fitVectorsToSize() {
      m_fileContent.setSize(getRowsCount());
      int fileSize = getRowsCount();
      int colCount = getColsCount();
      for (int i = 0; i < fileSize; i++) {
         Vector aRow = (Vector)m_fileContent.get(i);
         if (aRow == null) {
            m_fileContent.set(i, new Vector());
            aRow = (Vector)m_fileContent.get(i);
         }
         aRow.setSize(colCount);
      }
   }

   /**
    * Method readFromFile.
    * @param path
    */
   private void readFromFile(Reader reader) {
      BufferedReader buffReader = new BufferedReader(reader);
      if (buffReader != null) {
         try {
            String tempLine;
            tempLine = buffReader.readLine();
            while (tempLine != null) {
               readFromLine(tempLine);
               tempLine = buffReader.readLine();
            }
         } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.toString());
         } finally {
            try {
               buffReader.close();
            } catch (IOException e) {
               System.err.println(
                  "Erreur closing CSV file: "
                  + e.toString()
               );
            }
         }
      }
      System.runFinalization();
      System.gc();
   }

   /**
    * Method readFromLine.
    * @param tempLine
    */
   private void readFromLine(String tempLine) {
      if (tempLine == null) {
         return;
      }
      Vector currentLine = new Vector();
      m_fileContent.add(currentLine);
      m_rowsCount++;
//      setRowsCount(getRowsCount() + 1);
      if (tempLine.trim().length() == 0) {
         return;
      }
      int colCount = 0;
      int cursorBegin = 0;
      int cursorEnd = tempLine.indexOf(CELL_SEPARATOR);
      while (cursorBegin > -1) {
         if (cursorEnd == -1) {
            currentLine.add(tempLine.substring(cursorBegin));
            cursorBegin = cursorEnd;
         } else {
            currentLine.add(tempLine.substring(cursorBegin, cursorEnd));
            cursorBegin = cursorEnd + 1;
         }
         cursorEnd = tempLine.indexOf(CELL_SEPARATOR, cursorBegin);
         colCount++;
      }
      if (colCount > getColsCount()) {
         setColsCount(Math.max(getColsCount(), colCount));
      }
   }


   /**
    * Returns the colsCount.
    * @return int
    */
   public int getColsCount() {
      return m_colsCount;
   }

   /**
    * Returns the rowsCount.
    * @return int
    */
   public int getRowsCount() {
      return m_rowsCount;
   }

   /**
    * Sets the colsCount.
    * @param colsCount The colsCount to set
    */
   public void setColsCount(int colsCount) {
      m_colsCount = colsCount;
      fitVectorsToSize();
   }

   /**
    * Sets the rowsCount.
    * @param rowsCount The rowsCount to set
    */
   public void setRowsCount(int rowsCount) {
      m_rowsCount = rowsCount;
      fitVectorsToSize();
   }

   /**
    * Method getData.
    * @param row la ligne voulue
    * @param col la colonne voulue
    * @return String la valeur � l'enplacement sp�cifi�. Null si outOfBound.
    */
   public String getData(int row, int col) {
      if (row < 0
         || col < 0
         || row > (getRowsCount() - 1)
         || col > (getColsCount() - 1)) {
         return null;
      }
      try {
         Vector theRow = (Vector)m_fileContent.get(row);
         String result = (String)theRow.get(col);
         return (result == null ? "" : result);
      } catch (IndexOutOfBoundsException e) {
         return "";
      }
   }

   /**
    * Method setData.
    * @param row le num�ro de ligne (commence � 0).
    * @param col le num�ro de colonne (commence � 0).
    * @param data les donn�es � ins�rer.
    */
   public void setData(int row, int col, String data) {
      if (row < 0
         || col < 0
         || row > (getRowsCount() - 1)
         || col > (getColsCount() - 1)) {
         throw new IndexOutOfBoundsException();
      }
      Vector theRow = (Vector)m_fileContent.get(row);
      theRow.setElementAt(data, col);
   }

   public String getname(int row)
   { try{
	   return getData(row, column_nom_prenom).split(" ")[0];
   } catch(Exception e)
   {
	   System.out.println(e.toString());
	   return " ";
   }
   }
   
   public String getprenom(int row)
   {    try{
	   return getData(row, column_nom_prenom).split(" ")[1];
   } catch(Exception e)
   {
	   System.out.println(e.toString());
	   return " ";
   }
   }
   
   public String getNumeroDossier(int row)
   { try{
	   return getData(row,column_numerodossier);
   } catch(Exception e)
   {
	   System.out.println(e.toString());
	   return " ";
   }
   }
   
   public String getProvenance(int row)
   {try{
	   return getData(row, column_provenance).split(" ")[0];
   } catch(Exception e)
   {
	   System.out.println(e.toString());
	   return " ";
   }
   }
   public String getFiliereProvenance(int row)
   {
	   try{
	   return getData(row, column_provenance).split(" ")[1];
	   } catch(Exception e)
	   {
		   System.out.println(e.toString());
		   return " ";
	   }
	  
   }
   
   public String getFamiliesColumn_Nom()
   {
	   return getData(row_initiale, column_nom_prenom);
   }
   public String getFamiliesColumn_provenance()
   {
	   return getData(row_initiale, column_provenance);
   }
   public String getFamiliesColumn_filiere()
   {
	   return getData(row_initiale, column_filiere);
   }
   public String getFamiliesColumn_date_oral()
   {
	   return getData(row_initiale, column_date_audition);
   }
   public String getFamiliesColumn_heure_oral()
   {
	   return getData(row_initiale, column_heure_audition);
   }
   public String getFamiliesColumn_Lieu_oral()
   {
	   return getData(row_initiale, column_lieu_audition);
   }
   public String getFiliere(int row)
   {
	   return getData(row, column_filiere);
   }

   public String getDateAudition(int row)
   {
	   return getData(row, column_date_audition);
   }
   
   public String getHeureAudition(int row)
   {
	   return getData(row, column_heure_audition);
   }
   
   public String getSiteAudition(int row)
   {
	   return getData(row, column_lieu_audition);
   }
   
   public void addPropriete(int row)
   {
	   propriete= new Propriete(getname(row), getprenom(row));
   }
   
   public Propriete getPropriete(int row)
   {
	   return propriete= new Propriete(getname(row), getprenom(row));
	   
   }
   
   public void addPersonne(int row)
   {
	   personne= new Personne(getPropriete(row));
	   jdohb.persisterPersonne(personne);
	   personnes.add(personne);
   }
   
   
   public void createAndPersitPersonnes()
   {
	   int i=0;
	   
	   for(;i<getRowsCount();i++) 
	   {
		   addPersonne(i);
	   }
   }
   
   public void createProprietes()
   {
	   int i=0;
	   
	   for(;i<getRowsCount();i++) 
	   {
		   addPropriete(i);
	   }
   }
   
   
   
   public int getNbrPersonnes()
   {
	   
	   return personnes.size();
   }
   
   /**
    * Method write.
    * @param filePath le fichier dans lequel sauver les donn�es.
    * @throws IOException si une erreur survient.
    */
   public void write(String filePath) throws IOException {
      FileWriter fileWriter = new FileWriter(filePath);
      write(fileWriter);
   }

   /**
    * Method write.
    * @param aWriter le writer dans lequel on veut �crire les donn�es.
    * @throws IOException si une erreur survient.
    */
   public void write(Writer aWriter) throws IOException {
      BufferedWriter writer;
      writer = new BufferedWriter(aWriter);
      int fileSize = getRowsCount();
      int colCount = getColsCount();
      for (int i = 0; i < fileSize; i++) {
         for (int j = 0; j < colCount; j++) {
            writer.write(getData(i, j));
            if (j + 1 < colCount) {
               writer.write(CELL_SEPARATOR);
            }
         }
         if (i + 1 < fileSize) {
            writer.write("\n");
         }
      }
      writer.flush();
      writer.close();
   }
}