package single;

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

    public static final String USERNAME = "<browserstack-username>";
    public static final String AUTOMATE_KEY = "<browserstack-access-key>";
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
        caps.setCapability("name", "single_test");
        driver = new RemoteWebDriver(new URL(URL), caps);
    }

    @When("Search for BrowserStack")
    public void search_for_BrowserStack(){
        driver.get("http://www.google.com");
        element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
    }

    @Then("Check page title")
    public void check_page_title(){
        System.out.println(driver.getTitle());
        driver.quit();
    }

}
