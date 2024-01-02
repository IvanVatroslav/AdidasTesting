package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {

    private static final By COOKIES_ACCEPT_BUTTON = By.xpath("//button[@class='gl-cta gl-cta--tertiary']");
    private static final By CUSTOMER_INFO_BUTTON = By.xpath("//a[@data-auto-id='customer-info-button']");
    private static final By YAHOO_BUTTON = By.id("social-button-yahoo");
    private static final By ACCOUNT_LINK = By.xpath("//a[@data-testid=\"account-link\"]/span");
    private static final By ACCOUNT_SETTINGS = By.id("ACCOUNT");
    private static final By GOOGLE_BUTTON = By.id("social-button-google");

    public static void acceptCookies() {
        WebElement cookiesAcceptButton = Base.getWait().until(ExpectedConditions.elementToBeClickable(COOKIES_ACCEPT_BUTTON));
        cookiesAcceptButton.click();
    }

    public static void openCustomerInfo() {
        WebElement customerInfoButton = Base.getDriver().findElement(CUSTOMER_INFO_BUTTON);
        customerInfoButton.click();
    }

    public static void clickYahooButton() {
        WebElement yahooButton = Base.getWait().until(ExpectedConditions.elementToBeClickable(YAHOO_BUTTON));
        yahooButton.click();
    }

    public static void clickAccountLink() {
        WebElement accountLink = Base.getWait().until(ExpectedConditions.elementToBeClickable(ACCOUNT_LINK));
        accountLink.click();
    }

    public static void clickAccountSettings() {
        WebElement accountSettings = Base.getDriver().findElement(ACCOUNT_SETTINGS);
        accountSettings.click();
    }

    public static void clickGoogleButton() {
        WebElement googleButton = Base.getWait().until(ExpectedConditions.elementToBeClickable(GOOGLE_BUTTON));
        googleButton.click();
    }
}
