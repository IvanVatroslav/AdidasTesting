package objectpage.pages.search;

import objectpage.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ShoppingBagPage extends BasePage<ShoppingBagPage> {
    public ShoppingBagPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected WebElement getUniqueElement() {
        return null;
    }

    @Override
    protected ShoppingBagPage openPage() {
        return null;
    }
}
