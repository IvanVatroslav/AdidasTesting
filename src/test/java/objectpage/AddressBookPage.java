package objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressBookPage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    public AddressBookPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


}