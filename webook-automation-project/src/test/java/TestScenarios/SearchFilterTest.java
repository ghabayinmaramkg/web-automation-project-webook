package TestScenarios;

import TestHelpers.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchFilterTest extends BaseTest {
    WebDriver driver;

    @Test
    public void searchAndFilter() {
        driver = getDriver();

        getSearchPageHelper().getSearchBox().sendKeys("Sport");
        getSearchPageHelper().getSearchButton().click();
        // Use Actions class to move the sliders
        Actions actions = new Actions(driver);
        actions.clickAndHold(getSearchPageHelper().getMinSlider()).moveByOffset(50, 0).release().perform();
        actions.clickAndHold(getSearchPageHelper().getMaxSlider()).moveByOffset(-50, 0).release().perform();
        // Verify the results after filtering
        Assert.assertTrue(getSearchPageHelper().elementIsDisplayed(getSearchPageHelper().getFilterResult()) , "element is not present.");
    }
}
