package objectpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



}
