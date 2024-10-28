package testconfig;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.derivedproduct1.com.testutils.ConfigReader;

@Slf4j
public class Hooks {


    @BeforeAll
    public static void setup(){

    }

    @Before
    public void preCondition(){
        WebDriverManager.getInstance(ConfigReader.getBrowserType(),
                ConfigReader.getBrowserMode(),
                ConfigReader.getPageLoadWait(),
                ConfigReader.getImplicitWait());
    }

    @AfterAll
    public static void tearDown(){
        log.info("------------Closing the browser-----------");
        WebDriverManager.closeWebDriver();
        WebDriverManager.quitWebDriver();
    }
}
