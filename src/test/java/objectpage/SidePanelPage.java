package objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SidePanelPage extends BasePage {


    @FindBy(xpath = "//a[@data-testid='account-link']/span")
    private WebElement accountLink;


    public SidePanelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void clickAccountLink() {
        accountLink.click();
    }


}
