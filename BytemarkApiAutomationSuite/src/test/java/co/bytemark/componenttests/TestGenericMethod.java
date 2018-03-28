package co.bytemark.componenttests;

import org.testng.annotations.DataProvider;

import co.bytemark.helper.ConstantVariables;
import co.bytemark.helper.ExcelUtility;
import co.bytemark.helper.GetData;
import co.bytemark.helper.Utility;

public class TestGenericMethod {
	@DataProvider
	public static Object[][] fromExcelDP(int rowNo,String sheetName) throws Exception {
		Integer startRow = Integer.parseInt(
				GetData.fromExcelOneData(Utility.excelFilePathExtracter("TestData"), "ConfigSheet", rowNo, 1));
		Integer startColumn = Integer.parseInt(
				GetData.fromExcelOneData(Utility.excelFilePathExtracter("TestData"), "ConfigSheet", rowNo, 2));
		Integer endRow = Integer.parseInt(
				GetData.fromExcelOneData(Utility.excelFilePathExtracter("TestData"), "ConfigSheet", rowNo, 3));
		Integer endColumn = Integer.parseInt(
				GetData.fromExcelOneData(Utility.excelFilePathExtracter("TestData"), "ConfigSheet", rowNo, 4));
		
//		int startRow = Integer.parseInt(ExcelUtility.valueExtracter(rowNo, ConstantVariables.configSheetStartRow, ConstantVariables.configSheetName));
//		int startColumn=Integer.parseInt(ExcelUtility.valueExtracter(rowNo, ConstantVariables.configSheetStartColumn, ConstantVariables.configSheetName));
//		int endRow = Integer.parseInt(ExcelUtility.valueExtracter(rowNo, ConstantVariables.configSheetEndRow, ConstantVariables.configSheetName));
//		int endColumn = Integer.parseInt(ExcelUtility.valueExtracter(rowNo, ConstantVariables.configSheetEndColumn, ConstantVariables.configSheetName));
//		
		Object[][] fromExcelDp1 = GetData.fromExcelDp(Utility.excelFilePathExtracter("TestData"),
				sheetName, startRow, startColumn, endRow, endColumn);
		return fromExcelDp1;
	}
}
