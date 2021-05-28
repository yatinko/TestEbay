/**
 * 
 */
package TestRunner;

import org.testng.annotations.AfterClass;

import TestPages.BaseClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;

/**
 * @author yatinko
 *
 */

@CucumberOptions(
	features="src/test/resources/Features/HomePage.feature",
	glue="StepDefinitions",
	plugin= {
			"pretty",
			"html:target/cucumber-reports/cucumber-pretty.html",
			"testng:target/cucumber-reports/CucumberTestReport.xml"
		},
	monochrome=true,
	dryRun=false
)

public class HomePageRunner extends AbstractTestNGCucumberTests {
	private TestNGCucumberRunner testNGCucumberRunner;
	
	@AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (testNGCucumberRunner == null) {
            return;
        }
        BaseClass.driver.quit();
        testNGCucumberRunner.finish();
    }
}
