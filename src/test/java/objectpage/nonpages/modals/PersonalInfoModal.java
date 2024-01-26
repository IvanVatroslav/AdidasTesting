package objectpage.nonpages.modals;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class PersonalInfoModal extends BaseModal {
    @Getter
    @FindBy(id = "personal-info:firstName")
    private WebElement firstNameField;

    @Getter
    @FindBy(id = "personal-info:lastName")
    private WebElement lastNameField;
    private final By DAY_FIELD_ID = By.id("date-of-birth-day");
    private final By MONTH_FIELD_ID = By.id("date-of-birth-month");
    private final By YEAR_FIELD_ID = By.id("date-of-birth-year");
    private static final By FIRST_NAME_ERROR_XPATH = By.xpath("//div[@data-auto-id='personal-info:firstName-error']");
    private static final By LAST_NAME_ERROR_XPATH = By.xpath("//div[@data-auto-id='personal-info:lastName-error']");
    private static final By DATE_ERROR_XPATH = By.xpath("//div[@class='gl-form-hint gl-form-hint--error']");
    private final By SAVE_BUTTON_XPATH = By.xpath("//button[@data-auto-id='personal-info:button.submit']");

    public PersonalInfoModal(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected By getModalLocator() {
        return null;
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
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

    public void clickSaveButton() {
        WebElement saveButton = driver.findElement(SAVE_BUTTON_XPATH);
        saveButton.click();
    }


    public void changeBirthDateAndName(Map<String, String> entry) {
        String date = entry.get("Date");
        String name = entry.get("Name");
        String outcome = entry.get("Outcome");

        String[] nameParts = name.split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        String[] dateParts = date.split("-");
        int day = Integer.parseInt(dateParts[1].trim());
        int month = Integer.parseInt(dateParts[2].trim());
        int year = Integer.parseInt(dateParts[0].trim());

        clearAndSendKeys(firstNameField, firstName);
        if (!lastName.isEmpty()) {
            clearAndSendKeys(lastNameField, lastName);
        }

        enterBirthDate(day, month, year);

        if ("save and display".equals(outcome)) {
            clickSaveButton();
        } else if ("reject".equals(outcome)) {
            assertTrue(isErrorMessageDisplayed());
        }



        }


    protected void clearAndSendKeys(WebElement element, String text) {
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(text);
    }

}

