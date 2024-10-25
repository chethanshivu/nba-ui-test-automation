package testconfig;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features",
                 glue = {"stepdefinitions","testconfig"},
        plugin = {"pretty", "html:target/cucumber-reports/cucumber.html"})
public class CoreProductTestRunner extends AbstractTestNGCucumberTests {
}
