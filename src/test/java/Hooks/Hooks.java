package Hooks;
 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.DriverFactory;
 
public class Hooks {

 
	
	@Before
	public void setUp() {
	    try {
	        Thread.sleep(3000); // Pause before scenario starts
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    DriverFactory.getDriver();
	    System.out.println("Browser launched before scenario");
	}

	 @After
	 public void tearDown(Scenario scenario) {
	     WebDriver driver = DriverFactory.getDriver();

	     if (scenario.isFailed()) {
	         byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	         scenario.attach(screenshot, "image/png", "Failed Screenshot"); 
	     }

	     // Add delay after scenario (e.g., 3 seconds)
	     try {
	         Thread.sleep(3000); // 3000 milliseconds = 3 seconds
	     } catch (InterruptedException e) {
	         e.printStackTrace();
	     }

	     // Optionally quit the browser if you want fresh browser per scenario
	     // DriverFactory.quitDriver();

	     System.out.println("Browser closed after the scenario");
	 }



 
}