package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final By LOGIN_TEXT_BOX = By.id("identifierId");
    private static final By NEXT_BUTTON_LOGIN = By.id("identifierNext");
    private static final By PASSWORD_TEXT_BOX = By.name("Passwd");
    private static final By LOGIN_BUTTON = By.id("passwordNext");

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        this.wait = Base.getWait();
    }

    public WebElement getLoginTextBox() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_TEXT_BOX));
    }

    public void clickNextButtonLogin() {
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(NEXT_BUTTON_LOGIN));
        nextButton.click();
    }

    public WebElement getPasswordTextBox() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_TEXT_BOX));
    }

    public void clickLogin() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        loginButton.click();
    }
}
