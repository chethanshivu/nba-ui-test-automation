package testconfig;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;

@Slf4j
public class Hooks {


    @BeforeAll
    public static void setup(){

    }

    @AfterAll
    public static void tearDown(){
        log.info("------------Closing the browser-----------");
        WebDriverManager.closeWebDriver();
        WebDriverManager.quitWebDriver();
    }
}
