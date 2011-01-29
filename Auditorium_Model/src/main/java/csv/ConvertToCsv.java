package csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/*@authors Mohamed MEDARHRI
 * 
 */

public class ConvertToCsv {

	private final String output_csv = "output.csv";
	private String input_xls = "export.xls"; // Excel document to be imported
	private File input;
	private File output;

	public ConvertToCsv(File input,String output_path) {
		try {
			this.input = input;
			OutputStream os = (OutputStream) new FileOutputStream(output_path);
			String encoding = "UTF8";
			OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
			BufferedWriter bw = new BufferedWriter(osw);
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));
			Workbook w = Workbook.getWorkbook(input, ws);

			// Gets the sheets from workbook
			for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++) {
				Sheet s = w.getSheet(sheet);

				bw.write(s.getName());

				bw.newLine();

				Cell[] row = null;

				// Gets the cells from sheet
				for (int i = 0; i < s.getRows(); i++) {
					row = s.getRow(i);

					if (row.length > 0) {
						bw.write(row[0].getContents());
						for (int j = 1; j < row.length; j++) {
							bw.write(',');
							bw.write(row[j].getContents());
						}
					}
					bw.newLine();
				}
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error in convertCSV() " + e.toString());
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error in convertCSV() " + e.toString());
		}
	}

	public static void main(String[] args) {
		
		
		String name_xls = "export.xls";
		String name_csv = "test.csv";
		String path_xls = System.getProperty("user.dir")+"/"+name_xls;
		String path_csv = System.getProperty("user.dir")+"/"+name_csv;
		File xls = new File(path_xls);
	     
		ConvertToCsv test = new ConvertToCsv(xls,path_csv);

	}

}
