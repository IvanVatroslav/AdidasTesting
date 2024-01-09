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
import objectpage.MainPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.SetupProperties;

import java.time.Duration;

public class Hooks {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    private static FluentWait<WebDriver> fluentWait; // If used, implement; otherwise, remove
    private static final ExtentReports extent; // Made 'extent' final

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("path/to/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        WebDriver localDriver = new ChromeDriver(options);
        localDriver.manage().window().maximize();

        String baseUrl = SetupProperties.getMainUrl();
        localDriver.get(baseUrl);
        WebDriverWait explicitWait = new WebDriverWait(localDriver, Duration.ofSeconds(10));
        explicitWait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );

        // Set the WebDriver for the current thread
        driver.set(localDriver);
        wait.set(explicitWait);

        // Instantiate MainPage and call acceptCookies
        MainPage mainPage = new MainPage(localDriver);
        mainPage.acceptCookies();
    }




    @SneakyThrows
    @After
    public void tearDown() {
        Thread.sleep(5000);
        WebDriver localDriver = driver.get();
        if (localDriver != null) {
            localDriver.quit();
        }
        driver.remove();
        wait.remove();
        // Remove other ThreadLocal instances
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

    public WebDriver getDriver() {
        return driver.get();
    }

    public WebDriverWait getWait() {
        return wait.get();
    }

    public static FluentWait<WebDriver> getFluentWait() {
        return fluentWait;
    }

    public static ExtentReports getExtent() {
        return extent;
    }


}
