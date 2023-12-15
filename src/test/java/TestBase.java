import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class TestBase {
    private WebDriver driver;
    private static Scanner scanner = new Scanner(System.in);

    private WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        this.driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://www.adidas.com/us");


        logIn();

    }

    @After
    public void tearDown() {
        // Close the browser and WebDriver

    }

    public WebDriverWait getWait() {
        return wait;
    }

    private void logIn() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        WebElement stupidCookiesWindow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"gl-cta gl-cta--tertiary\"]")));
        stupidCookiesWindow.click();


        driver.findElement(By.xpath("//a[@data-auto-id=\"customer-info-button\"]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("social-button-yahoo"))).click();
        //driver.findElement(By.id("social-button-yahoo")).click();
        driver.findElement(By.id("login-username")).sendKeys("pero.peric64@yahoo.com");
        driver.findElement(By.id("login-signin")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-passwd"))).sendKeys("SifraSifric321");
        //driver.findElement(By.id("login-passwd")).sendKeys("SifraSifric321");
        driver.findElement(By.id("login-signin")).click();
        driver.findElement(By.id("oauth2-agree")).click();
//
//        WebElement EmailTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"email\"]")));
//
//
//
//        EmailTextBox.sendKeys("ivan.v.zeljeznjak@hotmail.com");
//
//        //driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("ivan.v.zeljeznjak@hotmail.com");
//        driver.findElement(By.xpath("//span[@data-testid=\"arrow-right-long\"]")).click();
//
//
//        WebElement PasswordTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"password\"]")));
//        PasswordTextBox.sendKeys("Pa$$w0rd123");
//
//        //driver.findElement(By.xpath("///input[@id=\"password\"]")).sendKeys("Pa$$w0rd123");


        // Additional setup can be done here (like maximizing window, setting timeouts, etc.)
    }


}
