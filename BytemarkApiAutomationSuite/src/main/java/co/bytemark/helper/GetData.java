package co.bytemark.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class GetData {
	// To Take the directory path
	static String userDir = System.getProperty("user.dir");
	static String propFilePath = userDir + "/Resources/external_data/config.properties";

	// GlobalVariables for fromExcelRangeData
	static File file;
	static Workbook create;
	static Sheet sheet;

	// Global var for frompropertiesFile
	static File file1;

	// Method is to create the workbook
	public static Workbook workBookFactory(String filePath) {
		if (create == null) {
			try {
				file = new File(filePath);
				FileInputStream fis = new FileInputStream(file);
				create = WorkbookFactory.create(fis);
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
				e.printStackTrace();
			}
		}
		return create;
	}

	// Method is to take the value from mention ranges
	public static ArrayList<String> fromExcelRangeData(String filePath, String sheetName, int startRow, int startColumn,
			int endRow, int endColumn) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		Workbook create = workBookFactory(filePath);
		sheet = create.getSheet(sheetName);
		String cellvalues = "";
		for (int startRead = startRow; startRead <= endRow; startRead++) {
			for (int columnRead = startColumn; columnRead < endColumn; columnRead++) {
				try {
					cellvalues = sheet.getRow(startRead).getCell(columnRead).toString();
					list.add(cellvalues);
				} catch (Exception e) {
					list.add(cellvalues);
				}

			}
		}
		return list;
	}

	public static int fromExcelUsedRangeRow(String fileName, String sheetName) {
		int lastRow = 0;
		try {
			// FileInputStream fis = new FileInputStream(fileName);
			// Workbook workbook = WorkbookFactory.create(fis);
			Workbook create = workBookFactory(fileName);
			Sheet sheet = create.getSheet(sheetName);
			lastRow = sheet.getLastRowNum();

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		}

		return lastRow;
	}

	public static String fromExcelOneData(String filePath, String sheetName, int rowNo, int intCellNo)
			throws Exception {
		Workbook create = workBookFactory(filePath);
		sheet = create.getSheet(sheetName);
		String value = sheet.getRow(rowNo).getCell(intCellNo).toString();
		return value;
	}

	public static void fromExcelValueExtractor(String fileName, String configSheetName, String testCaseName,
			String inputSheet, int valuePickerColumn) {

		try {
			ArrayList<String> list = GetData.fromExcelRangeData(fileName, configSheetName, 1, 0,
					GetData.fromExcelUsedRangeRow(fileName, configSheetName), 1);
			System.out.println(list);
			if (list.contains(testCaseName) == true) {
				int index = list.indexOf(testCaseName);
				System.out.println(index);
				Integer startRow = Integer.parseInt(GetData.fromExcelOneData(fileName, configSheetName, index + 1, 1));
				Integer endRow = Integer.parseInt(GetData.fromExcelOneData(fileName, configSheetName, index + 1, 2));
				System.out.println(startRow);
				System.out.println(endRow);
				ArrayList<String> list1 = GetData.fromExcelRangeData(fileName, inputSheet, 0, 0,
						GetData.fromExcelUsedRangeRow(fileName, inputSheet), 1);
				System.out.println(list1);
				if (list1.contains(testCaseName)) {
					int index2 = list1.indexOf(testCaseName);
					System.out.println(index2);
					int startpickInput = index2 + startRow;
					System.out.println(startpickInput);
					int endInputs = index2 + endRow;
					System.out.println(endInputs);
					ArrayList<?> list3 = GetData.fromExcelRangeData(fileName, inputSheet, startpickInput,
							valuePickerColumn, endInputs, 2);
					System.out.println(list3);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String fromPropertiesFile(String filePath, String key) {
		String value = null;
		try {
			file1 = new File(filePath);
			if (!file1.exists() == true) {
				file1.createNewFile();
			}
			FileInputStream fis = new FileInputStream(file1);
			Properties properties = new Properties();
			properties.load(fis);
			value = properties.getProperty(key);
		} catch (IOException io) {
		}
		return value;
	}

	@DataProvider
	public static Object[][] fromExcelDp(String filePath, String sheetName, int startRowParam, int startColumnParam,
			int lastRow, int lastColumn) {
		int startArray = 0;
		@SuppressWarnings("unused")
		File file = new File(filePath);
		Object[][] obj = null;
		try {
			Workbook wb = workBookFactory(filePath);
			Sheet sheet = wb.getSheet(sheetName);
			int arraySize = lastRow - startRowParam;
			obj = new Object[arraySize][lastColumn];
			for (int startRow = startRowParam; startRow < lastRow; startRow++) {

				for (int startColumn = startColumnParam; startColumn < lastColumn; startColumn++) {
					try {
						obj[startArray][startColumn] = sheet.getRow(startRow).getCell(startColumn).toString();
					} catch (NullPointerException ne) {
					}
				}
				startArray++;
			}
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static void columnValueBasedReader(String filePath, String sheetName) {
		Workbook wb = GetData.workBookFactory(filePath);
		Sheet sheet = wb.getSheet(sheetName);
		@SuppressWarnings("unused")
		Row row = sheet.getRow(0);

	}

}
