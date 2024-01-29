package objectpage.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.SetupProperties;
import stepdefinitions.Hooks;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public abstract class BasePage<T extends BasePage> {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final By MODAL_MAIN_DIV_XPATH = By.xpath("//div[@class='gl-modal__main']");
    protected final Logger logger = LogManager.getLogger(getClass());
    protected String baseUrl = SetupProperties.getMainUrl();

    public BasePage(WebDriver driver) {
        this.driver = Hooks.driver.get();
        this.wait = Hooks.wait.get();
        PageFactory.initElements(driver, this);
    }

    public  byte[] getScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    protected void enterText(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
        } catch (WebDriverException e) {
            throw new RuntimeException("Failed to enter text in element: " + e.getMessage());
        }
    }

    public  void waitForModalInvisibility() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_MAIN_DIV_XPATH));
    }



    boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected abstract WebElement getUniqueElement();
    protected abstract T openPage();

    protected void waitForLoad() {
        wait.withMessage("Waiting for " + getClass().getName() + " load")
                .until(ExpectedConditions.visibilityOf(getUniqueElement()));
    }

    public void checkWebPage() {
        wait.until(ExpectedConditions.urlToBe(baseUrl));
        String currentUrl = driver.getCurrentUrl();
        assertEquals("The user is not on the main page: " + baseUrl, baseUrl, currentUrl);
    }

    protected boolean waitForElement(By locator) {
        WebDriverWait newWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            newWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    protected void clearAndSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
