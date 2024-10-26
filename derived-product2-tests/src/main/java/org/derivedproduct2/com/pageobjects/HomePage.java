package org.derivedproduct2.com.pageobjects;

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
    @FindBy(css = "[data-testid='footer']")
    private WebElement footer;

    @Getter
    @FindBy(xpath = "//*[@data-testid='footer']//a")
    private List<WebElement> footerLinks;

}
