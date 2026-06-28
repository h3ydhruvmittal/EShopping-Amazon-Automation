package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    public static void selectByVisibleText(WebDriver driver, By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    public static void selectByValue(WebDriver driver, By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    public static void setRangeValue(WebDriver driver, By locator, int value) {
        WebElement slider = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                slider,
                value);

    }

}
