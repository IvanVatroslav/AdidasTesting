package objectpage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Base {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<FluentWait<WebDriver>> fluentWaitThreadLocal = new ThreadLocal<>();

    private static ExtentReports extent;


    private static final By MODAL_MAIN_DIV_XPATH = By.xpath("//div[@class='gl-modal__main']");
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static WebDriverWait getWait() {
        return waitThreadLocal.get();
    }

    public static FluentWait<WebDriver> getFluentWait() {
        return fluentWaitThreadLocal.get();
    }


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.adidas.com/us");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        driverThreadLocal.set(driver);
        waitThreadLocal.set(wait);
        fluentWaitThreadLocal.set(fluentWait);
        MainPage.acceptCookies();


    }

    @SneakyThrows
    @After
    public void tearDown() {
        Thread.sleep(3000);
        getDriver().quit();
        driverThreadLocal.remove();
        waitThreadLocal.remove();
        fluentWaitThreadLocal.remove();
    }



    @BeforeClass
    public static void setUpClass() {
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\ivan.zeljeznjak\\Desktop\\AdidasTesting\\target\\test-classes\\extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterClass
    public static void tearDownClass() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static byte[] getScreenshot() {
        try {
            System.out.println("Taking screenshot...");
            System.out.println("Current URL: " + driverThreadLocal.get().getCurrentUrl());
            System.out.println("Page Title: " + driverThreadLocal.get().getTitle());
            System.out.println("Window Size: " + driverThreadLocal.get().manage().window().getSize());

            File scrFile = ((TakesScreenshot) driverThreadLocal.get()).getScreenshotAs(OutputType.FILE);
            return Files.readAllBytes(scrFile.toPath());
        } catch (WebDriverException | IOException e) {
            System.out.println("Exception during screenshot capture: " + e.getMessage());
        }
        return null;
    }



    public static void waitForModalInvisibility() {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(MODAL_MAIN_DIV_XPATH));
    }
}
