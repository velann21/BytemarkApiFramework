package co.bytemark.componenttests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import co.bytemark.config.BaseAdapter;
import co.bytemark.helper.ConstantVariables;
import co.bytemark.helper.ExtentUtility;
import co.bytemark.helper.GetData;
import co.bytemark.helper.Then;
import co.bytemark.models.resetpin.ResetPinRootResponseModel;

public class ResetPinTests extends BaseAdapter {

	public static String testCaseName;

	@DataProvider
	public Object[][] fromExcelDP() throws Exception {
		Integer startRow = Integer.parseInt(GetData.fromExcelOneData(ConstantVariables.TESTDATA_EXCELSHEETPATH,
				ConstantVariables.CONFIGSHEETNAME, 1, 1));
		Integer startColumn = Integer.parseInt(GetData.fromExcelOneData(ConstantVariables.TESTDATA_EXCELSHEETPATH,
				ConstantVariables.CONFIGSHEETNAME, 1, 2));
		Integer endRow = Integer.parseInt(GetData.fromExcelOneData(ConstantVariables.TESTDATA_EXCELSHEETPATH,
				ConstantVariables.CONFIGSHEETNAME, 1, 3));
		Integer endColumn = Integer.parseInt(GetData.fromExcelOneData(ConstantVariables.TESTDATA_EXCELSHEETPATH,
				ConstantVariables.CONFIGSHEETNAME, 1, 4));
		Object[][] fromExcelDp1 = GetData.fromExcelDp(ConstantVariables.TESTDATA_EXCELSHEETPATH,
				ConstantVariables.RESETPIN_SHEETNAME, startRow, startColumn, endRow, endColumn);
		return fromExcelDp1;
	}

	@Test(dataProvider = "fromExcelDP")
	public void reset(String S_No, String testCaseName) {
		ResetPinTests.testCaseName = testCaseName;
		logger = ExtentUtility.startTest(testCaseName);
		ExtentUtility.infoLog(testCaseName + "TestStarted");
		ResetPinRootResponseModel resetPinRequest = resetPinService.resetPinRequest(Integer.parseInt(S_No),
				ConstantVariables.RSESTPIN_TESTDATA_SHEET);
		Then.thenValidation().assertEquals(resetPinRequest.getData().getStatus(), "success");
	}

}
