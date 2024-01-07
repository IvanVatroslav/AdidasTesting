package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected static WebDriverWait wait;
    protected static WebDriver driver;
    public static final By MODAL_MAIN_DIV_XPATH = By.xpath("your-xpath-here");
    private static final By COOKIES_ACCEPT_BUTTON = By.xpath("//button[@class='gl-cta gl-cta--tertiary']");

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Corrected to use Duration

        PageFactory.initElements(driver, this);
    }

    public static byte[] getScreenshot() {
        return null;
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }


    public static void waitForModalInvisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.invisibilityOfElementLocated(MODAL_MAIN_DIV_XPATH));
    }

    public static void acceptCookies() {
        WebElement cookiesAcceptButton = wait.until(ExpectedConditions.elementToBeClickable(COOKIES_ACCEPT_BUTTON));
        cookiesAcceptButton.click();
    }


}
