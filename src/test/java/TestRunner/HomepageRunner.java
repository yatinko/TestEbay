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
	tags= "@Test",
	plugin= {
			"pretty",
			"html:target/CucmberTestReports/HTML-Reports",
			"json:target/CucmberTestReports/CucumberJsonReport.json",
		},
	monochrome=true
)

public class HomepageRunner extends AbstractTestNGCucumberTests {
	
}