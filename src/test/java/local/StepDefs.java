package local;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class StepDefs {
    public static final String USERNAME = "BROWSERSTACK_USERNAME";
    public static final String AUTOMATE_KEY = "BROWSERSTACK_ACCESS_KEY";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    DesiredCapabilities caps;
    WebDriver driver;
    WebElement element;

    @Given("Open Browser")
    public void open_Browser() throws MalformedURLException {
        caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "75.0");
        caps.setCapability("build", "cucumber-java-testng-browserstack");
        caps.setCapability("name", "local_test");
        caps.setCapability("browserstack.local", "true");
//        caps.setCapability("browserstack.localIdentifier", "cucumbertest");
        driver = new RemoteWebDriver(new URL(URL), caps);
    }

    @When("Go to localhost")
    public void go_to_localhost() {
        driver.get("http://localhost:45691/check");
    }

    @Then("Retrieve Title if Up and Running")
    public void retrieve_Title_if_Up_and_Running() {
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
