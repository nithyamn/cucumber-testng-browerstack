package appium_ios;

import cucumber.api.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin = {"pretty"},
        features = "src/test/resources/appium_ios/iosFeature.feature",
        glue = "appium_ios")

public class RunCucumberIOSTest extends AbstractTestNGCucumberTests {
}
