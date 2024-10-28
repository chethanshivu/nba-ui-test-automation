package org.automationutils.com.commonutils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    /**
     * attach the screenshot given cucumber step
     *
     * @param driver
     * @param scenario
     */
    public void attachScreenshot(WebDriver driver, Scenario scenario){
        byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(bytes,"image/png", scenario.getName());
    }
}
