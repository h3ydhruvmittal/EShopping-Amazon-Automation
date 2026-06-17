package Tests.ProductSearchAndFilter;

import Pages.HomePage;
import Pages.ProductPage;
import api.AmazonProductApi;
import implementations.AppImpl;
import locators.HomePageLocators;
import locators.ProductPageLocators;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import utils.ElementUtils;
import utils.TestInfo;
import utils.WaitUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class SearchBar {
    public static AppImpl app;
    public static HomePage homePage;
    public static Actions actions;

    @Test
    @TestInfo(testCaseId = "AMZ_016", testCaseName = "Verify simple keyword search returns relevant products.")
    public static void testProductSearch() throws InterruptedException {
        app = new AppImpl();
        WebDriver driver = app.getDriver();
        homePage = new HomePage(driver);

        app.open();
        homePage.search("Laptop");
        String title = driver.getTitle();
        Assert.assertEquals(title, "Amazon.in : Laptop");
        app.close();
    }

    @Test
    @TestInfo(testCaseId = "AMZ_017", testCaseName = "Verify autocomplete/suggestions list appears while typing.")
    public static void testAutoCompleteListOnProductSearch() throws InterruptedException {
        app = new AppImpl();
        WebDriver driver = app.getDriver();
        homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        app.open();
        String inputText = "iph";
        ElementUtils.enterText(driver, HomePageLocators.SearchBar.searchBarTextBox, inputText);
        wait.until(driverTemp -> !driverTemp.findElements(HomePageLocators.SearchBar.autocompleteSuggestion).isEmpty());
        List<WebElement> suggestions = driver.findElements(HomePageLocators.SearchBar.autocompleteSuggestion);
        System.out.println("Suggestions count: " + suggestions.size());

        for (WebElement suggestion : suggestions) {
            Assert.assertTrue(suggestion.getText().startsWith(inputText));
            System.out.println(suggestion.getText());
        }
        Assert.assertFalse(suggestions.isEmpty(),
                "Suggestions are not displayed");

        app.close();
    }

    @Test
    @TestInfo(testCaseId = "AMZ_018", testCaseName = "Verify search works correctly with item numbers/ASINs.")
    public static void testProductSearchUsingASIN(@Optional("B0GR1RQ144") String asin) throws InterruptedException {
        app = new AppImpl();
        WebDriver driver = app.getDriver();
        homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ProductPage productPage = new ProductPage(driver);

        app.open();
        homePage.search(asin);

        //ElementUtils.click(driver,ProductPageLocators.ProductMenuPage.searchProductUsingASIN);
        productPage.openProductByASIN(driver,asin);
        wait.until(driverTemp -> !driverTemp.findElements(ProductPageLocators.ProductPage.productRating).isEmpty());

        String rating = ElementUtils.getVisibleElement(driver,ProductPageLocators.ProductPage.productRating).getText();
        String price = ElementUtils.getVisibleElement(driver,ProductPageLocators.ProductPage.productPrice).getText();
        String title = ElementUtils.getVisibleElement(driver,ProductPageLocators.ProductPage.productTile).getText();


        AmazonProductApi amazonProductApi =new AmazonProductApi();
        Product product = amazonProductApi.getProduct(asin);

        Assert.assertEquals(title,product.getTitle());
        Assert.assertEquals(price, product.getPrice());
        Assert.assertEquals(rating, product.getRating());

        app.close();
    }

}
