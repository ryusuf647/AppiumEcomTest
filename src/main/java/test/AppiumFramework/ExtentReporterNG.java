package test.AppiumFramework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {	
	public static ExtentReports extent;
	private static ExtentSparkReporter reporter;
	private static String path;
	private static String tester = "Automation Tester 1";
	
	public static ExtentReports extentReportGenerator()	{
		path = System.getProperty("user.dir") + "\\test-output\\testreports\\test_report.html";
		reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Android Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", tester);
		return extent;
	}
}