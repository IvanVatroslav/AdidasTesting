package stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import objectpage.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static FluentWait<WebDriver> fluentWait;
    private static ExtentReports extent;

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("path/to/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Before
    public void setUp() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.adidas.com/us");

            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofSeconds(5))
                    .ignoring(NoSuchElementException.class);

            BasePage.setDriver(driver);
            BasePage.setWait(wait);
            BasePage.acceptCookies();
        }
    }

    @SneakyThrows
    @After
    public void tearDown() {
        if (driver != null) {
            Thread.sleep(5000);
            driver.quit();
            driver = null;
        }
    }

    @AfterStep
    public void addScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = BasePage.getScreenshot();
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static FluentWait<WebDriver> getFluentWait() {
        return fluentWait;
    }

    public static ExtentReports getExtent() {
        return extent;
    }
}
