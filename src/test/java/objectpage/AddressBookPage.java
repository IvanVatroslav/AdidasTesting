package objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressBookPage {

    private final WebDriver driver;
    private final WebDriverWait wait;


    public AddressBookPage() {
        this.driver = Base.getDriver();
        this.wait = Base.getWait();
    }


}