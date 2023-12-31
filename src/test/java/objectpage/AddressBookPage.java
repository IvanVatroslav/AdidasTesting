package objectpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressBookPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By DELETE_ADDRESS_BUTTON = By.xpath("//button[@data-auto-id='delete_address']");
    private static final By CONFIRM_DELETE_BUTTON = By.xpath("//button[@data-auto-id='confirm-delete']");
    private static final By MODAL_MAIN_DIV = By.xpath("//div[@class='gl-modal__main']");
    private static final By PLUS_BUTTON = By.xpath("//span[@data-testid='plus']");
    private static final By FIRST_NAME_INPUT = By.xpath("//input[@name='firstName']");
    private static final By LAST_NAME_INPUT = By.xpath("//input[@name='lastName']");
    private static final By ADDRESS_INPUT = By.xpath("//input[@name='address1']");
    private static final By CITY_INPUT = By.xpath("//input[@name='city']");
    private static final By ZIPCODE_INPUT = By.xpath("//input[@name='zipcode']");
    private static final By PHONE_NUMBER_INPUT = By.xpath("//input[@name='phoneNumber']");
    private static final By ARROW_RIGHT_LONG_BUTTON = By.xpath("//span[@data-testid='arrow-right-long']");
    private static final By COMBOBOX_DIV = By.xpath("//div[@role='combobox']");
    private static final By CHECKOUT_DROPDOWN_LIST = By.id("gl-dropdown-custom__listbox--checkout-dropdown']");
    private static final By SAVED_ADDRESS_DIV = By.xpath("//div[@data-auto-id='saved_address']");
    private static final By STRONG_TAG = By.xpath(".//strong");
    private static final By ADDRESS_DETAIL_DIV = By.xpath(".//div[contains(@class, 'gl-vspace-bpall-small')]");

    public AddressBookPage() {
        this.driver = Base.getDriver();
        this.wait = Base.getWait();
    }


    public By getDeleteAddressButtonSelector() {
        return DELETE_ADDRESS_BUTTON;
    }

    public By getConfirmDeleteButtonSelector() {
        return CONFIRM_DELETE_BUTTON;
    }

    public By getModalMainDivSelector() {
        return MODAL_MAIN_DIV;
    }

    public By getPlusButtonSelector() {
        return PLUS_BUTTON;
    }

    public By getFirstNameInputSelector() {
        return FIRST_NAME_INPUT;
    }

    public By getLastNameInputSelector() {
        return LAST_NAME_INPUT;
    }

    public By getAddressInputSelector() {
        return ADDRESS_INPUT;
    }

    public By getCityInputSelector() {
        return CITY_INPUT;
    }

    public By getZipcodeInputSelector() {
        return ZIPCODE_INPUT;
    }

    public By getPhoneNumberInputSelector() {
        return PHONE_NUMBER_INPUT;
    }

    public By getArrowRightLongButtonSelector() {
        return ARROW_RIGHT_LONG_BUTTON;
    }

    public By getComboboxDivSelector() {
        return COMBOBOX_DIV;
    }

    public By getCheckoutDropdownListSelector() {
        return CHECKOUT_DROPDOWN_LIST;
    }

    public By getSavedAddressDivSelector() {
        return SAVED_ADDRESS_DIV;
    }

    public By getStrongTagSelector() {
        return STRONG_TAG;
    }

    public By getAddressDetailDivSelector() {
        return ADDRESS_DETAIL_DIV;
    }

}