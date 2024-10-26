package org.coreproduct.com.pageobjects;


import org.automationutils.com.commonutils.SeleniumActions;
import org.automationutils.com.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    SeleniumActions seleniumActions = new SeleniumActions();

    public HomePage(WebDriver  driver){
        super(driver);
    }

   @FindBy(css="[id='nba-nav']>div:nth-of-type(2)>nav>div>nav>ul>[data-testid='nav-item-https://shop.warriors.com/']")
    private WebElement shopIcon;

    @FindBy(id="onetrust-accept-btn-handler")
    private WebElement cookieAccept;

    @FindBy(xpath = "//*[text()='x']")
    private WebElement closeAddButton;

    @FindBy(css = "li[role='menuitem'] li[role='menuitem'] a[title*='Men']")
    private WebElement menCollection;

    public void clickElement(String element){
        switch (element.toLowerCase()){
            case "shop": seleniumActions.clickOn(shopIcon);
            break;
            case "accept cookie": seleniumActions.clickOn(cookieAccept);
            break;
            case "close adds": seleniumActions.clickOn(closeAddButton);
                break;
            case "men's collection": seleniumActions.clickOn(menCollection);
                break;
            default: throw new IllegalArgumentException("Element is not matching");
        }

    }

    public void hoverElement(WebDriver driver){
        Actions action = new Actions(driver);

        action.moveToElement(shopIcon).build().perform();
    }
}
