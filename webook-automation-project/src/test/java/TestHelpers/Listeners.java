package TestHelpers;

import Resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Listeners extends BaseTest implements ITestListener {
    private ExtentTest test;
    private ExtentReports extent = ExtentReporterNG.getReportObject();
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = BaseTest.getDriver();

        if (driver != null) {
            String methodName = result.getMethod().getMethodName();
            try {
                String screenshotPath = captureScreenshot(driver, methodName);
                test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");

                // Set the screenshotPath attribute in the ITestResult object
                result.setAttribute("screenshotPath", screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("WebDriver is null. Cannot take a screenshot.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Implement this method if needed.
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Implement this method if needed.
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        cleanupExtentReport();
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String captureScreenshot(WebDriver driver, String methodName) throws IOException {
        // Take screenshot using Selenium WebDriver
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + methodName + ".png";
        Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots/"));
        Files.copy(srcFile.toPath(), Paths.get(screenshotPath));
        return screenshotPath;
    }
}
