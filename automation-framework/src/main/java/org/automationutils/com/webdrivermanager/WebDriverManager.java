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

    private static volatile WebDriverManager instance;
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private WebDriverManager(){}


    /**
     *
     * public static method to create singleton driver
     *
     * @param browser
     * @param mode
     * @param pageLoadWait
     * @param implicitWait
     * @return
     */
    public static WebDriverManager getInstance(String browser, String mode, int pageLoadWait, int implicitWait){
        if(instance==null){
            synchronized (WebDriverManager.class){
                if(instance==null){
                    instance = new WebDriverManager();
                }
            }
        }

        if(tlDriver.get()==null){
            instance.initDriver(browser, mode, pageLoadWait, implicitWait);
        }
        return instance;
    }


    /**
     *
     * It returns the driver
     *
     * @return
     */
    public static WebDriver getDriver(){
        return tlDriver.get();
    }

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
    private void initDriver(String browser, String mode, int pageLoadWait, int implicitWait) {

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(mode);
                tlDriver.set(new ChromeDriver(chromeOptions));
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments(mode);
                tlDriver.set(new EdgeDriver(edgeOptions));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(mode);
                tlDriver.set(new FirefoxDriver(firefoxOptions));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        tlDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadWait));
        tlDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        tlDriver.get().manage().window().maximize();
    }

    /**
     *
     * close the active browser
     */
    public static void closeWebDriver(){
        if(tlDriver.get()!=null){
            tlDriver.get().close();
        }
    }

    /**
     *
     * close the webdriver and close all the instance
     */
    public static void quitWebDriver(){
        if(tlDriver.get()!=null){
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
