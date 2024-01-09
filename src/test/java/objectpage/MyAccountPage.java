package objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage {

    @FindBy(id = "ACCOUNT")
    private WebElement accountSection;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProfilePage clickOnAccountSection() {
        wait.until(ExpectedConditions.elementToBeClickable(accountSection)).click();
        return new ProfilePage(driver);
    }
}
