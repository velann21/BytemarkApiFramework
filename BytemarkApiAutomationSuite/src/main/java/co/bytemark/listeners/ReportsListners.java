package co.bytemark.listeners;

import java.lang.reflect.Field;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentTest;

import co.bytemark.config.BaseAdapter;
import co.bytemark.helper.ExtentUtility;

public class ReportsListners extends BaseAdapter implements ITestListener {
	ExtentTest logger;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Am in onTestStart");

		Object testCaseName = null;
		try {
			@SuppressWarnings("rawtypes")
			Class forName = Class.forName(result.getInstanceName());
			System.out.println("InsatnceName: " + result.getInstanceName());
			Field field;
			field = forName.getField("testCaseName");
			System.out.println(field);
			System.out.println(field.get(forName.newInstance()));
			// RegistrationLogin_Scenario01 rg=new RegistrationLogin_Scenario01();
			// System.out.println("filed"+field);
			// System.out.println(field.get(rg));
			// if (field.getType().getName().equals("java.lang.String")) {
			// Object newInstance = forName.newInstance();
			// testCaseName = field.get(newInstance);
			// System.out.println("TestName"+testCaseName);
			// }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger = ExtentUtility.startTest((String) testCaseName);
		ExtentUtility.infoLog("TestStarted");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Am in OnTestSucces");
	}

	@Override
	public void onTestFailure(ITestResult result) {

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentUtility.skipLog("TestCase Skipped" + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Am in onStart");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Am in onTestFinish");

	}

}
