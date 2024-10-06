package TestHelpers;

import WeBookTask.PagesHelper.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BaseTest {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private SearchPageHelper WeBookSearchPageHelper;
    private RegistrationPageHelper WeBookregistrationPageHelper;

    private static final String GLOBAL_DATA_PATH = "/src/main/java/Resources/GlobalData.properties";
    private static final String EXCEL_FILE_PATH = "/src/main/java/Resources/WeBookTestData.xlsx";
    private static final String REPORTS_PATH = "/reports/";
    private static final String SCREENSHOTS_PATH = "/screenshots/";

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) throws IOException {
        setDriver(initializeDriver());
       getDriver().get("https://webook.com/en");

    } //header_nav_login_button

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getMethod().getMethodName());
        }

        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    private WebDriver initializeDriver() throws IOException {
        Properties prop = loadProperties();
        WebDriverManager.chromedriver().setup();
        setDriver(new ChromeDriver());
        configureDriver();
        return getDriver();
    }

    private Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + GLOBAL_DATA_PATH);
        prop.load(fis);
        return prop;
    }


    private void configureDriver() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        getDriver().manage().window().maximize();

        WeBookSearchPageHelper = new SearchPageHelper(getDriver());
        WeBookregistrationPageHelper = new RegistrationPageHelper(getDriver());
    }

    @BeforeSuite
    public void cleanUp() {
        cleanupExtentReport();
    }

    public void cleanupExtentReport() {
        File reportsDirectory = new File(System.getProperty("user.dir") + REPORTS_PATH);
        if (reportsDirectory.exists() && deleteDirectory(reportsDirectory)) {
            System.out.println("Reports directory and its contents deleted successfully.");
        } else {
            System.err.println("Failed to delete the reports directory or directory does not exist.");
        }
    }

    private boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        if (!file.delete()) {
                            return false;
                        }
                    }
                }
            }
        }
        return directory.delete();
    }

    public String captureScreenshot(String testCaseName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String screenshotFileName = testCaseName + "_" + timestamp + ".png";
            String screenshotPath = System.getProperty("user.dir") + REPORTS_PATH + SCREENSHOTS_PATH + screenshotFileName;
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot captured: " + destination.getAbsolutePath());
            return screenshotPath;
        } catch (IOException e) {
            System.out.println("Error taking screenshot: " + e.getMessage());
            return null;
        }
    }
    @DataProvider(name = "userData")
    public Iterator<Object[]> userDataProvider() {
        List<Object[]> data = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(System.getProperty("user.dir") + EXCEL_FILE_PATH)) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (int rowNumber = 1; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
                Row row = sheet.getRow(rowNumber);
                String firstname = row.getCell(0).getStringCellValue();
                String lastname = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();
                double phoneNumber = row.getCell(3).getNumericCellValue();
                String phoneNumberString = String.valueOf(phoneNumber);
                data.add(new Object[]{firstname, lastname, password, phoneNumberString});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.iterator();
    }

    public SearchPageHelper getSearchPageHelper() {
        return WeBookSearchPageHelper;
    }

    public RegistrationPageHelper getRegistrationPageHelper() {
        return WeBookregistrationPageHelper;
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }
}
