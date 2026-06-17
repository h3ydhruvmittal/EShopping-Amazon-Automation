package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ElementUtils {

    public static void click(WebDriver driver, By locator) {
        driver.findElement(locator).click();
    }

    public static List<WebElement> enterText(WebDriver driver, By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        return null;
    }

    public static String getText(WebDriver driver, By locator) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getText();
    }

    public static boolean isDisplayed(WebDriver driver, By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public static String getTextFromTextBox(WebDriver driver, By locator) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getAttribute("value");
    }

    public static WebElement getVisibleElement(WebDriver driver, By locator) {

        return driver.findElements(locator)
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No visible element found: " + locator));
    }

}
