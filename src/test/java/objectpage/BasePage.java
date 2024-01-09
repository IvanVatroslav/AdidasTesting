package objectpage;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage<T extends BasePage> {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final By COOKIES_ACCEPT_BUTTON = By.xpath("//button[@class='gl-cta gl-cta--tertiary']");
    private final By MODAL_MAIN_DIV_XPATH = By.xpath("//div[@class='gl-modal__main']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public byte[] getScreenshot() {
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

    public void waitForModalInvisibility() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_MAIN_DIV_XPATH));
    }

    public void acceptCookies() {
        WebElement cookiesAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(COOKIES_ACCEPT_BUTTON));
        cookiesAcceptButton.click();
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
}
