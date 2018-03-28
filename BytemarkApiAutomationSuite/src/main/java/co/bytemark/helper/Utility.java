package co.bytemark.helper;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Utility {
	public static String userDirPath = System.getProperty("user.dir");

	public static String propFilePath(String fileName) {
		String FilePath = null;
		switch (fileName) {
		case "config":
			FilePath = userDirPath + "/Resources/external_data/config.properties";
			break;
		case "URL":
			FilePath = userDirPath + "/Resources/URI/URL.properties";
			break;
		case "Exception":
			FilePath = userDirPath + "/Resources/ExceptionProperties/Exception.properties"; 
		}
		return FilePath;

	}

	public static String jsonFilePathExtracter(String jsonFileName) {
		String jsonFilePath = null;
		String fromPropertiesFile = GetData.fromPropertiesFile(Utility.propFilePath("config"), "jsonFilePath");
		try {
			jsonFilePath = Utility.userDirPath + fromPropertiesFile + jsonFileName;
		} catch (Exception e) {
			File file = new File(jsonFilePath);
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return jsonFilePath;
	}

	public static String XMLFilepathExtracter(String fileName) {
		String FilePath = null;
		switch (fileName) {
		case "extent-config":
			FilePath = userDirPath + "/Resources/ExtentConfigFiles/extent-config.xml";
			break;
		case "pom":
			FilePath = userDirPath + "/pom.xml";
			break;
		}
		return FilePath;

	}

	public static String excelFilePathExtracter(String fileName) {
		String FilePath = null;
		switch (fileName) {
		case "TestData":
			FilePath = userDirPath + "/Resources/external_data/data/TestData.xlsx";
			break;
		}
		return FilePath;
	}

	public static int showRandomInteger(int aStart, int aEnd, Random aRandom){
	    if (aStart > aEnd) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    return randomNumber;
	}
	
	public static void main(String[] args) {
		mailIdUpdater("singaravelan+testacc12@bytemark.co");
	}
	
	public static String  mailIdUpdater(String actual) {
		String[] splitMailId = actual.split("@");
		String employeeNameString= splitMailId[0];
		String numberExtracter = employeeNameString.substring(employeeNameString.length()-2, employeeNameString.length());
		String testNumberReplace = numberExtracter.replace(numberExtracter, ""+Utility.showRandomInteger(1, 100, new Random()));
		String updatedMailId=splitMailId[0].substring(0, splitMailId[0].length()-2)+testNumberReplace+"@"+splitMailId[1];
		return updatedMailId;
	}
}
