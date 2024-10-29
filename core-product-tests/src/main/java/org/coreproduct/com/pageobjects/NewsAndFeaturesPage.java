package org.coreproduct.com.pageobjects;

import lombok.Getter;
import org.automationutils.com.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewsAndFeaturesPage extends BasePage {

    public NewsAndFeaturesPage(WebDriver driver){
        super(driver);
    }

    @Getter
    @FindBy(xpath = "//a[contains(@href,'video') and @class='TileArticle_tileLink__dBHYH']")
    private List<WebElement> videoFeeds;

    @Getter
    @FindBy(xpath = "//a[contains(@class,'absolute inset')]")
    private WebElement featuredVideo;

    @Getter
    @FindBy(xpath = "//*[@data-testid='post-duration']/parent::div")
    private List<WebElement> lastUpdatedDuration;

}
