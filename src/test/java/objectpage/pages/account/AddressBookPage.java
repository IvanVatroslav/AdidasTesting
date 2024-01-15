package objectpage.pages.account;

import objectpage.BasePage;
import objectpage.nonpages.modals.AddressBookModal;
import objectpage.nonpages.modals.DeleteAddressModal;
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


    // Locators
    private static final By DELETE_ADDRESS_BUTTON_XPATH = By.xpath("//button[@data-auto-id='delete_address']");
    private static final By CONFIRM_DELETE_BUTTON_XPATH = By.xpath("//button[@data-auto-id='confirm-delete']");
    private static final By PLUS_BUTTON_XPATH = By.xpath("//span[@data-testid='plus']");
    private static final By SAVED_ADDRESS_DIV_XPATH = By.xpath("//div[@data-auto-id='saved_address']");
    private static final By ADDRESS_DETAIL_DIV_XPATH = By.xpath(".//div[contains(@class, 'gl-vspace-bpall-small')]"); // AddressBookPage class
    private static final By STRONG_TAG_XPATH = By.xpath(".//strong"); // AddressBookPage class
    private DeleteAddressModal deleteModal;

    public AddressBookPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.deleteModal = new DeleteAddressModal(driver);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected AddressBookPage openPage() {
        return null;
    }

    public AddressBookModal openAddAddressModal() {
        WebElement plusButton = wait.until(ExpectedConditions.elementToBeClickable(PLUS_BUTTON_XPATH));
        plusButton.click();
        return new AddressBookModal(driver);
    }

    public void removeAllAddresses() {
        waitForModalInvisibility();
        wait.until(ExpectedConditions.elementToBeClickable(DELETE_ADDRESS_BUTTON_XPATH));
        // Check if there are any delete buttons on the page
        List<WebElement> removeButtons = driver.findElements(DELETE_ADDRESS_BUTTON_XPATH);
        if (removeButtons.isEmpty()) {
            return; // Exit if there are no delete buttons (i.e., no addresses to delete)
        }

        // Proceed with deletion only if delete buttons are present
        for (WebElement button : removeButtons) {
            try {
                wait.until(ExpectedConditions.visibilityOf(button));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();

                deleteModal.confirmDelete();

                waitForModalInvisibility();
                // Wait briefly to allow the DOM to update after each deletion
            } catch (StaleElementReferenceException e) {
                System.out.println("Caught a stale element exception, retrying...");
            }
        }
    }


    public AddressBookModal openAddressModal() {
        waitForModalInvisibility(); // Assuming this method is part of AddressBookPage
       // wait.until(ExpectedConditions.visibilityOfElementLocated(PLUS_BUTTON_XPATH));

        //   WebElement plusButton = wait.until(ExpectedConditions.elementToBeClickable(PLUS_BUTTON_XPATH));
        //plusButton.click();

        driver.findElement(PLUS_BUTTON_XPATH).click();
        return new AddressBookModal(driver); // Assuming this is the correct way to instantiate AddressBookModal
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
}
