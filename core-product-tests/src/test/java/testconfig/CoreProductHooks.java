package testconfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.coreproduct.com.testutils.ConfigReader;

@Slf4j
public class CoreProductHooks {

    @Before
    public void preCondition(){
        log.info("------------Initializing the browser-----------");
        WebDriverManager.getInstance(ConfigReader.getBrowserType(),
                                     ConfigReader.getBrowserMode(),
                                     ConfigReader.getPageLoadWait(),
                                     ConfigReader.getImplicitWait());
    }

    @After
    public void tearDown(){
        log.info("------------Closing the browser-----------");
        WebDriverManager.closeWebDriver();
        WebDriverManager.quitWebDriver();
    }
}
