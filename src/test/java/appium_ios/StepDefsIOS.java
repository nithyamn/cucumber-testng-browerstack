package appium_ios;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class StepDefsIOS {

    public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME"); //OR String USERNAME = "<browserstack-username>"
    public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");//OR String AUTOMATE_KEY = "<browserstack-accesskey>"
<<<<<<< HEAD
=======
    
>>>>>>> cfa7c682fef3599d52318d809860cf2f5600df5a
    DesiredCapabilities caps;
    public IOSDriver<IOSElement> driver;
 
    @Given("Open Application")
    public void open_Application() throws MalformedURLException {
        caps = new DesiredCapabilities();
        caps.setCapability("device", "iPhone XS");
        caps.setCapability("os_version", "12");
        caps.setCapability("build", "cucumber-java-testng-browserstack");
        caps.setCapability("name", "single_ios_test");
<<<<<<< HEAD
        caps.setCapability("app", "bs://01c84515047e72f00ce2452455b4e64aff792e02");
=======
        caps.setCapability("app", "bs://<app-hashid>");
>>>>>>> cfa7c682fef3599d52318d809860cf2f5600df5a
        driver = new IOSDriver<IOSElement>(new URL("http://"+USERNAME+":"+AUTOMATE_KEY+"@hub-cloud.browserstack.com/wd/hub"), caps);
    }

    @When("Handle Text box")
    public void handle_Text_Box() throws InterruptedException {
        IOSElement textButton = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Button")));
        textButton.click();
        IOSElement textInput = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Input")));
        textInput.sendKeys("hello@browserstack.com"+"\n");

        Thread.sleep(5000);

        IOSElement textOutput = (IOSElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Text Output")));

        Assert.assertEquals(textOutput.getText(),"hello@browserstack.com");
    }

    @Then("Close application")
    public void close_Application(){
        driver.quit();
    }

}
