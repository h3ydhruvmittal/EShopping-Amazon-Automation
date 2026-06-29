package Tests.ProductSearchAndFilter;

import Pages.HomePage;
import Pages.ProductPage;
import implementations.AppImpl;
import locators.ProductPageLocators;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import utils.ElementUtils;
import utils.TestInfo;

import java.util.ArrayList;
import java.util.List;

public class Sorting {
    public static AppImpl app;
    public static HomePage homePage;
    public static ProductPage productPage;

    @Test
    @TestInfo(testCaseId = "AMZ_023", testCaseName = "Verify sorting search results by 'Price: Low to High'.")
    public static void testSortingLowToHigh(@Optional("toys") String category) throws InterruptedException {
        app = new AppImpl();
        WebDriver driver = app.getDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);

        app.open();
        homePage.search(category);
        Select select = new Select(driver.findElement(ProductPageLocators.ProductMenuPage.sortByDropDown));
        select.selectByVisibleText("Price: Low to High");
        Thread.sleep(3000);
        List<Product> products = productPage.getAllProducts();
        double previousProductPrice = productPage.parsePrice(products.getFirst().getPrice());
        List<Double> priceList = new ArrayList<>();
        for(Product product : products){
            double productPrice = productPage.parsePrice(product.getPrice());
            priceList.add(productPrice);
            Assert.assertTrue(
                    previousProductPrice <= productPrice,
                    String.format(
                            "Products are not sorted in ascending order. Previous price: ₹%.2f, Current price: ₹%.2f",
                            previousProductPrice,
                            productPrice));
            previousProductPrice = productPrice;
        }
        app.close();
    }
}
