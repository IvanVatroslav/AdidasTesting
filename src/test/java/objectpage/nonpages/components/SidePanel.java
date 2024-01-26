package objectpage.nonpages.components;

import lombok.SneakyThrows;
import objectpage.nonpages.BaseComponents;
import objectpage.pages.account.MyAccountPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SidePanel extends BaseComponents {

    @FindBy(id = "side-panel-container")
    private WebElement sidePanelContainerLocator;
    @FindBy(xpath = "//button[@data-testid='side-panel-title-close']")
    private WebElement closeSidePanelButton;

    private final By accountLinkBy = By.xpath("//a[@data-testid='account-link']/span");

    public SidePanel(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        List<WebElement> sidePanelElements = driver.findElements(By.xpath("//div[@data-testid='sidepanel-container']"));
        if (!sidePanelElements.isEmpty()) {
            return sidePanelElements.get(0);
        } else {
            return null;
        }
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

    public void closeSidePanel() {
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(closeSidePanelButton));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(closeSidePanelButton));

        closeSidePanelButton.click();

        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.invisibilityOf(closeSidePanelButton));
    }

    public boolean isPanelOpen() {
        return getUniqueElement() != null;
    }

}
