package co.bytemark.helper;

public interface ConstantVariables {

	// ResetPinSheetName
	public static String RSESTPIN_TESTDATA_SHEET = GetData.fromPropertiesFile(Utility.propFilePath("config"),
			"resetPinSheet");
	// ResetPin
	public static String RESETPIN_OAUTHTOKEN = GetData.fromPropertiesFile(Utility.propFilePath("config"),
			"oauth_token");
	public static String RESETPIN_CLIENT_ID = GetData.fromPropertiesFile(Utility.propFilePath("config"), "client_id");

	// FileName
	public static String RESETPINREQUEST_JSONFILE = "ResetPinRequest.json";

	//URL ResetPin
	public static String RESET_PIN_REQUEST = GetData.fromPropertiesFile(Utility.propFilePath("URL"), "resetPinRequestUri");
	
	//ResetPin SheetName
	public static String RESETPIN_SHEETNAME = GetData.fromPropertiesFile(Utility.propFilePath("config"), "resetPinSheet");
	
	// URL class constants
	public static String URLSHEETNAME = "URL";
	public static String CONFIGSHEETNAME = "ConfigSheet";
	public static String RESPONSE_TYPE = GetData.fromPropertiesFile(Utility.propFilePath("URL"), "JsonReposnse");
	public static String TESTDATA_EXCELSHEETPATH = Utility.excelFilePathExtracter("TestData");

	// MakeOrderSheetName
	public static String makeSheetName = GetData.fromPropertiesFile(Utility.propFilePath("config"),
			"MakeOrderlegacyapp");
	public static String makeOrder2App = GetData.fromPropertiesFile(Utility.propFilePath("config"), "MakeOrder2.0app");
	public static String makeOrder4App = GetData.fromPropertiesFile(Utility.propFilePath("config"), "MakeOrder4.0app");
	public static String configSheetName = GetData.fromPropertiesFile(Utility.propFilePath("config"), "ConfigSheet");
	// TestCase Names
	public static String ordersTestName = "OrdersRegressionSuite";

	public static String GUEST_ORDER = GetData.fromPropertiesFile(Utility.propFilePath("URL"), "guestOrder");
	public static String clientId = GetData.fromPropertiesFile(Utility.propFilePath("config"), "makeOrderClientId");
}