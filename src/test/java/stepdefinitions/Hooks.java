package stepdefinitions;

import ObjectPage.Base;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks {

    @AfterStep
    public void addScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = Base.getScreenshot();
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        }
    }
}
