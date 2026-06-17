package Tests.UserAuthentication;

import Pages.HomePage;
import constants.Credentials;
import implementations.AppImpl;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ElementUtils;
import utils.GmailReader;
import utils.TestInfo;

public class TestRegistration {
    public static AppImpl app;
    public static HomePage homePage;
    public static Actions actions;

    @Test
    @TestInfo(testCaseId = "AMZ_001", testCaseName = "Verify user registration with valid details")
    public static void VerifyPasswordRestOtpIsSentSuccessfully() throws Exception {
        GmailReader gmailReader = new GmailReader();
        app = new AppImpl();
        homePage = new HomePage(app.getDriver());
        WebDriver driver = app.getDriver();

        app.open();
        homePage.expandAccountsDropdown();
        ElementUtils.click(driver,HomePageLocators.AccountsDropdown.signUpButton);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginUsername, Credentials.USERNAME.getValue());
        ElementUtils.click(driver,LoginPageLocators.LoginPage.continueButton);
        ElementUtils.click(driver,LoginPageLocators.LoginPage.forgotPassword);
        Assert.assertEquals(ElementUtils.getTextFromTextBox(driver,LoginPageLocators.LoginPage.forgotPasswordUsername),Credentials.USERNAME.getValue());
        ElementUtils.click(driver,LoginPageLocators.LoginPage.continueButton);

        Thread.sleep(3000);
        String expectedSubject = "amazon.in: Password recovery";
        String otp = gmailReader.getLatestOtp(expectedSubject);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.otpTextBox,otp);
        ElementUtils.click(driver,LoginPageLocators.LoginPage.submitCodeButton);
        Assert.assertEquals(ElementUtils.getText(driver,LoginPageLocators.RegistrationPage.thankYouHeader),"Thank you");
        app.close();
    }
}
