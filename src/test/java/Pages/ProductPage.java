package Pages;

import locators.ProductPageLocators;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;
import utils.WindowUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    public void openProductByASIN(WebDriver driver, String asin) {
        String currentWindow = driver.getWindowHandle();
        ElementUtils.click(driver, ProductPageLocators.productByASIN(asin));
        if (driver.getWindowHandles().size() > 1) {
            WindowUtils.switchToNewTab(driver, currentWindow);
        }
    }

    public List<Product> getAllProducts() {
        List<WebElement> productCards = driver.findElements(ProductPageLocators.ProductMenuPage.productCardList);
        System.out.println(productCards);
        List<Product> products = new ArrayList<>();
        for (WebElement card : productCards) {
            Product product = new Product();
            product.setAsin(card.getAttribute("data-asin"));
            product.setTitle(
                    card.findElement(ProductPageLocators.ProductPageCard.productTitle).getText());
            product.setPrice(
                    card.findElement(ProductPageLocators.ProductPageCard.productPrice).getAttribute("innerText"));
            product.setRating(
                    card.findElement(ProductPageLocators.ProductPageCard.productRating).getText());
            products.add(product);
        }
        return products;
    }

//    public Product productCard(WebDriver driver){
//
//    }

}
