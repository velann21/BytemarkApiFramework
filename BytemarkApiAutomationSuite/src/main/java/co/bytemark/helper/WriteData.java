package co.bytemark.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteData {
	public static void toExcelSinglevalueWritter(String fileName, String sheetName, int rowNum, int cellno,
			String value) {
		File file = new File(fileName);
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbk = WorkbookFactory.create(fis);
			Sheet sheet = workbk.getSheet(sheetName);
			Row row = sheet.getRow(rowNum);
			row.createCell(cellno).setCellValue(value);
			FileOutputStream fos = new FileOutputStream(file);
			workbk.write(fos);
			workbk.close();
			fos.close();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void toExcelSinglevalueWritter(String filePath, String sheetName, String rowkey,
			String startRowColumnIdentifier, String clmnKey, String newValue) {
		File file = new File(filePath);
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbk = WorkbookFactory.create(fis);
			Sheet sheet = workbk.getSheet(sheetName);
			int startRowRead = 1;
			for (int columnfinder = 0; columnfinder <= sheet.getRow(0).getLastCellNum(); columnfinder++) {
				if (sheet.getRow(0).getCell(columnfinder).getStringCellValue().equals(startRowColumnIdentifier)) {
					for (startRowRead = 1; startRowRead < sheet.getLastRowNum(); startRowRead++) {
						if (sheet.getRow(startRowRead).getCell(columnfinder).toString().equals(rowkey)) {
							break;
						}
					}
					break;
				}
			}
			for (int columnRead = 0; columnRead <= sheet.getRow(0).getLastCellNum(); columnRead++) {
				if (sheet.getRow(0).getCell(columnRead).getStringCellValue().equals(clmnKey)) {
					sheet.getRow(startRowRead).createCell(columnRead).setCellValue(newValue);
					break;
				}
			}
			FileOutputStream fos = new FileOutputStream(file);
			workbk.write(fos);
			workbk.close();
			fos.close();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
