package objectpage.components;

import objectpage.BasePage;
import objectpage.account.MyAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class SidePanelPage extends BasePage {

    @FindBy(id = "side-panel-container")
    private WebElement sidePanelContainerLocator;

    @FindBy(xpath = "//a[@data-testid='account-link']/span")
    private WebElement accountLinkLocator;

    public SidePanelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MyAccountPage clickAccountLink() {
        wait.until(ExpectedConditions.visibilityOf(accountLinkLocator));
        wait.until(ExpectedConditions.elementToBeClickable(accountLinkLocator)).click();
        return new MyAccountPage(driver);
    }
}
