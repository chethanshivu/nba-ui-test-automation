package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.derivedproduct1.com.pageobjects.HomePage;
import org.derivedproduct1.com.testutils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class SlidesDetailStepDef {
    String browserType = ConfigReader.getBrowserType();
    WebDriverManager webDriverManager = new WebDriverManager();
    WebDriver driver = webDriverManager.getWebDriver(browserType);
    HomePage homePage=new HomePage(driver);

    @Given("User is on the Website {string}")
    public void userIsOnTheWebsite(String url) {
        driver.get(url);
        log.info(driver.getTitle());
    }

    @Then("Slides should be present below tickets menu")
    public void slidesShouldBePresentBelowTicketsMenu() {

    }

    @And("Fetch the number of slides in home page")
    public void fetchTheNumberOfSlidesInHomePage() {
        List<WebElement> slides = homePage.getSlides();

        log.info("Count : "+slides.size());

        WebElement slideTile = homePage.getSlideTile();

        List<WebElement> slideTileElements = slideTile.findElements(By.cssSelector("button[type='button']>div"));

        for(WebElement element : slideTileElements){
            log.info("Title : "+element.getText());
        }
    }
}
