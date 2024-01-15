package services;

import objectpage.nonpages.modals.AddressBookModal;
import objectpage.nonpages.modals.CookiesModal;
import objectpage.nonpages.modals.LoginModal;
import objectpage.nonpages.modals.PersonalInfoModal;
import org.openqa.selenium.WebDriver;

public class ModalFactory {

    private WebDriver driver;

    public ModalFactory(WebDriver driver) {
        this.driver = driver;
    }

    public LoginModal createLoginModal() {
        return new LoginModal(driver);
    }

    public AddressBookModal createAddressModal() {
        return new AddressBookModal(driver);
    }

    public CookiesModal createCookiesModal() {
        return new CookiesModal(driver);
    }

    public PersonalInfoModal createPersonalInfoModal() {
        return new PersonalInfoModal(driver);
    }

}
