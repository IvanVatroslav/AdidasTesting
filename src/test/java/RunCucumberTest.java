import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"objectpage", "stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false,
        tags = "" +
          //     "@MainNavigationMenu"+
           //   " or @MenSectionSubcategories"+
              "@SearchFunctionality"+
            " or @SearchFunctionality_invalid"
         //  " or @AddressBook"
     //   " or @RandomBirthdate"+
  //   " or @EditPreferences"
//" or @EditPersonalInfo"
)
public class RunCucumberTest {

}