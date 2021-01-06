package MyFramework;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.DriverUtility;
import Resources.ExtentReportsNG;

public class Listeners implements ITestListener{
ExtentReports extent=ExtentReportsNG.publishReport();
DriverUtility d=new DriverUtility();
ExtentTest test;
ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentTest.get().log(Status.PASS, "Test Passed");
		String testCaseName=result.getMethod().getMethodName();
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			extentTest.get().addScreenCaptureFromPath(d.getScreenshot(testCaseName,driver),testCaseName);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		String testCaseName=result.getMethod().getMethodName();
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			extentTest.get().addScreenCaptureFromPath(d.getScreenshot(testCaseName,driver),testCaseName);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
