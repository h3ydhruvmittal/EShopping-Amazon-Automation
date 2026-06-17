package implementations;

import interfaces.App;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import drivers.DriverFactory;
import utils.WaitUtils;

public class AppImpl implements App {
    WebDriver webDriver = DriverFactory.initializeDriver();

    String baseUrl = ConfigReader.getProperty("base.url");

    @Override
    public void open() throws InterruptedException {

        webDriver.manage().window().maximize();
        System.out.println(baseUrl);
        webDriver.get(baseUrl);
        Thread.sleep(2000L);
        WaitUtils.waitForPageToLoad(webDriver);

    }

    @Override
    public void close() throws InterruptedException {
        Thread.sleep(5000L);
        webDriver.quit();
    }

    public WebDriver getDriver() {
        return webDriver;
    }
}
