package appium_ios;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.function.Function;

public class StepDefsIOS {

    public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME"); //OR String USERNAME = "<browserstack-username>"
    public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");//OR String AUTOMATE_KEY = "<browserstack-accesskey>"
    public String APP_ID = "";
    DesiredCapabilities caps;
    JavascriptExecutor jse;
    public IOSDriver<IOSElement> driver;
 
    @Given("Open Application")
    public void open_Application() throws MalformedURLException {
        caps = new DesiredCapabilities();
        caps.setCapability("device", "iPhone XS");
        caps.setCapability("os_version", "12");
        caps.setCapability("build", "cucumber-java-testng-browserstack");
        caps.setCapability("name", "single_ios_test");
        caps.setCapability("app", APP_ID);
        driver = new IOSDriver<IOSElement>(new URL("http://"+USERNAME+":"+AUTOMATE_KEY+"@hub-cloud.browserstack.com/wd/hub"), caps);
        jse = (JavascriptExecutor)driver;
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
        if(textOutput.getText().equals("hello@browserstack.com")){
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Successful!\"}}");
        }else{
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \"Unsuccessful!\"}}");
        }
    }

    @Then("Close application")
    public void close_Application(){
        driver.quit();
    }

}
