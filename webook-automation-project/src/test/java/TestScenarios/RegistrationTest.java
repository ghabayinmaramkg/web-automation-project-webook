package TestScenarios;

import TestHelpers.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Random;

public class RegistrationTest extends BaseTest {

    @Test(dataProvider = "userData")
    public void registrationFlow(String firstname , String lastname, String password , String phoneNumber) {
       getRegistrationPageHelper().getLoginAndSignUpButton().click();
        String actualTitle = getRegistrationPageHelper().getPageTitle().getText();
        System.out.println("Actual Page Title: " + actualTitle);
        Assert.assertEquals(actualTitle, "Login to webook.com" , "the correct page wasn't displayed correctly");
        getRegistrationPageHelper().getCreateAnAccountButton().click();
        getRegistrationPageHelper().getFirstNameField().sendKeys(firstname);;
        getRegistrationPageHelper().getLastNameField().sendKeys(lastname);;
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        String randomEmail = "newtestemail" + randomNumber + "@gmail.com";
        getRegistrationPageHelper().getEmailField().sendKeys(randomEmail);
        getRegistrationPageHelper().getConfirmEmailField().sendKeys(randomEmail);
        getRegistrationPageHelper().getPasswordField().sendKeys(password);
        getRegistrationPageHelper().getMobileNumberField().sendKeys(phoneNumber);
        getRegistrationPageHelper().getAgreeTermsCheckbox().click();
        getRegistrationPageHelper().getCreateAccountButton().click();
        Assert.assertTrue(getRegistrationPageHelper().elementIsDisplayed(getRegistrationPageHelper().getProfileLogo()) , "the Profile logo in the log in home page wasn't displayed");
    }
}
