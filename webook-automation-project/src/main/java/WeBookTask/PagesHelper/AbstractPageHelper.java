package WeBookTask.PagesHelper;

import SharedElements.SharedElementsHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPageHelper extends SharedElementsHelper {

    WebDriver driver;

    public AbstractPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



}
