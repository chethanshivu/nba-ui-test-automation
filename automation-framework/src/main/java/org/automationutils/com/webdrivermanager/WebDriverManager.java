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

@Slf4j
@Data
public class WebDriverManager {

    private static WebDriver driver;

    public WebDriver getWebDriver(String browser){
        if(driver==null){
            driver = createWebDriver(browser);
            return driver;
        }
        return driver;
    }

    private WebDriver createWebDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return getChromeDriver();
            case "edge":
                return getEdgeDriver();
            case "firefox":
                return getFirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }


    private WebDriver getChromeDriver(){
        log.info("Chrome browser launched");

        ChromeOptions chromeOptions = new ChromeOptions();
       // chromeOptions.setCapability("javascriptEnabled",true);
      //ß  chromeOptions.setCapability("version","latest");

        WebDriver chromeDriver = new ChromeDriver(chromeOptions);

        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        chromeDriver.manage().window().maximize();

        return chromeDriver;
    }


    private WebDriver getEdgeDriver(){
        log.info("Edge browser launched");

        WebDriver edgeDriver = new EdgeDriver();

        edgeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        edgeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        edgeDriver.manage().window().maximize();

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("javascriptEnabled",true);
        edgeOptions.setCapability("version","latest");

        return edgeDriver;
    }


    private WebDriver getFirefoxDriver(){
        log.info("Firefox browser launched");

        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        firefoxDriver.manage().window().maximize();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("javascriptEnabled",true);
        firefoxOptions.setCapability("version","latest");

        return firefoxDriver;
    }

    public static void closeWebDriver(){
        if(driver!=null){
            driver.close();
        }
    }

    public static void quitWebDriver(){
        if(driver!=null){
            driver.quit();
        }
    }
}
