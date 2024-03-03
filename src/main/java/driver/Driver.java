package driver;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.log.Log;

import java.time.Duration;

public class Driver {
    private static volatile Driver instance = null;
    private WebDriver driver;
    /**
     * This private constructor initializes the WebDriver based on the default browser specified in the configuration.
     * It sets up the WebDriver for Firefox, Edge, or Chrome based on the configuration.
     * It maximizes the window, sets implicit wait time to 10 seconds, and navigates to the default URL.
     * If an exception occurs during the setup process, it logs an error message.
     */
    private Driver() {
        try {
            switch (Config.getProperty("default.browser")) {
                case "FIREFOX":
                    WebDriverManager.firefoxdriver().setup();

                    driver = new FirefoxDriver();
                    break;
                case "EDGE":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Config.getProperty("default.browser"));
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(Config.getProperty("default.url"));
        } catch (Exception e) {
            Log.error("Failed to set driver");
        }
    }
    /**
     * Returns the singleton instance of the Driver class.
     * If the instance is null, it creates a new instance in a thread-safe manner using double-checked locking.
     *
     * @return The singleton instance of the Driver class.
     */
    public static Driver getInstance() {
        if (instance == null) {
            synchronized (Driver.class) {
                if (instance == null) {
                    instance = new Driver();
                }
            }
        }
        return instance;
    }
    /**
     * Returns the WebDriver instance associated with this Driver object.
     *
     * @return The WebDriver instance used by this Driver object.
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * Closes the WebDriver instance associated with the Driver class.
     * If the instance is not null and the driver is initialized, it quits the driver, sets the driver to null,
     * and resets the Singleton instance to null.
     */
    public static void closeDriver() {
        if (instance != null && instance.driver != null) {
            instance.driver.quit();
            instance.driver = null;
            instance = null;
        }
    }
}
