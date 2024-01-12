package objectpage.nonpages.components;

import objectpage.BasePage;
import objectpage.pages.account.MyAccountPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public  class SidePanel extends BasePage {
    private static final Logger logger = Logger.getLogger(SidePanel.class);

    @FindBy(id = "side-panel-container")
    private WebElement sidePanelContainerLocator;

    @FindBy(xpath = "//a[@data-testid='account-link']/span")
    private WebElement accountLinkLocator;

    public SidePanel(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected BasePage openPage() {
        return null;
    }

    public MyAccountPage clickAccountLink() {
        By accountLinkBy = By.xpath("//a[@data-testid='account-link']/span");
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(accountLinkBy));

        logger.info("Waiting for account link to be visible");
        WebElement accountLinkElement = wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(accountLinkBy));

        accountLinkElement.click();
        logger.info("Clicked on the account link");

        return new MyAccountPage(driver);
    }

}
