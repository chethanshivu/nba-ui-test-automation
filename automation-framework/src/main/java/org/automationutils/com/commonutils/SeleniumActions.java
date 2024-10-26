package org.automationutils.com.commonutils;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SeleniumActions {

    public void clickOn(WebElement element){
        if(isElementPresent(element)){
            element.click();
        }
    }

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
