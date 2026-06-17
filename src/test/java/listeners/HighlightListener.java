package listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightListener implements WebDriverListener {

    private WebElement previousElement;

    private void highlight(WebElement element) {

        try {
            WebDriver driver = ((WrapsDriver) element).getWrappedDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Remove highlight from previous element
            if (previousElement != null) {
                try {
                    js.executeScript(
                            "arguments[0].style.border=''",
                            previousElement);
                } catch (Exception ignored) {
                }
            }

            // Highlight current element
            js.executeScript(
                    "arguments[0].style.border='3px solid red'",
                    element);

            previousElement = element;

        } catch (Exception e) {
            System.out.println("Unable to highlight element: " + e.getMessage());
        }
    }

    @Override
    public void beforeClick(WebElement element) {
        highlight(element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        highlight(element);
    }

    @Override
    public void beforeGetText(WebElement element) {
        highlight(element);
    }

    @Override
    public void beforeIsDisplayed(WebElement element) {
        highlight(element);
    }
}