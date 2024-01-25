package objectpage.pages.account;

import objectpage.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage<MyAccountPage> {
    private static final Logger logger = LogManager.getLogger(MyAccountPage.class);

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
        logger.info("Waiting for account section to be clickable");

        WebElement accountSectionElement = wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(accountSection));

        accountSectionElement.click();
        logger.info("Clicked on the account section");

        return new ProfilePage(driver);
    }

}
