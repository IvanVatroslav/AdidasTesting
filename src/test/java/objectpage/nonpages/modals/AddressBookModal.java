package objectpage.nonpages.modals;

import objectpage.pages.account.AddressBookPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddressBookModal extends BaseModal {



    private static final By FIRST_NAME_INPUT_XPATH = By.xpath("//input[@name='firstName']");
    private static final By LAST_NAME_INPUT_XPATH = By.xpath("//input[@name='lastName']");
    private static final By ADDRESS_INPUT_XPATH = By.xpath("//input[@name='address1']");
    private static final By CITY_INPUT_XPATH = By.xpath("//input[@name='city']");
    private static final By ZIPCODE_INPUT_XPATH = By.xpath("//input[@name='zipcode']");
    private static final By PHONE_NUMBER_INPUT_XPATH = By.xpath("//input[@name='phoneNumber']");
    private static final By ARROW_RIGHT_LONG_BUTTON_XPATH = By.xpath("//span[@data-testid='arrow-right-long']");
    private static final By COMBOBOX_DIV_XPATH = By.xpath("//div[@role='combobox']");

    private static final By CHECKOUT_DROPDOWN_LIST_XPATH = By.id("gl-dropdown-custom__listbox--checkout-dropdown");
    private AddressBookPage addressBookPage;


    public AddressBookModal(WebDriver driver) {
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

    public void fillAddressForm(String firstName, String lastName, String streetAddress, String city, String state, String zipCode, String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_INPUT_XPATH)).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT_XPATH).sendKeys(lastName);
        driver.findElement(ADDRESS_INPUT_XPATH).sendKeys(streetAddress);
        driver.findElement(CITY_INPUT_XPATH).sendKeys(city);
        selectSpecificState(state);
        driver.findElement(ZIPCODE_INPUT_XPATH).sendKeys(zipCode);
        driver.findElement(PHONE_NUMBER_INPUT_XPATH).sendKeys(phoneNumber);
        driver.findElement(ARROW_RIGHT_LONG_BUTTON_XPATH).click();

        addressBookPage.waitForModalInvisibility();

    }
    public void selectSpecificState(String stateName) {
        WebElement dropdown = driver.findElement(COMBOBOX_DIV_XPATH);
        dropdown.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_DROPDOWN_LIST_XPATH));

        String stateXPath = getStateXPath(stateName);
        WebElement stateOption = driver.findElement(By.xpath(stateXPath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateOption);
        stateOption.click();
    }
    public void submitAddress() {
        WebElement arrowRightButton = wait.until(ExpectedConditions.elementToBeClickable(ARROW_RIGHT_LONG_BUTTON_XPATH));
        arrowRightButton.click();
    }

    private String getStateXPath(String stateName) {
        return String.format("//ul[@id='gl-dropdown-custom__listbox--checkout-dropdown']/li[contains(text(), '%s')]", stateName);
    }

}
