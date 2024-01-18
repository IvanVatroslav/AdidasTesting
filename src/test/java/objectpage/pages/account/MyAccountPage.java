package objectpage.pages.account;

import objectpage.nonpages.components.SidePanel;
import objectpage.pages.BasePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage<MyAccountPage> {
    private static final Logger logger = Logger.getLogger(SidePanel.class);

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
        // Assuming you have a logger set up as in clickAccountLink()
        logger.info("Waiting for account section to be clickable");

        // Wait for the account section to be clickable, with the ignoring of StaleElementReferenceException
        WebElement accountSectionElement = wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(accountSection));

        // Click on the account section
        accountSectionElement.click();
        logger.info("Clicked on the account section");

        // Return the new ProfilePage
        return new ProfilePage(driver);
    }

}
