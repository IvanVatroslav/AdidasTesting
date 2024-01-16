package objectpage.pages.search;

import objectpage.pages.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NoResultsPage extends BasePage<NoResultsPage> {

    @FindBy(xpath = "//input[@data-auto-id='searchinput-desktop']")
    private  WebElement searchBox;

    @FindBy(xpath = "//h4[contains(text(), 'OOPS – NO RESULTS FOR')]")
    private  WebElement NO_RESULTS_MESSAGE_LOCATOR;
    public NoResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected NoResultsPage openPage() {
        return null;
    }
    public  void searchFor(String keyword) {
        searchBox.sendKeys(keyword + Keys.ENTER);
    }

    public WebElement getNoResultsMessageLocator() {
wait.until(ExpectedConditions.visibilityOf(NO_RESULTS_MESSAGE_LOCATOR));
    return NO_RESULTS_MESSAGE_LOCATOR;
    }
}
