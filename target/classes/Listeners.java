package resources;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import test.AppiumFramework.AppTest;
import test.AppiumFramework.ExtentReporterNG;

public class Listeners implements ITestListener {
	private ExtentReports extent = ExtentReporterNG.extentReportGenerator();
	private ExtentTest test;
	private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //note: useful only for when running in parallel, no need to use for sequential

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "PASSED!");
	}

	@Override
	public void onTestFailure(ITestResult result)	{
		String name = result.getName();
		String scrpath = AppTest.getScreenCapture(name);
		extentTest.get().log(Status.FAIL, "FAILED!");
		extentTest.get().fail(result.getThrowable());
		extentTest.get().addScreenCaptureFromPath(scrpath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, "SKIPPED!");
		extentTest.get().skip(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}