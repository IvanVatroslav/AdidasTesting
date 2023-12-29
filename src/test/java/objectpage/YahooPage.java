package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YahooPage {


    static By usernameTextBox_id = By.id("login-username");
    static By loginNext_id = By.id("login-signin");

    static By passwordTextBox_id = By.id("login-passwd");

    static By loginButton_id = By.id("login-signin");

    static By authAgreeButton_id = By.id("oauth2-agree");
    public static WebElement getLoginTextBox() {

        return Base.getDriver().findElement(usernameTextBox_id);
    }

    public static void clickNextButtonLogin() {
        WebElement nextButton = Base.getDriver().findElement(loginNext_id );
        nextButton.click();
    }

    public static WebElement getPasswordTextBox() {
        return Base.getWait().until(ExpectedConditions.elementToBeClickable(passwordTextBox_id));
    }

    public static void clickLoginButton() {
        WebElement LoginButton = Base.getDriver().findElement(loginButton_id);
        LoginButton.click();
    }
    public static void clickAuthButton() {
        WebElement AuthButton = Base.getDriver().findElement(authAgreeButton_id);
        AuthButton.click();
    }
}
