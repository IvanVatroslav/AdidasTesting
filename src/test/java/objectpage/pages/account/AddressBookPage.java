package objectpage.pages.account;

import objectpage.nonpages.modals.AddressBookModal;
import objectpage.nonpages.modals.DeleteAddressModal;
import objectpage.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AddressBookPage extends BasePage<AddressBookPage> {


    private static final By DELETE_ADDRESS_BUTTON_XPATH = By.xpath("//button[@data-auto-id='delete_address']");
    private static final By PLUS_BUTTON_XPATH = By.xpath("//span[@data-testid='plus']");
    private static final By SAVED_ADDRESS_DIV_XPATH = By.xpath("//div[@data-auto-id='saved_address']");
    private static final By ADDRESS_DETAIL_DIV_XPATH = By.xpath(".//div[contains(@class, 'gl-vspace-bpall-small')]");
    private static final By STRONG_TAG_XPATH = By.xpath(".//strong");

    public AddressBookPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected AddressBookPage openPage() {
        return null;
    }



    public void removeAllAddresses() {
        waitForModalInvisibility();
        List<WebElement> removeButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(DELETE_ADDRESS_BUTTON_XPATH));
        if (removeButtons.isEmpty()) {
            return;
        }
        wait.until(ExpectedConditions.elementToBeClickable(DELETE_ADDRESS_BUTTON_XPATH));

        for (WebElement button : removeButtons) {
            try {
                wait.until(ExpectedConditions.visibilityOf(button));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                DeleteAddressModal confirmDeleteAddressModal = new DeleteAddressModal(driver);
                confirmDeleteAddressModal.confirmDelete();

                waitForModalInvisibility();
            } catch (StaleElementReferenceException e) {
                System.out.println("Caught a stale element exception, retrying...");
            }
        }
    }


    public AddressBookModal openAddAddressModal() {
        WebElement plusButton = wait.until(ExpectedConditions.elementToBeClickable(PLUS_BUTTON_XPATH));
        plusButton.click();
        return new AddressBookModal(driver);
    }


    public void addNewAddress(String firstName, String lastName, String streetAddress, String city, String state, String zipCode, String phoneNumber) {
        waitForModalInvisibility();
        wait.until(ExpectedConditions.elementToBeClickable(PLUS_BUTTON_XPATH)).click();
    }

    public List<WebElement> getSavedAddressElements() {
        waitForModalInvisibility();
        return driver.findElements(SAVED_ADDRESS_DIV_XPATH);
    }

    public void assertAddresses(List<Map<String, String>> expectedAddresses) {
        waitForModalInvisibility();
        List<WebElement> addressElements = driver.findElements(SAVED_ADDRESS_DIV_XPATH);
        assertEquals("Number of addresses does not match", expectedAddresses.size(), addressElements.size());

        for (int i = 0; i < expectedAddresses.size(); i++) {
            Map<String, String> expectedAddress = expectedAddresses.get(i);
            WebElement addressElement = addressElements.get(i);

            String expectedFullName = expectedAddress.get("FirstName") + " " + expectedAddress.get("LastName");
            assertEquals(expectedFullName, addressElement.findElement(STRONG_TAG_XPATH).getText());

            String fullAddress = addressElement.findElement(ADDRESS_DETAIL_DIV_XPATH).getText();
            String[] addressParts = fullAddress.split("\n");

            assertEquals(expectedAddress.get("Address"), addressParts[0]);
            String expectedStateAbbreviation = convertStateNameToAbbreviation(expectedAddress.get("State"));
            String expectedCityStateZip = expectedAddress.get("City") + ", " + expectedStateAbbreviation + ", " + expectedAddress.get("Zip") + ", US";
            assertEquals(expectedCityStateZip, addressParts[1]);
            assertEquals(expectedAddress.get("Phone"), addressParts[2]);
        }
    }


    private String convertStateNameToAbbreviation(String stateName) {
        Map<String, String> stateAbbreviations = new HashMap<>();
        stateAbbreviations.put("Florida", "FL");
        stateAbbreviations.put("Maine", "ME");

        return stateAbbreviations.getOrDefault(stateName, stateName);
    }

    public void addAddresses(List<Map<String, String>> addresses) {
        for (Map<String, String> address : addresses) {
            try {
                AddressBookModal addressBookModal = this.openAddAddressModal();

                addressBookModal.fillAddressForm(
                        address.get("FirstName"),
                        address.get("LastName"),
                        address.get("Address"),
                        address.get("City"),
                        address.get("State"),
                        address.get("Zip"),
                        address.get("Phone"));

                addressBookModal.submitAddress();
                this.waitForModalInvisibility();
            } catch (Exception e) {
                logger.error("Exception occurred while adding address: " + e.getMessage());
            }
        }
    }
}
