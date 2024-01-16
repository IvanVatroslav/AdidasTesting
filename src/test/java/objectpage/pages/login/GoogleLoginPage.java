package objectpage.pages.login;

import objectpage.pages.BasePage;
import objectpage.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleLoginPage extends BasePage<GoogleLoginPage> {

    @FindBy(id = "identifierId")
    private WebElement loginTextBox;

    @FindBy(id = "identifierNext")
    private WebElement nextButtonLogin;

    @FindBy(name = "Passwd")
    private WebElement passwordTextBox;

    @FindBy(id = "passwordNext")
    private WebElement loginButton;

    public GoogleLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected GoogleLoginPage openPage() {
        return null;
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(loginTextBox)).sendKeys(username);
    }

    public void clickNextAfterUsername() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButtonLogin)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextBox)).sendKeys(password);
    }

    public void clickNextAfterPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public MainPage login(String username, String password) {
        enterUsername(username);
        clickNextAfterUsername();
        enterPassword(password);
        clickNextAfterPassword();
        return new MainPage(driver);

    }
}
