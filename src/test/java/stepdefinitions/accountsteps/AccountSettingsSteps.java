package stepdefinitions.accountsteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import objectpage.nonpages.components.SidePanel;
import objectpage.pages.account.MyAccountPage;
import services.Helper;
import services.LoginService;

public class AccountSettingsSteps {
    private final Helper helper;
    private final SidePanel sidePanel;
    private final MyAccountPage myAccountPage;
    private final LoginService loginService;

    public AccountSettingsSteps(Helper helper, SidePanel sidePanel, MyAccountPage myAccountPage, LoginService loginService) {
        this.helper = helper;
        this.sidePanel = sidePanel;
        this.myAccountPage = myAccountPage;
        this.loginService = loginService;
    }

    @Given("I am logged in and on the main page")
    public void iAmLoggedInAndOnTheMainPage() {
        helper.checkWebPage("https://www.adidas.com/us");
        loginService.logIn();
    }

    @When("I navigate to the account settings page")
    public void iNavigateToTheAccountSettingsPage() {
        sidePanel.clickAccountLink();
        myAccountPage.clickOnAccountSection();
    }
}
