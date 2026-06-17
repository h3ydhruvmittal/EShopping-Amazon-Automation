package Pages;

import locators.ProductPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;
import utils.WindowUtils;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public static void getProductDetails(){

    }

    public ProductPage openProductByASIN(WebDriver driver,String asin) {
        String currentWindow = driver.getWindowHandle();
        ElementUtils.click(driver, ProductPageLocators.productByASIN(asin));
        if (driver.getWindowHandles().size() > 1) {
            WindowUtils.switchToNewTab(driver, currentWindow);
        }
        return this;

    }

}
