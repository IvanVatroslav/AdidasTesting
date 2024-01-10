package objectpage.pages.account;

import objectpage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage<MyAccountPage> {

    @FindBy(id = "ACCOUNT")
    private WebElement accountSection;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected MyAccountPage openPage() {
        return null;
    }

    public ProfilePage clickOnAccountSection() {
        wait.until(ExpectedConditions.elementToBeClickable(accountSection)).click();
        return new ProfilePage(driver);
    }
}
