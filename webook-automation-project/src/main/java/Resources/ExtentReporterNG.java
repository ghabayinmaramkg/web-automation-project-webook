package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Result");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Add system information
        extent.setSystemInfo("Tester", "Maram Ghabayin");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Environment", "QA");

        return extent;
    }
}
