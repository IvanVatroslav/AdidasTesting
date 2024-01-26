package stepdefinitions.stepswithlogin.accountsteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.pages.account.AddressBookPage;
import objectpage.pages.account.ProfilePage;
import org.openqa.selenium.WebDriver;
import stepdefinitions.Hooks;

import java.util.List;
import java.util.Map;

public class AddressBookSteps {

    private AddressBookPage addressBookPage;
    private List<Map<String, String>> storedAddresses;
    private ProfilePage profilePage;
    private WebDriver driver;

    public AddressBookSteps() {
        this.driver = Hooks.driver.get();
        this.addressBookPage = new AddressBookPage(driver);
        this.profilePage = new ProfilePage(driver);
    }

    @When("the user navigates to the address book page")
    public void navigateToAddressBookPage() {
        profilePage.navigateToAddressBookPage();
    }

    @And("the user removes any old addresses")
    public void removeAllAddresses() {
        addressBookPage.removeAllAddresses();
    }

    @When("the user adds new addresses")
    public void addNewAddress(DataTable addressTable) {
        storedAddresses = addressTable.asMaps(String.class, String.class);
        addressBookPage.addAddresses(storedAddresses);
    }




    @Then("the new addresses should be saved and displayed in the address book")
    public void theNewAddressesShouldBeSavedAndDisplayedInTheAddressBook() {
        addressBookPage.assertAddresses(storedAddresses);
    }

}
