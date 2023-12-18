package ObjectPage;

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

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> waitThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<FluentWait<WebDriver>> fluentWaitThreadLocal = new ThreadLocal<>();

    private static ExtentReports extent;

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
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        // Set WebDriver instances to thread local variables
        driverThreadLocal.set(driver);
        waitThreadLocal.set(wait);
        fluentWaitThreadLocal.set(fluentWait);

        logIn();
    }

    @SneakyThrows
    @After
    public void tearDown() {
        // Quit the WebDriver instance and remove from the thread local
        Thread.sleep(10000);
        getDriver().quit();
        driverThreadLocal.remove();
        waitThreadLocal.remove();
        fluentWaitThreadLocal.remove();
    }

    private void logIn() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            // Load the properties file
            prop.load(input);

            // Get username and password from properties file
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            // Perform the login process using the username and password
            getWait().until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            WebElement cookiesAcceptButton = getWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"gl-cta gl-cta--tertiary\"]")));
            cookiesAcceptButton.click();

            getDriver().findElement(By.xpath("//a[@data-auto-id=\"customer-info-button\"]")).click();
            getWait().until(ExpectedConditions.elementToBeClickable(By.id("social-button-yahoo"))).click();
            getDriver().findElement(By.id("login-username")).sendKeys(username);
            getDriver().findElement(By.id("login-signin")).click();
            getWait().until(ExpectedConditions.elementToBeClickable(By.id("login-passwd"))).sendKeys(password);
            getDriver().findElement(By.id("login-signin")).click();
            getDriver().findElement(By.id("oauth2-agree")).click();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @BeforeClass
    public static void setUpClass() {
        // Initialize ExtentReports and attach the HtmlReporter
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\ivan.zeljeznjak\\Desktop\\AdidasTesting\\target\\test-classes\\extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterClass
    public static void tearDownClass() {
        // Write the report to file at the end of the test suite
        if (extent != null) {
            extent.flush();
        }
    }
}


