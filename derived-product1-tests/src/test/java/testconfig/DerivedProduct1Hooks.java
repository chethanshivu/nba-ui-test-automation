package testconfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.commonutils.ScenarioContext;
import org.automationutils.com.commonutils.ScreenshotUtils;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.derivedproduct1.com.testutils.ConfigReader;

@Slf4j
public class DerivedProduct1Hooks {

    @Before
    public void preCondition(Scenario scenario){
        ScenarioContext.setCurrentScenario(scenario);
        log.info("------------Initializing the browser-----------");
        WebDriverManager.getInstance(ConfigReader.getBrowserType(),
                ConfigReader.getBrowserMode(),
                ConfigReader.getPageLoadWait(),
                ConfigReader.getImplicitWait());
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            new ScreenshotUtils().attachScreenshot(WebDriverManager.getDriver(),scenario);
        }

        log.info("------------Closing the browser-----------");
        WebDriverManager.closeWebDriver();
        WebDriverManager.quitWebDriver();
    }
}
