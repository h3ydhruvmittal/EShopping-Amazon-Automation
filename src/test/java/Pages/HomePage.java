package Pages;

import constants.Credentials;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void expandAccountsDropdown() {
        actions.moveToElement(
                driver.findElement(HomePageLocators.AccountsDropdown.accountDropdown)
        ).perform();
    }

    public void signIn(String userName, String password){
        expandAccountsDropdown();
        ElementUtils.click(driver,HomePageLocators.AccountsDropdown.signInButton);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginUsername,userName);
        ElementUtils.click(driver,LoginPageLocators.LoginPage.continueButton);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginPassword,password);
        ElementUtils.click(driver,LoginPageLocators.LoginPage.signInButton);
    }

    public void signIn(){
        String userName = Credentials.USERNAME.getValue();
        String password = Credentials.PASSWORD.getValue();
        expandAccountsDropdown();
        ElementUtils.click(driver,HomePageLocators.AccountsDropdown.signInButton);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginUsername,userName);
        ElementUtils.click(driver,LoginPageLocators.LoginPage.continueButton);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginPassword,password);
        ElementUtils.click(driver,LoginPageLocators.LoginPage.signInButton);
    }

    public void signIn(String userName){
        String password = Credentials.PASSWORD.getValue();
        expandAccountsDropdown();
        ElementUtils.click(driver,HomePageLocators.AccountsDropdown.signInButton);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginUsername,userName);
        ElementUtils.click(driver,LoginPageLocators.LoginPage.continueButton);
        ElementUtils.enterText(driver,LoginPageLocators.LoginPage.loginPassword,password);
        ElementUtils.click(driver,LoginPageLocators.LoginPage.signInButton);
    }

    public void signOut(){
        expandAccountsDropdown();
        ElementUtils.click(driver,HomePageLocators.AccountsDropdown.signOutButton);
    }

    public void search(String itemName){
        ElementUtils.enterText(driver,HomePageLocators.SearchBar.searchBarTextBox,itemName);
        ElementUtils.click(driver,HomePageLocators.SearchBar.searchSubmitButton);
    }

    public void search(String category,String itemName){
        ElementUtils.click(driver,HomePageLocators.SearchBar.categoryDropDown);
        ElementUtils.selectByValue(driver,HomePageLocators.SearchBar.categoryDropDown,category);
        ElementUtils.enterText(driver,HomePageLocators.SearchBar.searchBarTextBox,itemName);
        ElementUtils.click(driver,HomePageLocators.SearchBar.searchSubmitButton);
    }
}
