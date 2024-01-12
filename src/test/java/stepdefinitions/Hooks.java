package stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import objectpage.pages.MainPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.SetupProperties;

import java.time.Duration;

public class Hooks {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    private static final ExtentReports extent;

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


        //driver.manage().timeouts.implicitlyWait(10, TimeUnit.SECONDS); implicit wait maybe implement later

        String baseUrl = SetupProperties.getMainUrl();
        localDriver.get(baseUrl);
        WebDriverWait explicitWait = new WebDriverWait(localDriver, Duration.ofSeconds(30));
        explicitWait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );

        driver.set(localDriver);
        wait.set(explicitWait);

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
    }
    @AfterStep
    public void addScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = null; // will fix later, maybe
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "screenshot");
            }
            else{
                scenario.log("screenshot is null");
            }
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public WebDriverWait getWait() {
        return wait.get();
    }


    public static ExtentReports getExtent() {
        return extent;
    }


}
