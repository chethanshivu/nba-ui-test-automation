package org.coreproduct.com.pageobjects;


import lombok.Getter;
import org.automationutils.com.commonutils.SeleniumActions;
import org.automationutils.com.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

    SeleniumActions seleniumActions = new SeleniumActions();

    public HomePage(WebDriver  driver){
        super(driver);
    }

    @Getter
    @FindBy(css="[id='nba-nav']>div:nth-of-type(2)>nav>div>nav>ul>[data-testid='nav-item-https://shop.warriors.com/']")
    private WebElement shopIcon;

    @Getter
    @FindBy(id="onetrust-accept-btn-handler")
    private List<WebElement> cookieAccept;

    @Getter
    @FindBy(xpath = "//*[text()='x']")
    private List<WebElement> closeAdButton;

    @Getter
    @FindBy(css = "li[role='menuitem'] li[role='menuitem'] a[title*='Men']")
    private WebElement menCollection;

    @Getter
    @FindBy(css = "[data-testid='nav-item-#']")
    private WebElement menuItem;

    @Getter
    @FindBy(css = "li[role='menuitem'] li[role='menuitem'] a[title='News & Features']")
    private WebElement newsAndFeatures;

    @Getter
    @FindBy(xpath = "(//*[@class='pagination-list-container']/li[@class='show-for-large'])[last()]")
    private WebElement lastPageNumber;

    /**
     *
     * click on a given element
     *
     * @param element
     */
    public void clickElement( WebDriver driver,String element){
        switch (element.toLowerCase()){
            case "shop": seleniumActions.clickOn(driver, shopIcon);
               break;
            case "accept cookies": seleniumActions.clickOn(driver, cookieAccept.getFirst());
               break;
            case "close ads": seleniumActions.clickOn(driver,closeAdButton.getFirst());
                break;
            case "news and features": seleniumActions.clickOn(driver,newsAndFeatures);
                break;
            case "men's collection": seleniumActions.clickOn(driver,menCollection);
                break;

            default: throw new IllegalArgumentException("Element is not matching");
        }
    }

    /**
     * Hover on a given element
     *
     * @param driver
     * @param element
     */
    public void hoverElement(WebDriver driver, String element){
        switch (element.toLowerCase()){
            case "menu item" : seleniumActions.mouseHover(driver, menuItem);
            break;
            case "shop" : seleniumActions.mouseHover(driver, shopIcon);
                break;
            default: throw new IllegalArgumentException("Element is not matching");
        }

    }
}
