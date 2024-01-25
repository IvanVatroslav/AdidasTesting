package objectpage.pages.account;

import objectpage.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage<ProfilePage> {
    private final By PREFERENCES_BUTTON_XPATH = By.xpath("//a[@data-auto-id='members-home-account-preferences']");

    private final By EDIT_DETAILS_BUTTON_XPATH = By.xpath("//button[@data-auto-id='edit-profile-information-button-PROFILE_INFORMATION_MODAL']");

    private final By DISPLAYED_NAME_LOCATOR = By.xpath("//div[@data-auto-id='ma-name-overview']");
    private final By DISPLAYED_DATE_LOCATOR = By.xpath("//div[@data-auto-id='ma-dateOfBirth-overview']");


    private final By LOG_OUT_BUTTON_XPATH = By.xpath("//a[@data-auto-id='members-home-account-log-out']");


    @FindBy(xpath = "//a[@data-auto-id='members-home-account-address-book']")
    private WebElement addressBookLink;


    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected ProfilePage openPage() {
        return null;
    }

    public void clickEditDetails() {
        try {
            WebElement editDetails = wait.until(ExpectedConditions.elementToBeClickable(EDIT_DETAILS_BUTTON_XPATH));
            editDetails.click();
        } catch (Exception e) {
            System.out.println("Failed to click on Edit Details button: " + e.getMessage());
        }
    }





    public String getDisplayedName() {
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DISPLAYED_NAME_LOCATOR));
        return nameElement.getText();
    }

    public String getDisplayedDate() {
        WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DISPLAYED_DATE_LOCATOR));
        return dateElement.getText();
    }


    public AddressBookPage navigateToAddressBookPage() {
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(addressBookLink));
        addressBookLink.click();
        return new AddressBookPage(driver);
    }





    public PreferencesPage clicksPreferencesButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PREFERENCES_BUTTON_XPATH));

        WebElement preferencesButton = wait.until(ExpectedConditions.elementToBeClickable(PREFERENCES_BUTTON_XPATH));
        preferencesButton.click();
        return new PreferencesPage(driver);
    }

    public AccountLoginPage clickLogOutButton() {
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(LOG_OUT_BUTTON_XPATH));
            WebElement logOutButton = wait.until(ExpectedConditions.elementToBeClickable(LOG_OUT_BUTTON_XPATH));

            logOutButton.click();
            return new AccountLoginPage(driver);
        } catch (Exception e) {
            System.out.println("Failed to click on Log Out button: " + e.getMessage());
            return null;
        }
    }

}
