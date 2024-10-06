package WeBookTask.PagesHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPageHelper extends AbstractPageHelper {

    WebDriver driver;

    public RegistrationPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-testid='header_nav_login_button']")
    private WebElement loginAndSignUpButton;

    @FindBy(css = ".text-2xl")
    private WebElement pageTitle;

    @FindBy(css = "[data-testid='auth_signup_button']")
    private WebElement createAnAccountButton;

    @FindBy(id = "first_name")
    private WebElement firstNameField;

    @FindBy(id = "last_name")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "confirm_email")
    private WebElement confirmEmailField;

    @FindBy(css = "[data-testid='auth_signup_password_input']")
    private WebElement passwordField;

    @FindBy(css = "[data-testid='auth_mobile_number_input']")
    private WebElement mobileNumberField;

    @FindBy(css = "[data-testid='auth_terms_checkbox']")
    private WebElement agreeTermsCheckbox;

    @FindBy(css = "[data-testid='auth_signup_submit_button']")
    private WebElement createAccountButton;

    @FindBy(css = ".text-sm > button > div")
    private WebElement profileLogo;

    public WebElement getLoginAndSignUpButton() {
        waitForElementToAppearWithFindElement(loginAndSignUpButton);
        return loginAndSignUpButton;
    }

    public WebElement getPageTitle() {
        waitForElementToAppearWithFindElement(pageTitle);
        return pageTitle;
    }

    public WebElement getCreateAnAccountButton() {
        waitForElementToAppearWithFindElement(createAnAccountButton);
        return createAnAccountButton;
    }

    public WebElement getFirstNameField() {
        waitForElementToAppearWithFindElement(firstNameField);
        return firstNameField;
    }

    public WebElement getLastNameField() {
        waitForElementToAppearWithFindElement(lastNameField);
        return lastNameField;
    }

    public WebElement getEmailField() {
        waitForElementToAppearWithFindElement(emailField);
        return emailField;
    }

    public WebElement getConfirmEmailField() {
        waitForElementToAppearWithFindElement(confirmEmailField);
        return confirmEmailField;
    }

    public WebElement getPasswordField() {
        waitForElementToAppearWithFindElement(passwordField);
        return passwordField;
    }

    public WebElement getMobileNumberField() {
        waitForElementToAppearWithFindElement(mobileNumberField);
        return mobileNumberField;
    }

    public WebElement getAgreeTermsCheckbox() {
        waitForElementToAppearWithFindElement(agreeTermsCheckbox);
        return agreeTermsCheckbox;
    }

    public WebElement getCreateAccountButton() {
        waitForElementToAppearWithFindElement(createAccountButton);
        return createAccountButton;
    }

    public WebElement getProfileLogo() {
        waitForElementToAppearWithFindElement(profileLogo);
        return profileLogo;
    }
}