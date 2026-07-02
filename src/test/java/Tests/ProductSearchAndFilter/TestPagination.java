package Tests.ProductSearchAndFilter;

import Pages.HomePage;
import Pages.ProductPage;
import implementations.AppImpl;
import locators.ProductPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import utils.ElementUtils;
import utils.TestInfo;
import utils.WaitUtils;

import java.time.Duration;

import static locators.ProductPageLocators.ProductMenuPage.currentPage;
import static locators.ProductPageLocators.ProductMenuPage.switchToPageNumber;

public class TestPagination {
    public static AppImpl app;
    public static HomePage homePage;
    public static ProductPage productPage;

    @Test
    @TestInfo(testCaseId = "AMZ_026", testCaseName = "Verify navigation to the next page of search results.")
    public static void testPaginationPreviousButton(@Optional("Books") String itemName) throws InterruptedException {
        app = new AppImpl();
        WebDriver driver = app.getDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);

        app.open();
        homePage.search(itemName);

        int pageNumber = 2;

        WebElement previousButtonInactive = driver.findElement(ProductPageLocators.ProductMenuPage.previousPageButtonInactive);
        Assert.assertEquals(previousButtonInactive.getAttribute("aria-disabled"),"true");
        ElementUtils.click(driver, switchToPageNumber(pageNumber));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(currentPage(2), "class", "s-pagination-selected"));

        WebElement previousButtonActive = driver.findElement(ProductPageLocators.ProductMenuPage.previousPageButtonActive);
        Assert.assertTrue(previousButtonActive.getAttribute("aria-label").contains(String.format("Go to previous page, page %d",pageNumber-1)));

        app.close();
    }
}
