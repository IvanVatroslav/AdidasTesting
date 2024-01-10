package objectpage.pages.account;

import lombok.Getter;
import objectpage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends BasePage<ProfilePage> {
    private final By PREFERENCES_BUTTON_XPATH = By.xpath("//a[@data-auto-id='members-home-account-preferences']");

    private final By EDIT_DETAILS_BUTTON_XPATH = By.xpath("//button[@data-auto-id='edit-profile-information-button-PROFILE_INFORMATION_MODAL']");
    private final By DAY_FIELD_ID = By.id("date-of-birth-day");
    private final By MONTH_FIELD_ID = By.id("date-of-birth-month");
    private final By YEAR_FIELD_ID = By.id("date-of-birth-year");
    private final By SAVE_BUTTON_XPATH = By.xpath("//button[@data-auto-id='personal-info:button.submit']");
    private final By DISPLAYED_NAME_LOCATOR = By.xpath("//div[@data-auto-id='ma-name-overview']");
    private final By DISPLAYED_DATE_LOCATOR = By.xpath("//div[@data-auto-id='ma-dateOfBirth-overview']");
    private static final By FIRST_NAME_ERROR_XPATH = By.xpath("//div[@data-auto-id='personal-info:firstName-error']");
    private static final By LAST_NAME_ERROR_XPATH = By.xpath("//div[@data-auto-id='personal-info:lastName-error']");
    private static final By DATE_ERROR_XPATH = By.xpath("//div[@class='gl-form-hint gl-form-hint--error']");

    @FindBy(xpath = "//a[@data-auto-id='members-home-account-address-book']")
    private WebElement addressBookLink;

    @Getter
    @FindBy(id = "personal-info:firstName")
    private WebElement firstNameField;

    @Getter
    @FindBy(id = "personal-info:lastName")
    private WebElement lastNameField;
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

    public WebElement getDayField() {
        WebElement dayField = driver.findElement(DAY_FIELD_ID);
        dayField.clear();
        return dayField;
    }

    public WebElement getMonthField() {
        WebElement monthField = driver.findElement(MONTH_FIELD_ID);
        monthField.clear();
        return monthField;
    }

    public WebElement getYearField() {
        WebElement yearField = driver.findElement(YEAR_FIELD_ID);
        yearField.clear();
        return yearField;
    }

    public void clickSaveButton() {
        WebElement saveButton = driver.findElement(SAVE_BUTTON_XPATH);
        saveButton.click();
    }

    public String getDisplayedName() {
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DISPLAYED_NAME_LOCATOR));
        return nameElement.getText();
    }

    public String getDisplayedDate() {
        WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DISPLAYED_DATE_LOCATOR));
        return dateElement.getText();
    }


    public void navigateToAddressBookPage() {
        addressBookLink.click();
    }

    public boolean isErrorMessageDisplayed() {
        return waitForElement(FIRST_NAME_ERROR_XPATH) ||
                waitForElement(LAST_NAME_ERROR_XPATH) ||
                waitForElement(DATE_ERROR_XPATH);
    }

    private boolean waitForElement(By locator) {
        WebDriverWait newWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            newWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void enterBirthDate(int day, int month, int year) {
        WebElement dayField = getDayField();
        dayField.clear();
        dayField.sendKeys(String.valueOf(day));

        WebElement monthField = getMonthField();
        monthField.clear();
        monthField.sendKeys(String.valueOf(month));

        WebElement yearField = getYearField();
        yearField.clear();
        yearField.sendKeys(String.valueOf(year));
    }

    public PreferencesPage clicksPreferencesButton() {
        WebElement preferencesButton = wait.until(ExpectedConditions.elementToBeClickable(PREFERENCES_BUTTON_XPATH));
        preferencesButton.click();
        return new PreferencesPage(driver);
    }

}
