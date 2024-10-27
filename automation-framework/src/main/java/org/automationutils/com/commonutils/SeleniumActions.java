package org.automationutils.com.commonutils;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SeleniumActions {

    /**
     *
     * this method is used to click on any given element
     * it will check for the element presence then will do the operation
     *
     * @param element webelement of UI element
     */
    public void clickOn(WebElement element){
        if(isElementPresent(element)){
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
}
