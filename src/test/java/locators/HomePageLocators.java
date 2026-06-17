package locators;

import org.openqa.selenium.By;

public class HomePageLocators {
    private HomePageLocators() {}

    public static class AccountsDropdown{
        public static By accountUsername = By.id("nav-link-accountList-nav-line-1");
        public static By accountDropdown = By.xpath("//*[@id='nav-link-accountList']/button");
        public static By signInButton = By.xpath("//span[text()='Sign in']");
        public static By signOutButton = By.xpath("//span[text()='Sign Out']");
        public static By signUpButton = By.xpath("//a[text()='Start here.']");
    }
    public static class SearchBar{
        public static By searchBarTextBox = By.id("twotabsearchtextbox");
        public static By searchSubmitButton = By.id("nav-search-submit-button");
        public static By autocompleteSuggestion = By.cssSelector("div[role='row'][id^='sac-suggestion-row-']");
    }
}
