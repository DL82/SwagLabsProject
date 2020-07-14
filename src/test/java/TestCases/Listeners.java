package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import resources.ExtentReporter;
import resources.TakeScreenshot;

public class Listeners extends Base implements ITestListener {

	WebDriver driver;
	ExtentReports extent = ExtentReporter.extentReportsGenerator();
	ExtentTest test;
	TakeScreenshot ss = new TakeScreenshot();

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		test.log(Status.FAIL, result.getThrowable());
		
		String name = result.getMethod().getMethodName();
			try {
				ss.takeSS(name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		test.log(Status.SKIP, "skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("~~~ Session Started ~~~");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("~~~ Session Ended ~~~");
		extent.flush();
	}

}
