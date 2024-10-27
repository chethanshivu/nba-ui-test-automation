package org.automationutils.com.webdrivermanager;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
public class WebDriverManager {



    private static WebDriver driver;

    public static WebDriver getWebDriver(String browser, String mode, int pageLoadWait, int implicitWait) {
        if(driver==null){

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = getChromeOptions();
                chromeOptions.addArguments(mode);
                driver= new ChromeDriver(chromeOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments(mode);
                driver = new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(mode);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadWait));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        return driver;
        }
        return driver;
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Add Chrome-specific preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.cookies", 1);
        prefs.put("profile.cookie_controls_mode", 0);
        prefs.put("profile.block_third_party_cookies", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        options.setExperimentalOption("prefs", prefs);

        options.addArguments(
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-infobars",
                "--start-maximized"
        );

        return options;
    }

    public static void closeWebDriver(){
        if(driver!=null){
            driver.close();
        }
    }

    public static void quitWebDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
