package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SidePanelPage extends BasePage {

    private By accountLinkLocator = By.xpath("//a[@data-testid='account-link']/span");
    private By sidePanelContainerLocator = By.id("side-panel-container");

    public SidePanelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickAccountLink() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(sidePanelContainerLocator));

            WebElement dynamicAccountLink = driver.findElement(accountLinkLocator);
            wait.until(ExpectedConditions.elementToBeClickable(dynamicAccountLink)).click();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
