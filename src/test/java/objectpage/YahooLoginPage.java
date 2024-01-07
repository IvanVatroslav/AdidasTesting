package objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YahooLoginPage extends BasePage {
    private final WebDriverWait wait;

    @FindBy(id = "login-username")
    private WebElement usernameTextBox;

    @FindBy(id = "login-signin")
    private WebElement loginNextButton;

    @FindBy(id = "login-passwd")
    private WebElement passwordTextBox;

    @FindBy(id = "login-signin")
    private WebElement loginButton;

    @FindBy(id = "oauth2-agree")
    private WebElement authAgreeButton;

    public YahooLoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Assuming 10 seconds wait
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameTextBox)).sendKeys(username);
    }

    public void clickNextButtonLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginNextButton)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextBox)).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clickAuthButton() {
        wait.until(ExpectedConditions.elementToBeClickable(authAgreeButton)).click();
    }
}
