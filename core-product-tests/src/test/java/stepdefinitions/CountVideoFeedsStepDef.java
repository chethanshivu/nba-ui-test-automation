package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.coreproduct.com.pageobjects.HomePage;
import org.coreproduct.com.pageobjects.NewsAndFeatures;
import org.coreproduct.com.testutils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Slf4j
public class CountVideoFeedsStepDef {
    String browserType = ConfigReader.getBrowserType();
    String browserMode = ConfigReader.getBrowserMode();
    int pageLoadWait = ConfigReader.getPageLoadWait();
    int implicitWait = ConfigReader.getImplicitWait();

    WebDriver driver = WebDriverManager.getWebDriver(browserType, browserMode, pageLoadWait,implicitWait);
    HomePage homePage = new HomePage(driver);
    NewsAndFeatures newsAndFeatures = new NewsAndFeatures(driver);

    @Given("User is on Website {string}")
    public void userIsOnTheWebsite(String url) {
        driver.get(url);
        log.info(driver.getTitle());
        if (!homePage.getCloseAdButton().isEmpty()) {
            homePage.clickElement("close ads");
        }
    }

    @And("User hover over on the menu {string} item")
    public void userHoverOverOnTheMenuItem(String arg0) {
      homePage.hoverElement(driver,"menu item");
    }

    @And("User clicks on {string}")
    public void userClicksOn(String arg0) throws InterruptedException{
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(homePage.getNewsAndFeatures()));
        homePage.clickElement("news and features");
    }

    @When("Fetch the total number of video feeds")
    public void fetchTheTotalNumberOfVideoFeeds() {
        int totalVideoFeeds = newsAndFeatures.getVideoFeeds().size();
        if (newsAndFeatures.getFeaturedVideo().isDisplayed()) {
            totalVideoFeeds++;
        }

        log.info("Number of Video feeds in the news and features page are "+totalVideoFeeds);
    }

    @And("Fetch the total video feeds added {int} days back")
    public void fetchTheTotalVideoFeedsAddedDaysBack(int days) {

        List<WebElement> lastUpdatedDuration = newsAndFeatures.getLastUpdatedDuration();
        int count = 0;
        for (WebElement element : lastUpdatedDuration) {
            String text =  element.getText().split("\n")[0];
            if (text.contains("d")) {
                int duration = Integer.parseInt(text
                        .replaceAll("d", ""));

                if (duration >= days) {
                    count++;
                }
            }
        }
        log.info("Total number of video feeds posted 3 days back are " + count);
    }
}
