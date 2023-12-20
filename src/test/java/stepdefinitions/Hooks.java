package stepdefinitions;

import ObjectPage.Base;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks {

    @AfterStep
    public void addScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot and get it as a byte array
            byte[] screenshot = Base.getScreenshot();
            if (screenshot != null) {
                // Attach it to the report
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        }
    }
}
