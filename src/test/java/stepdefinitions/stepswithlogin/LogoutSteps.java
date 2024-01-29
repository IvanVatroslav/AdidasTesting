package stepdefinitions.stepswithlogin;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectpage.nonpages.components.Header;
import objectpage.nonpages.components.SidePanel;
import objectpage.pages.account.AccountLoginPage;
import objectpage.pages.account.MyAccountPage;
import objectpage.pages.account.ProfilePage;
import org.openqa.selenium.WebDriver;
import stepdefinitions.Hooks;

import static org.junit.Assert.assertTrue;

public class LogoutSteps {
    private WebDriver driver;
    private Header header;
    private SidePanel sidePanel;
    private ProfilePage profilePage;
    private MyAccountPage myAccountPage;

    private AccountLoginPage loginPage;

    public LogoutSteps() {
        this.driver = Hooks.driver.get();
        this.header = new Header(driver);
        this.sidePanel = new SidePanel(driver);
        this.profilePage = new ProfilePage(driver);
        this.myAccountPage = new MyAccountPage(driver);
        this.loginPage = new AccountLoginPage(driver);
    }

    @When("the user logs out")
    public void theUserLogsOut() {
        header.getAccountPortalTrigger().click();
        sidePanel.clickAccountLink();
        myAccountPage.clickOnAccountSection();

        profilePage.clickLogOutButton();
    }


    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLogin() {
        assertTrue("Login page did not open", loginPage.checkWebPage("account-login"));
    }


}
