package myprojects.automation.assignment3;

import myprojects.automation.assignment3.listener.DriverEventListener;
import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.concurrent.TimeUnit;

public class BaseScript {

    private static final Integer IMPLICIT_WAIT = 30;
    private static final Integer PAGE_LOAD_WAIT = 30;
    private WebDriver driver;
    private WebDriverEventListener driverEventListener;
    private EventFiringWebDriver eventFiringWebDriver;


    private final static BaseScript INSTANCE = new BaseScript();

    private BaseScript() {
    }

    public static BaseScript getInstance() {
        return INSTANCE;
    }

    public EventFiringWebDriver getDriverListener() {
        if (eventFiringWebDriver != null) {
            return eventFiringWebDriver;
        }
        eventFiringWebDriver = new EventFiringWebDriver(getDriver());
        driverEventListener = new DriverEventListener();
        eventFiringWebDriver.register(driverEventListener);
        eventFiringWebDriver.manage().window().maximize();
        eventFiringWebDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        eventFiringWebDriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_WAIT, TimeUnit.SECONDS);
        return eventFiringWebDriver;
    }

    private WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        switch (Properties.getBrowser()) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", BaseScript.class.getResource("chromedriver.exe").getPath());
                driver = new ChromeDriver();
                break;
            case BrowserType.FIREFOX:
                System.setProperty("webdriver.gecko.driver", BaseScript.class.getResource("geckodriver.exe").getPath());
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Wrong Browser Type");
        }
        return driver;
    }

    public void driverClose() {
        driver.quit();
        driver = null;
        driverEventListener = null;
    }
}
