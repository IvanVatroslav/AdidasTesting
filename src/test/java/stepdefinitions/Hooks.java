package stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import objectpage.nonpages.modals.CookiesModal;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.SetupProperties;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static services.SetupProperties.getScreenshotDir;

public class Hooks {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private static final ExtentReports extent;

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("path/to/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Before
    public void setUp(Scenario scenario) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        WebDriver localDriver = new ChromeDriver(options);
        localDriver.manage().window().maximize();

        ExtentTest test = extent.createTest(scenario.getName());
        extentTest.set(test);

        String baseUrl = SetupProperties.getMainUrl();
        localDriver.get(baseUrl);
        WebDriverWait explicitWait = new WebDriverWait(localDriver, Duration.ofSeconds(30));
        explicitWait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );

        driver.set(localDriver);
        wait.set(explicitWait);

        CookiesModal cookiesModal = new CookiesModal(localDriver);
        cookiesModal.acceptCookies();
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
        extentTest.remove();

    }

    @AfterStep
    public void addScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            WebDriver localDriver = driver.get();
            if (localDriver != null) {
                try {
                    File screenshotFile = ((TakesScreenshot) localDriver).getScreenshotAs(OutputType.FILE);
                    String screenshotDirectory = getScreenshotDir();
                    String screenshotFileName = "Screenshot_" + scenario.getName() + "_" + System.currentTimeMillis() + ".png";
                    String screenshotPath = screenshotDirectory + screenshotFileName;
                    File destinationPath = new File(screenshotPath);
                    FileUtils.copyFile(screenshotFile, destinationPath);

                    extentTest.get().fail("Failure Screenshot",
                            MediaEntityBuilder.createScreenCaptureFromPath(destinationPath.getAbsolutePath()).build());

                    byte[] screenshot = FileUtils.readFileToByteArray(destinationPath);
                    scenario.attach(screenshot, "image/png", "screenshot");

                } catch (WebDriverException | IOException e) {
                    scenario.log("Failed to take screenshot: " + e.getMessage());
                }
            } else {
                scenario.log("Driver is null, screenshot not taken.");
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
