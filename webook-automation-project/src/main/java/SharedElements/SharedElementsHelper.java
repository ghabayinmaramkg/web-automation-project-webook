package SharedElements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SharedElementsHelper {

    WebDriver driver;

    public SharedElementsHelper(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void waitForElementToAppearWithFindElement(WebElement Locator) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
        w.until(ExpectedConditions.visibilityOf(Locator));
    }

    public void waitUntilElementTextAppears(WebElement Locator , String searchedText){
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.textToBePresentInElement(Locator , searchedText));
    }

    public void waitForElementToBeClickable(WebElement Locator) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.elementToBeClickable(Locator));
    }

    public void waitForElementToDisappear(WebElement Locator) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.invisibilityOf(Locator));
    }

    public void doubleClickElement(WebElement Locator) {
        Actions a = new Actions(driver);
        a.doubleClick(Locator).build().perform();
    }

    public void scrollBy(int pixelsToScroll) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0]);", pixelsToScroll);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean elementIsDisplayed(WebElement webElement){

        return webElement.isDisplayed();
    }

}
