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
    @FindBy(xpath = "//div[@class='sponsorBar TileHeroStories_tileHeroStoriesButtonBar__A3WSo']/parent::button")
    private List<WebElement> slides;

    @Getter
    @FindBy(css="div[class='TileHeroStories_tileHeroStoriesButtonTitle__8Xiey']")
    private List<WebElement> slideContent;


}
