package objectpage.nonpages.components;

import lombok.SneakyThrows;
import objectpage.pages.account.MyAccountPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

public class SidePanel {
    private static final Logger logger = Logger.getLogger(SidePanel.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "side-panel-container")
    private WebElement sidePanelContainerLocator;

    private final By accountLinkBy = By.xpath("//a[@data-testid='account-link']/span");

    public SidePanel(WebDriver driver) {
        this.driver = Hooks.driver.get();
        this.wait = Hooks.wait.get();
        PageFactory.initElements(driver, this);
    }

    @SneakyThrows
    public MyAccountPage clickAccountLink() {
        wait.until(ExpectedConditions.visibilityOf(sidePanelContainerLocator));
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
