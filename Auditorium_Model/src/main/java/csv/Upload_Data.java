package csv;

import java.io.File;
import java.io.FileNotFoundException;

public class Upload_Data {

	private File xls;
	private File csv;
	String name_csv = "candidats.csv";
	private final String csv_path=System.getProperty("user.dir");
	private String xls_path;
	private ConvertToCsv tocsv;
	private CSVManager managecsv;

	public Upload_Data(File xls, String name_csv) {
		try {

			this.xls = xls;
			this.name_csv = name_csv;
			tocsv = new ConvertToCsv(xls, csv_path+"/"+name_csv);
			csv = new File(csv_path+"/"+name_csv);
			managecsv = new CSVManager(csv);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		
		
		String name_xls = "export.xls";
		String name_csv ="test2.csv";
		String path_xls = System.getProperty("user.dir")+"/"+name_xls;
		File xls = new File(path_xls);		
		Upload_Data upload = new Upload_Data(xls,name_csv);
		System.out.println("succeeded !");

	}
}
