package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileFilter;

/*@authors Mohamed MEDARHRI
 * 
 */

public class ExcelFilter extends FileFilter{

	String  ext;
	   String  laDescription;
	   public ExcelFilter(String ext){
	      this.ext = ext;
	      
	   }
	   boolean appartient( String suffixe ){
	      
	         if(suffixe.equals(ext))
	            return true;
	         return false;
	   }
	   public boolean accept(File f) {
	      if (f.isDirectory())  return true;
	      String suffixe = null;
	      String s = f.getName();
	      int i = s.lastIndexOf('.');
	      if(i > 0 &&  i < s.length() - 1)
	         suffixe=s.substring(i+1).toLowerCase();
	      return suffixe!=null&&appartient(suffixe);
	   }
	   // la description du filtre
	   public String getDescription() {
	      return laDescription;
	   }

	}
