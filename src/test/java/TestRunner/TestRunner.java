package TestRunner;
 
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
 
@CucumberOptions(
	    features = "src/test/resources/Features/SearchFilterPage.feature",
	    glue = {"StepDefinitions","Hooks"},
//	    tags="@sanity",
	    plugin = {
	        "pretty",
	        "html:target/cucumber-html-report",
	        "json:target/cucumber.json",
	        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"  // ✅ correct extent adapter
	    }
	)
 
public class TestRunner extends AbstractTestNGCucumberTests {
 
}
 