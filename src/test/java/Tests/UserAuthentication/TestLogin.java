package Tests.UserAuthentication;

import Pages.HomePage;
import constants.Credentials;
import implementations.AppImpl;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ElementUtils;
import utils.TestInfo;

public class TestLogin{
    public static AppImpl app;
    public static HomePage homePage;
    public static Actions actions;

    @Test
    @TestInfo(testCaseName = "Verify login with valid credentials", testCaseId = "AMZ_TC_001", priority = "High")
    public static void testLoginWithValidCredentials() throws InterruptedException {
        app = new AppImpl();
        homePage = new HomePage(app.getDriver());
        app.open();
        String accountSignIn = ElementUtils.getText(app.getDriver(), HomePageLocators.AccountsDropdown.accountUsername);
        Assert.assertEquals(accountSignIn, "Hello, sign in");
        homePage.signIn();
        String accountUsername = ElementUtils.getText(app.getDriver(), HomePageLocators.AccountsDropdown.accountUsername);
        Assert.assertEquals(accountUsername, "Hello, Dhruv");
        app.close();
    }

    @Test
    @TestInfo(testCaseName = "Verify login with invalid password", testCaseId = "AMZ_TC_002", priority = "High")
    public static void testLoginWithInValidPassword() throws InterruptedException {
        app = new AppImpl();
        homePage = new HomePage(app.getDriver());
        app.open();
        homePage.signIn(Credentials.USERNAME.getValue(),"invalidPass");
        String errorHeading = ElementUtils.getText(app.getDriver(), LoginPageLocators.LoginPage.invalidPassErrorHeading);
        String errorMessage = ElementUtils.getText(app.getDriver(), LoginPageLocators.LoginPage.invalidPassErrorMsg);
        Assert.assertEquals(errorHeading, "There was a problem");
        Assert.assertEquals(errorMessage, "Your password is incorrect");
        app.close();
    }

    @Test
    @TestInfo(testCaseName = "Verify login with missing Username", testCaseId = "AMZ_TC_003", priority = "High")
    public static void testLoginWithMissingUsername() throws InterruptedException {
        app = new AppImpl();
        homePage = new HomePage(app.getDriver());
        WebDriver driver = app.getDriver();

        app.open();
        homePage.expandAccountsDropdown();

        ElementUtils.click(driver,HomePageLocators.AccountsDropdown.signInButton);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginUsername,"");
        ElementUtils.click(driver,LoginPageLocators.LoginPage.continueButton);

        String errorAlert = ElementUtils.getText(app.getDriver(), LoginPageLocators.LoginPage.loginErrorAlert);
        Assert.assertEquals(errorAlert, "Enter your mobile number or email");
        app.close();
    }

    @Test
    @TestInfo(testCaseName = "Verify error with with Missing Password", testCaseId = "AMZ_TC_004", priority = "High")
    public static void testLoginWithMissingPassword() throws InterruptedException {
        app = new AppImpl();
        homePage = new HomePage(app.getDriver());
        app.open();
        String accountSignIn = ElementUtils.getText(app.getDriver(), HomePageLocators.AccountsDropdown.accountUsername);
        Assert.assertEquals(accountSignIn, "Hello, sign in");
        homePage.signIn("h3ydhruv@gmail.com","");
        String errorAlert = ElementUtils.getText(app.getDriver(), LoginPageLocators.LoginPage.loginErrorAlert);
        Assert.assertEquals(errorAlert, "Enter your password");
        app.close();
    }

    @Test
    @TestInfo(testCaseName = "Verify logout", testCaseId = "AMZ_TC_009")
    public static void testSignOut() throws InterruptedException {
        app = new AppImpl();
        homePage = new HomePage(app.getDriver());
        app.open();
        homePage.signIn();
        String accountUsername = ElementUtils.getText(app.getDriver(), HomePageLocators.AccountsDropdown.accountUsername);
        Assert.assertEquals(accountUsername, "Hello, Dhruv");
        homePage.signOut();
        String signInTitle = app.getDriver().getTitle();
        Assert.assertEquals(signInTitle, "Amazon Sign-In");
        app.close();
    }

    @Test
    @TestInfo(testCaseName = "Verify SignIn With Enter Key", testCaseId = "AMZ_TC_008")
    public static void testSignInWithEnterKey() throws InterruptedException {
        app = new AppImpl();
        homePage = new HomePage(app.getDriver());
        app.open();
        WebDriver driver = app.getDriver();
        actions = new Actions(driver);

        String accountSignIn = ElementUtils.getText(app.getDriver(), HomePageLocators.AccountsDropdown.accountUsername);
        Assert.assertEquals(accountSignIn, "Hello, sign in");

        homePage.expandAccountsDropdown();
        ElementUtils.click(driver,HomePageLocators.AccountsDropdown.signInButton);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginUsername,Credentials.USERNAME.getValue());
        actions.sendKeys(Keys.ENTER).perform();

        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginPassword,Credentials.PASSWORD.getValue());
        actions.sendKeys(Keys.ENTER).perform();

        String accountUsername = ElementUtils.getText(app.getDriver(), HomePageLocators.AccountsDropdown.accountUsername);
        Assert.assertEquals(accountUsername, "Hello, Dhruv");

        app.close();
    }
}
