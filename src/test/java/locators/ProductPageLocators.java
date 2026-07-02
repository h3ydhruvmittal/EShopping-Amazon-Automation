package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductPageLocators {
    private ProductPageLocators() {};

    public static class ProductMenuPage{
        public static By productCardList = By.xpath("//div[@data-component-type='s-search-result' and @data-asin!='' and not(.//*[contains(text(),'Sponsored')])]");
        public static By sortByDropDown = By.id("s-result-sort-select");
        public static By previousPageButtonInactive = By.xpath("//span[contains(@class,'s-pagination-previous')]");
        public static By previousPageButtonActive = By.xpath("//a[contains(@class,'s-pagination-previous')]");
        public static By switchToPageNumber(int pageNumber){
            return By.xpath(String.format("//a[@aria-label='Go to page %d']",pageNumber));
        }
        public static By currentPage(int pageNumber){
            return By.xpath(String.format("//span[@aria-label='Page %d']",pageNumber));
        }
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
    public static class ProductFilter{
        public static By filterProductByBrand(String brand){
            return By.xpath(String.format("//span[normalize-space()='%s']/preceding-sibling::div//i[contains(@class,'a-icon-checkbox')]",brand));
        }
        public static By minimumPrice = By.xpath("//input[@aria-label='Minimum price']");
        public static By maximumPrice = By.xpath("//input[@aria-label='Maximum price']");
    }

}
