package testconfig;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = "src/test/resources/features",
                 glue = {"stepdefinitions","testconfig"},
                 tags = "@Regression",
                 plugin = {"pretty",
                 "html:target/cucumber-reports/cucumber.html",
                 "json:target/cucumber-reports/cucumber.json",
                 "testng:target/cucumber-reports/cucumber.xml"})
public class DerivedProduct2TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
