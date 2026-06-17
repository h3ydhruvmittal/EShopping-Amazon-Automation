package locators;

import org.openqa.selenium.By;

public class ProductPageLocators {
    private ProductPageLocators() {};

    public static class ProductMenuPage{
        public static By searchProductUsingASIN = By.xpath("//div[@data-component-type='s-search-result' and not (contains(@class,'AdHolder'))]");
    }

    public static class ProductPage{
        public static By productTile = By.id("productTitle");
        public static By productPrice = By.xpath("//span[contains(@class,'priceToPay')]");
        public static By productRating = By.xpath("//span[contains(@class,'reviewCountTextLinkedHistogram')]//a/span");
    }
    public static By productByASIN(String asin) {
        return By.xpath(String.format("//div[@data-component-type='s-search-result' and @data-asin='%s']", asin));
    }

}
