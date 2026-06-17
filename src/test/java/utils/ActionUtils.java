package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionUtils {
    WebDriver driver;
    Actions actions;

    public ActionUtils(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void hover(By locator) {
        actions.moveToElement(
                driver.findElement(locator)
        ).perform();
    }
}
