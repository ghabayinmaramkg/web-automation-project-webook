package WeBookTask.PagesHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPageHelper extends AbstractPageHelper {

    WebDriver driver;

    public SearchPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "[data-testid='home_search_input']")
    private WebElement searchBox;

    @FindBy(css = "[data-testid='home_search_submit_button']")
    private WebElement searchButton;

    @FindBy(css = "[aria-label='Minimum']")
    private WebElement minSlider;

    @FindBy(css = "[aria-label='Maximum']")
    private WebElement maxSlider;

    @FindBy(css = "div.mb-6.flex.flex-wrap.gap-2 > span")
    private WebElement filterResult;

    public WebElement getSearchBox() {
        return searchBox;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getMinSlider() {
        return minSlider;
    }

    public WebElement getMaxSlider() {
        return maxSlider;
    }

    public WebElement getFilterResult() {
        return filterResult;
    }
}
