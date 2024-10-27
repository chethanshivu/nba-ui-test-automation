package org.derivedproduct1.com.pageobjects;


import lombok.Getter;
import org.automationutils.com.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    @Getter
    @FindBy(xpath = "//button[@type='button']//div/parent::div")
    private List<WebElement> slides;

    @Getter
    @FindBy(xpath="//*[@role='tabpanel']/parent::div")
    private WebElement slideTile;


}
