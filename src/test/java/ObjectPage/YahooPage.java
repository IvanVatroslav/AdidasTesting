package ObjectPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YahooPage {
//    private WebElement loginTextBox;
//    private WebElement nextButton;
//    private WebElement passwordTextBox;
//    private WebElement loginButton;
//    private WebElement authAgreeButton;
//
//    public YahooPage(WebElement loginTextBox, WebElement nextButton, WebElement passwordTextBox, WebElement loginButton, WebElement authAgreeButton) {
//        this.loginTextBox =  Base.getDriver().findElement(By.id("login-username"));
//        this.nextButton = Base.getDriver().findElement(By.id("login-signin"));
//        this.passwordTextBox = Base.getWait().until(ExpectedConditions.elementToBeClickable(By.id("login-passwd")));
//        this.loginButton = Base.getDriver().findElement(By.id("login-signin"));
//        this.authAgreeButton = Base.getDriver().findElement(By.id("oauth2-agree"));
//    }


    static By usernameTextBox_id = By.id("login-username");
    static By loginNext_id = By.id("login-signin");

    static By passwordTextBox_id = By.id("login-passwd");

    static By loginButton_id = By.id("login-signin");

    static By authAgreeButton_id = By.id("oauth2-agree");
    public static WebElement getLoginTextBox() {
        WebElement loginTextBox = Base.getDriver().findElement(usernameTextBox_id);

        return loginTextBox;
    }

    public static void clickNextButtonLogin() {
        WebElement nextButton = Base.getDriver().findElement(loginNext_id );
        nextButton.click();
    }

    public static WebElement getPasswordTextBox() {
        WebElement passwordTextBox =Base.getWait().until(ExpectedConditions.elementToBeClickable(passwordTextBox_id));
        return passwordTextBox;
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
