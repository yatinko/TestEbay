/**
 * 
 */
package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * @author yatinko
 *
 */

@CucumberOptions(
	features="src/test/resources/Features/HomePage.feature",
	glue="StepDefinitions",
	tags= "@Test and not @Ignore",
	plugin= {
			"pretty",
			"html:target/cucumber-reports/cucumber-pretty.html",
			"testng:target/cucumber-reports/CucumberTestReport.xml"
		},
	monochrome=true
)

public class HomePageRunner extends AbstractTestNGCucumberTests {
	
}
