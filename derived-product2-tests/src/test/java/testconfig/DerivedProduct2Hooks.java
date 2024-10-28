package testconfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.commonutils.ScreenshotUtils;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.derivedproduct2.com.testutils.ConfigReader;

@Slf4j
public class DerivedProduct2Hooks {

    @Before
    public void preCondition(){
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
