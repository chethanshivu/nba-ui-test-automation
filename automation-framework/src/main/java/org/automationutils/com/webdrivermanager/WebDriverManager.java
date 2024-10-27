package org.automationutils.com.webdrivermanager;


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;


@Slf4j
public class WebDriverManager {

    private static WebDriver driver;

    /**
     *
     * create webdriver for given browser type
     *
     * @param browser
     * @param mode
     * @param pageLoadWait
     * @param implicitWait
     * @return
     */
    public static WebDriver getWebDriver(String browser, String mode, int pageLoadWait, int implicitWait) {
        if(driver==null){

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
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

    /**
     *
     * close the active browser
     */
    public static void closeWebDriver(){
        if(driver!=null){
            driver.close();
        }
    }

    /**
     *
     * close the webdriver and close all the instance
     */
    public static void quitWebDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}
