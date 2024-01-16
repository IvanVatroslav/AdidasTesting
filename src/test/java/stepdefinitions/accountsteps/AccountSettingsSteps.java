package stepdefinitions.accountsteps;

import io.cucumber.java.en.When;
import objectpage.nonpages.components.SidePanel;
import objectpage.pages.account.MyAccountPage;
import org.openqa.selenium.WebDriver;
import stepdefinitions.Hooks;

public class AccountSettingsSteps {
    private final SidePanel sidePanel;
    private final MyAccountPage myAccountPage;
    private WebDriver driver;

    public AccountSettingsSteps() {
        this.driver = Hooks.driver.get();
        this.sidePanel = new SidePanel(driver);
        this.myAccountPage = new MyAccountPage(driver);
    }



    @When("the user navigates to the account settings page")
    public void iNavigateToTheAccountSettingsPage() {
        sidePanel.clickAccountLink();
        myAccountPage.clickOnAccountSection();
    }
}
