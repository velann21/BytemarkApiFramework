package co.bytemark.config;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import co.bytemark.contexts.Contexts;
import co.bytemark.helper.AppZip;
import co.bytemark.helper.ExcelUtility;
import co.bytemark.helper.ExtentUtility;
import co.bytemark.helper.GetData;
import co.bytemark.helper.Utility;
import co.bytemark.instances.ClassInstances;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseAdapter extends ClassInstances {
	public ExtentReports extentReports;
	public ExtentTest logger;
	protected String baseurl;

	public static RequestSpecification givenP;
	Object testCaseName = null;
	protected Contexts ctxt;

	@BeforeSuite
	public void preconditionBeforeSuite() {
		
		instance();
		System.out.println("Am in before suite");
		// code to zip the reports and put into test-output folder
		Date date = new Date();
		String dateString = date.toString().replace("-", ":");
		String OUTPUT_ZIP_FILE = Utility.userDirPath + "/Results/test-output " + dateString + ".zip";
		String SOURCE_FOLDER = Utility.userDirPath + "/test-output";
		AppZip appZip = new AppZip(OUTPUT_ZIP_FILE, SOURCE_FOLDER);
		appZip.generateFileList(new File(SOURCE_FOLDER));
		appZip.zipIt(OUTPUT_ZIP_FILE);

		// code to set the Registered Email and password in context
		ctxt = new Contexts();
		try {
			//ctxt.OBJECTGETTER=ctxt;
//			ctxt.setContext("RegEmail",
//					ExcelUtility.fromExcelColumnKeyFinder(Utility.excelFilePathExtracter("TestData"),
//							GetData.fromPropertiesFile(Utility.propFilePath("config"), "RegistrationApiInputs"),
//							"Marta_Kapstch_Registration_Success_05", 1, "Email"));
//			ctxt.setContext("RegEmailPwd",
//					ExcelUtility.fromExcelColumnKeyFinder(Utility.excelFilePathExtracter("TestData"),
//							GetData.fromPropertiesFile(Utility.propFilePath("config"), "RegistrationApiInputs"),
//							"Marta_Kapstch_Registration_Success_05", 1, "Password"));
//			System.out.println(ctxt.getContext("RegEmailPwd"));
//			ctxt.setContext("CSTLOGINEmail",
//					ExcelUtility.fromExcelColumnKeyFinder(Utility.excelFilePathExtracter("TestData"),
//							GetData.fromPropertiesFile(Utility.propFilePath("config"), "LoginApiInputs"),
//							"SuccesfullMartaCSTLogin", "TestId", "Email"));
//			System.out.println(ctxt.getContext("CSTLOGINEmail"));
//			
//			ctxt.setContext("CSTLOGINPASSWORD",
//					ExcelUtility.fromExcelColumnKeyFinder(Utility.excelFilePathExtracter("TestData"),
//							GetData.fromPropertiesFile(Utility.propFilePath("config"), "LoginApiInputs"),
//							"SuccesfullMartaCSTLogin", "TestId", "Password"));
//			System.out.println(ctxt.getContext("CSTLOGINPASSWORD"));
//			System.out.println("AAAA"+ctxt.getContext("RegEmail"));
//			System.out.println("BBBBB"+ctxt.getContext("RegEmailPwd"));
			Contexts.OBJECTGETTER = ctxt;
			System.out.println("Objectss"+Contexts.OBJECTGETTER);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeTest
	public void preconditionsBeforeTest() {
		extentReports = ExtentUtility.extentReportsInstance("extent-config", "ByteMark", "Platform Automation",
				"Singaravelan");
	}

	// @Parameters("propertyName")
	@BeforeMethod()
	public void preCondtions(ITestResult result) throws Exception {
		
		System.out.println("Am in Before method");
		ctxt = new Contexts(); 
		Contexts.OBJECTGETTER = ctxt;
		// if(envName.equals("Alpha")) {
		baseurl = GetData.fromPropertiesFile(Utility.propFilePath("URL"), "baseURL");
		RestAssured.baseURI = baseurl;
		// }
		ExcelUtility.fromExcelRangeData12=null;
		}

	@AfterMethod()
	public void tearDownAfterMetod(ITestResult result) throws Exception {
		//This code is only useful for end to end case
		baseurl = null;
		extentReports = ExtentUtility.extentReportsInstance("extent-config", "ByteMark", "Platform Automation",
				"Singaravelan");
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentUtility.failLog("TestCase Failed" + result.getName());
			try {
				@SuppressWarnings("rawtypes")
				Class forName = Class.forName(result.getInstanceName());
				Field field;
				field = forName.getField("testCaseName");
				if (field.getType().getName().equals("java.lang.String")) {
					Object newInstance = null;
					try {
						newInstance = forName.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
					testCaseName = field.get(newInstance);
				}
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			ExtentUtility.infoLog("<a href='file:" + Utility.userDirPath + "/Resources/JsonFiles/" + testCaseName
					+ "_Response.json'>Please click here to see the JSON response</a>");
		}

		else if (result.getStatus() == ITestResult.SKIP) {
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentUtility.passLog("TestCase passed" + result.getName());
			try {
				@SuppressWarnings("rawtypes")
				Class forName = Class.forName(result.getInstanceName());
				Field field;
				field = forName.getField("testCaseName");
				if (field.getType().getName().equals("java.lang.String")) {

					Object newInstance = null;
					try {
						newInstance = forName.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
					testCaseName = field.get(newInstance);
				}
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			ExtentUtility.infoLog("<a href='file:" + Utility.userDirPath + "/Resources/JsonFiles/" + testCaseName
					+ "_Response.json'>Please click here to see the JSON response</a>");
		}
		extentReports.endTest(logger);
	}

	@AfterTest
	public void tearDownAfterTest() {
		System.out.println(" Am in after Test");
		extentReports.flush();
	}

	
	@AfterSuite
	public void tearDownAfterSuite() {
		System.out.println("Am in After suite");
	}
}
