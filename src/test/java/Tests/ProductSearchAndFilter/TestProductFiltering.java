package Tests.ProductSearchAndFilter;

import Pages.HomePage;
import Pages.ProductPage;
import constants.Categories;
import implementations.AppImpl;
import locators.HomePageLocators;
import locators.ProductPageLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import utils.ElementUtils;
import utils.TestInfo;

public class TestProductFiltering {
    public static AppImpl app;
    public static HomePage homePage;
    public static ProductPage productPage;

    @Test
    @TestInfo(testCaseId = "AMZ_020", testCaseName = "Verify filtering search results by Brand.")
    public static void testFilterProductByBrand(@Optional("Shoes") String category, @Optional("Nike") String brand) throws InterruptedException {
        app = new AppImpl();
        WebDriver driver = app.getDriver();
        homePage = new HomePage(driver);

        app.open();
        homePage.search(category);
        ElementUtils.click(driver,ProductPageLocators.ProductFilter.filterProductByBrand(brand));
        app.close();
    }

    @Test
    @TestInfo(testCaseId = "AMZ_021", testCaseName = "Verify filtering by price range.")
    public static void testFilterProductByPriceRange(@Optional("Watches") String itemName) throws InterruptedException {
        app = new AppImpl();
        WebDriver driver = app.getDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);

        app.open();
        homePage.search(itemName);
        //productPage.setMinimumPrice(1050);
        productPage.setMaximumPrice(25299);

        app.close();
    }
}
