package co.bytemark.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	// ArrayList to add the sheetnames to find the duplicates
	static HashMap<String, HashMap<String, ArrayList<String>>> sheetNames = new HashMap<String, HashMap<String, ArrayList<String>>>();
	public static HashMap<String, ArrayList<String>> fromExcelRangeData12 = null;
	static private Workbook create;
	static private Sheet sheet;
	static Workbook wbk;
	private static Sheet getSheet;
	private static Row createRow;
	private static Cell createCell;
	static Map<String, String> map = null;

	public static void toExcel(String filePath, String sheetName, int startRow, Map<?, ?> map) throws Exception {
		File file1 = new File(filePath);
		if (file1.exists() == true) {
			FileInputStream fis = new FileInputStream(file1);
			wbk = WorkbookFactory.create(fis);
			getSheet = wbk.getSheet(sheetName);
			createRow = getSheet.createRow(startRow);
			int columnReader = 0;
			@SuppressWarnings("unused")
			int columnRead = 0;
			Set<?> set = map.entrySet();
			Iterator<?> i1 = set.iterator();
			while (i1.hasNext()) {
				String excelkey = getSheet.getRow(0).getCell(columnReader).toString();
				Entry<?, ?> entry = (Entry<?, ?>) i1.next();
				Set<?> keySet = map.keySet();
				Iterator<?> itr = keySet.iterator();
				while (itr.hasNext()) {
					String key1 = (String) itr.next();
					if (key1.equals(excelkey)) {
						createRow = getSheet.getRow(startRow);
						createCell = createRow.createCell(columnReader);
						createCell.setCellValue((String) entry.getValue());
						break;
					}
					columnRead++;
				}
				columnReader++;
			}
		}
		FileOutputStream fos = new FileOutputStream(filePath);
		wbk.write(fos);
		fos.close();
		wbk.close();
	}

	public static HashMap<String, ArrayList<String>> fromExcelRangeData12(String filePath, String sheetName,
			int startRow) throws Exception {
		HashMap<String, ArrayList<String>> hp = new HashMap<String, ArrayList<String>>();
		ArrayList<String> list = null;
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		create = WorkbookFactory.create(fis);
		sheet = create.getSheet(sheetName);
		String cellvalues = "";
		String key = null;
		// System.out.println(sheet.getLastRowNum());
		for (int columnRead = 0; columnRead < sheet.getRow(0).getLastCellNum(); columnRead++) {
			list = new ArrayList<String>();
			for (int startRead = 1; startRead < sheet.getLastRowNum(); startRead++) {
				try {
					key = sheet.getRow(0).getCell(columnRead).toString();
					cellvalues = sheet.getRow(startRead).getCell(columnRead).toString();
					System.out.println(cellvalues);
					list.add(cellvalues);
				} catch (Exception e) {
				}
			}
			hp.put(key, list);
		}
		return hp;

	}

	public static String fromExcelColumnKeyFinder(String filePath, String sheetName, String rowkey, int columnNumber,
			String clmnKey) throws Exception {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		create = WorkbookFactory.create(fis);
		sheet = create.getSheet(sheetName);
		String key = null;
		//int i = 0;
		for (int startRowRead = 1; startRowRead < sheet.getLastRowNum(); startRowRead++) {
			if (sheet.getRow(startRowRead).getCell(columnNumber).toString().equals(rowkey))
				for (int columnRead = 0; columnRead <= sheet.getRow(0).getLastCellNum(); columnRead++) {
					if (sheet.getRow(0).getCell(columnRead).getStringCellValue().equals(clmnKey)) {
						key = sheet.getRow(startRowRead).getCell(columnRead).toString();
						break;
					}
				}
		}
		return key;
	}

	public static String fromExcelColumnKeyFinder(String filePath, String sheetName, String rowkey,
			String startRowColumnIdentifier, String clmnKey) throws Exception {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		create = WorkbookFactory.create(fis);
		sheet = create.getSheet(sheetName);
		String key = null;
		//int i = 0;
		int startRowRead = 0;
		for (int columnfinder = 0; columnfinder <= sheet.getRow(0).getLastCellNum(); columnfinder++)
			if (sheet.getRow(0).getCell(columnfinder).getStringCellValue().equals(startRowColumnIdentifier)) {
				for (startRowRead = 1; startRowRead < sheet.getLastRowNum(); startRowRead++) {
					if (sheet.getRow(startRowRead).getCell(columnfinder).toString().equals(rowkey)) {
						break;
					}
				}
				break;
			}
		for (int columnRead = 0; columnRead <= sheet.getRow(0).getLastCellNum(); columnRead++) {
			if (sheet.getRow(0).getCell(columnRead).getStringCellValue().equals(clmnKey)) {
				key = sheet.getRow(startRowRead).getCell(columnRead).toString();
				break;
			}

		}
		return key;
	}

	public static String valueExtracter(int startRow, String key, String dataSheetName) {
		String userDir = GetData.userDir;
		String propfile = userDir + "/Resources/external_data/config.properties";
		String file = GetData.fromPropertiesFile(propfile, "Inputsheetpath");
		String filename1 = userDir + file;
		if (!sheetNames.keySet().contains(dataSheetName) == true) {
			try {
				fromExcelRangeData12 = ExcelUtility.fromExcelRangeData12(filename1, dataSheetName, startRow);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sheetNames.put(dataSheetName, fromExcelRangeData12);
			fromExcelRangeData12 = null;
		} else {
			fromExcelRangeData12 = sheetNames.get(dataSheetName);
		}

		try {
			if (fromExcelRangeData12 == null) {
				fromExcelRangeData12 = ExcelUtility.fromExcelRangeData12(filename1, dataSheetName, startRow);
				System.out.println(fromExcelRangeData12);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		@SuppressWarnings("rawtypes")
		ArrayList list = fromExcelRangeData12.get(key);
		String value = (String) list.get(startRow);
		return value;

	}
}
