package org.automationutils.com.commonutils;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumActions {

    /**
     *
     * this method is used to click on any given element
     * it will check for the element presence then will do the operation
     *
     * @param element webelement of UI element
     */
    public void clickOn(WebDriver driver, WebElement element){
        if(isElementPresent(element)){
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

            element.click();
        }
    }

    /**
     * This method checks the presence of element
     * throws the exception if element is not present
     *
     * @param element
     * @return
     */
    public boolean isElementPresent(WebElement element){
        try{
            element.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e){
           return false;
        }
    }

    /**
     * Hover on given element
     *
     * @param driver
     * @param element
     */
    public void mouseHover(WebDriver driver, WebElement element){

        Actions action = new Actions(driver);

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        action.moveToElement(element).perform();
    }
}
