package objectpage.nonpages.components;

import objectpage.BasePage;
import objectpage.pages.account.MyAccountPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public  class SidePanel extends BasePage {

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
        wait.until(ExpectedConditions.visibilityOf(accountLinkLocator));
        wait.until(ExpectedConditions.elementToBeClickable(accountLinkLocator)).click();
        return new MyAccountPage(driver);
    }
}
