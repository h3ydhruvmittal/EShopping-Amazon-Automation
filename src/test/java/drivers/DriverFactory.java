package drivers;

import listeners.HighlightListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverFactory {

    public static ChromeDriver chromeDriver ;

    public static WebDriver initializeDriver() {

        String mode = System.getProperty("execution.mode");
        System.out.println("current mode: " + mode);
        if (mode.equalsIgnoreCase("local")) {
            System.out.println("Running Locally");
            chromeDriver = new ChromeDriver();

        } else if (mode.equalsIgnoreCase("browserstack")) {
            System.out.println("Running on BrowserStack");// BrowserStack SDK intercepts this
            chromeDriver = new ChromeDriver();
        }

        return new EventFiringDecorator<ChromeDriver>(
                new HighlightListener())
                .decorate(chromeDriver);
    }
}