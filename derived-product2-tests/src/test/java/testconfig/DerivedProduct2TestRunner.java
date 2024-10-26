package testconfig;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features",
                 glue = {"stepdefinitions","testconfig"},
                 plugin = {"pretty",
                 "html:target/cucumber-reports/cucumber.html",
                 "json:target/cucumber-reports/cucumber.json",
                 "testng:target/cucumber-reports/cucumber.xml"})
public class DerivedProduct2TestRunner extends AbstractTestNGCucumberTests {
}
