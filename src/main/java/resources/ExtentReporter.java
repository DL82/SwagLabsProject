package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter extends Base{
	
	//static DateTime date;
	static ExtentReports extent;
	public static ExtentReports extentReportsGenerator() {
		String path = System.getProperty("user.dir") + "/reports/HTML/extent_" + getDateTime() + ".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("SWAGLABS ExtentReports");
		reporter.config().setReportName("My Automation Tests");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Daniel Landman");
		return extent;
	}
}
