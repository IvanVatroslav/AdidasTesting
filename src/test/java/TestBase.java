import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class TestBase {
    private static WebDriver driver;
    private static Scanner scanner = new Scanner(System.in);
    private static FluentWait<WebDriver> fluentWait;

    private static WebDriverWait wait;

    public static WebDriver getDriver() {
        return driver;
    }

    public static Scanner getScanner() {
        return scanner;
    }
    public static FluentWait<WebDriver> getFluentWait() {
        return fluentWait;
    }
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        this.driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://www.adidas.com/us");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        logIn();
    }

    @After
    public void tearDown() {
        // Close the browser and WebDriver

    }

    public static WebDriverWait getWait() {
        return wait;
    }

    private void logIn() {


        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        WebElement stupidCookiesWindow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"gl-cta gl-cta--tertiary\"]")));
        stupidCookiesWindow.click();


        driver.findElement(By.xpath("//a[@data-auto-id=\"customer-info-button\"]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("social-button-yahoo"))).click();
        driver.findElement(By.id("login-username")).sendKeys("pero.peric64@yahoo.com");
        driver.findElement(By.id("login-signin")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-passwd"))).sendKeys("SifraSifric321");
        driver.findElement(By.id("login-signin")).click();
        driver.findElement(By.id("oauth2-agree")).click();

    }


}
