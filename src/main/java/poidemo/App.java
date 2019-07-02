package poidemo;

import java.io.File;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

public class App {
	public static Workbook wb;
	private static Sheet sh;
	private FileInputStream fis;
	private static MissingCellPolicy Row;
	public static ArrayList<String> errors = new ArrayList<String>();
	public static LinkedList<Metadata> metaDataList = new LinkedList<Metadata>();
	public App() {

	}

	public static void main(String[] args) throws Exception {
		App app = new App();
		String file = app.returnFileAddress();
		app.excelWork(file);
//		errors.add(0, "damn daniel");;
		GUIux ux = new GUIux(errors, metaDataList);
		
	}

//************************working with the excel sheet *************************************************************************************
	@SuppressWarnings("static-access")
	private void excelWork(String file) throws Exception {
		fis = new FileInputStream(file);
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet("Sheet1");
//		List<Metadata> metaDataList = new LinkedList<Metadata>();
		int rowEnd = sh.getLastRowNum();
		int rowStart = sh.getFirstRowNum();
		System.out.println("rows: "+rowEnd);
		for (int rowNum = rowStart+1; rowNum <= 16; rowNum++) {
			System.out.println(rowNum);
			Row r = sh.getRow(rowNum);
			int lastColumn = r.getLastCellNum();
//			System.out.println("column:"+lastColumn);
			Metadata metadata = new Metadata();
			int n = 0;
			for (int cn = 0; cn < 7; cn++) {
				String cellValue = "null";
				Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
				if (c == null) {
					cellValue = "null";
					setObject(cn, cellValue, metadata);
					System.out.println("it worked");
				} else {
					switch (c.getCellType()) {

					case NUMERIC:
						//perform action here
						//System.out.println(c.getNumericCellValue() + "\t");

					case STRING:
						cellValue = c.getStringCellValue();
						setObject(cn, cellValue, metadata);
					}

				}
				
			}
			metaDataList.add(metadata);
		}

		//prints a list of all the metadata serialized
		for (int i = 0; i < 16;i++) {
			System.out.println(i);
			System.out.println(metaDataList.get(i).toString());
		}

		System.out.println("done and dusted");
		wb.close();
	}
//************************working with the excel sheet ends********************************************************************************

	// *********************serializes a column into Metadata Object****************************************************
	private void setObject(int n, String cellValue, Metadata metadata) {
		if (n == 0) {
			metadata.setCdCode(cellValue);
		} else if (n == 1)
			metadata.setTrackTitle(cellValue);
		else if (n == 2)
			metadata.setVersion(cellValue);
		else if (n == 3) {
			//validates the instruments given delimited by a comma :)
			validateInstruments(cellValue);
			metadata.setInstruments(cellValue);
		} else if (n == 4)
			metadata.setDate(cellValue);
		else if (n == 5) {

			metadata.getComposer().setfName(cellValue);
		} else if (n == 6)
			metadata.getComposer().setlName(cellValue);
		n = n + 1;

	}

//*********************serializes a column into Metadata Object ends here****************************************************

//**********************************Supporting Methods validation, file GUI, ********************************************************************************

	// Method to validate all the instruments
	// Checks if the given string is equal to atleast of of the instruments in the
	// list
	private static void validateInstruments(String value) {
		StringTokenizer token = new StringTokenizer(value, ",");
		while (token.hasMoreTokens()) {
			String instrument = token.nextToken();
			if (!stringContainsItemFromList(instrument)) {
				errors.add(instrument + " is not a valid instrument");
				System.out.println(instrument + " is not a valid instrument");
			}
		}
	}

	// method to checks if the instrument lies in the list of instruments provided
	public static boolean stringContainsItemFromList(String inputStr) {
		String[] instrumentList = { "electric bass", "steel string guitar", "upright bass", "synth pad" };

		return Arrays.stream(instrumentList).parallel().anyMatch(inputStr::contains);
	}

	public String returnFileAddress() {

		JFileChooser jfc = new JFileChooser("D:\\javaeclipse\\poidemo\\demometadata.xlsx");

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			return (selectedFile.getAbsolutePath());
		}
		return "";
	}
//***************************************Supporting Methods ends********************************************************************************

}
