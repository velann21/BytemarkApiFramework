package co.bytemark.helper;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

  public class ExtentUtility {
	public static ExtentReports extentReports;
	public static ExtentTest logger;
	public static ExtentUtility ex;

	private ExtentUtility() {

	}
	public static ExtentUtility extentUtilityInstance() {
		if (ex == null) {
			ex = new ExtentUtility();
		}
		return ex;
	}

	public static ExtentReports extentReportsInstance(String configFile, String hostName, String envName,
			String author) {
		if(extentReports==null) {
		extentReports = new ExtentReports(Utility.userDirPath + "/test-output/extent.html", true);
		extentReports.addSystemInfo("HostName", hostName).addSystemInfo("Environment", envName).addSystemInfo("Author",author);
		extentReports.loadConfig(new File(Utility.XMLFilepathExtracter(configFile)));
		return extentReports;
		}
		return extentReports;
	}

	public static ExtentTest startTest(String testName) {
			logger = extentReports.startTest(testName);
		return logger;
	}
	
	public static void passLog(String passInfo) {
		logger.log(LogStatus.PASS, passInfo);
	}
	
	public static void failLog(String failLog) {
		logger.log(LogStatus.FAIL,failLog);
	}
	
	public static void infoLog(String info) {
		logger.log(LogStatus.INFO, info);
	}
	
	public static void skipLog(String skipInfo) {
		logger.log(LogStatus.SKIP,skipInfo);
	}

}
