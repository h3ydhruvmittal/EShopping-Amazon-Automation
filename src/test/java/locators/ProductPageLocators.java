package locators;

import org.openqa.selenium.By;

public class ProductPageLocators {
    private ProductPageLocators() {};

    public static class ProductMenuPage{
        public static By productCardList = By.xpath("//div[@data-component-type='s-search-result' and @data-asin!='' and not(.//*[contains(text(),'Sponsored')])]");
    }

    public static class ProductPageCard{
        public static By productTitle = By.xpath(".//div[@data-cy='title-recipe']/a");
        public static By productPrice = By.xpath(".//div[@data-cy='price-recipe']//span[contains(@class,'a-offscreen')]");
        public static By productRating = By.xpath(".//div[@data-cy='reviews-block'][1]/div/span[1]");
    }
    public static class ProductPage{
        public static By productTitle = By.id("productTitle");
        public static By productPrice = By.xpath("//span[contains(@class,'priceToPay')]");
        public static By productRating = By.xpath("//span[contains(@class,'reviewCountTextLinkedHistogram')]//a/span");
    }
    public static By productByASIN(String asin) {
        return By.xpath(String.format("//div[@data-component-type='s-search-result' and @data-asin='%s']", asin));
    }

}
