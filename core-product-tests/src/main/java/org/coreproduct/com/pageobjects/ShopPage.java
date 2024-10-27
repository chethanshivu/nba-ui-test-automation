package org.coreproduct.com.pageobjects;

import lombok.Getter;
import org.automationutils.com.commonutils.SeleniumActions;
import org.automationutils.com.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class ShopPage extends BasePage {

    SeleniumActions seleniumActions = new SeleniumActions();
    public ShopPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "[data-trk-id*='side-nav-jackets-all-departments-boxes']")
    private WebElement jackets;

    @FindBy(css = "a[data-trk-id='topnav-group-ga-1_men']")
    private WebElement mensCollection;

    @FindBy(css = "[data-talos='srpProductPrice']")
    private WebElement productPrice;

    @FindBy(css = "[data-talos='linkSearchResult']")
    private WebElement productTitle;

    @Getter
    @FindBy(css = ".product-vibrancy.top-seller-vibrancy")
    private WebElement productTopSellerMessage;

    @Getter
    @FindBy(xpath = "//*[@data-talos='srpProductPrice']/parent::div/parent::div")
    private List<WebElement> productDetailSection;

    @Getter
    @FindBy(css= "a[data-talos='linkSearchResultsPage']")
    private List<WebElement> pages;

    /**
     *
     * Click on a given element
     *
     * @param element
     */
    public void clickElement(String element){
        switch (element.toLowerCase()){
            case "jackets": seleniumActions.clickOn(jackets);
                break;
            case "mens": seleniumActions.clickOn(mensCollection);
                break;
            default: throw new IllegalArgumentException("Element is not matching");
        }
    }

}
