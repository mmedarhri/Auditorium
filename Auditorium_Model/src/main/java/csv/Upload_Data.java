package csv;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

import utils.ExcelFilter;

/*@authors Mohamed MEDARHRI
 * 
 */
public class Upload_Data {

	private File xls;
	public File getXls() {
		return xls;
	}

	public void setXls(File xls) {
		this.xls = xls;
	}

	public File getCsv() {
		return csv;
	}

	public void setCsv(File csv) {
		this.csv = csv;
	}

	public String getName_csv() {
		return name_csv;
	}

	public void setName_csv(String name_csv) {
		this.name_csv = name_csv;
	}

	public String getXls_path() {
		return xls_path;
	}

	public void setXls_path(String xls_path) {
		this.xls_path = xls_path;
	}

	public ConvertToCsv getTocsv() {
		return tocsv;
	}

	public void setTocsv(ConvertToCsv tocsv) {
		this.tocsv = tocsv;
	}

	public CSVManager getManagecsv() {
		return managecsv;
	}

	public void setManagecsv(CSVManager managecsv) {
		this.managecsv = managecsv;
	}

	public JFileChooser getFc() {
		return fc;
	}

	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}

	public ExcelFilter getExcelfilter() {
		return excelfilter;
	}

	public void setExcelfilter(ExcelFilter excelfilter) {
		this.excelfilter = excelfilter;
	}

	public String getCsv_path() {
		return csv_path;
	}

	private File csv;
	String name_csv = "candidats.csv";
	private final String csv_path = System.getProperty("user.dir");
	private String xls_path;
	private ConvertToCsv tocsv;
	private CSVManager managecsv;
	private JFileChooser fc;
	private ExcelFilter excelfilter;

	public Upload_Data() {
		try {
			fc = new JFileChooser(".");
			excelfilter = new ExcelFilter("xls");
			fc.addChoosableFileFilter(excelfilter);

			int retour = fc.showOpenDialog(null);
			if (retour == JFileChooser.APPROVE_OPTION) {
				this.xls = new File(fc.getSelectedFile().getAbsolutePath());
			}

			tocsv = new ConvertToCsv(xls, csv_path + "/" + name_csv);
			csv = new File(csv_path + "/" + name_csv);
			managecsv = new CSVManager(csv);
			managecsv.createAndPersitPersonnes();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		String name_csv = "output.csv";
		try{
		Upload_Data upload = new Upload_Data();
		}catch(Exception e)
		{
			System.out.println("Error in Upload_Data "+e.toString());
		}
		

	}
}
