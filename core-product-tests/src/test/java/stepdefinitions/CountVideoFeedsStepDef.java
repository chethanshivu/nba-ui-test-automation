package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.coreproduct.com.pageobjects.HomePage;
import org.coreproduct.com.pageobjects.NewsAndFeatures;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

@Slf4j
public class CountVideoFeedsStepDef {

    WebDriver driver = WebDriverManager.getDriver();
    HomePage homePage = new HomePage(driver);
    NewsAndFeatures newsAndFeatures = new NewsAndFeatures(driver);

    @Given("User is on Website {string}")
    public void userIsOnTheWebsite(String url) {
        driver.get(url);
        log.info(driver.getTitle());
        if (!homePage.getCloseAdButton().isEmpty()) {
            homePage.clickElement(driver,"close ads");
        }
    }

    @And("User hover over on the menu {string} item")
    public void userHoverOverOnTheMenuItem(String element) {
        driver.navigate().refresh();
        homePage.hoverElement(driver,"menu item");
    }

    @And("User clicks on {string}")
    public void userClicksOn(String arg0){
        homePage.clickElement(driver,"news and features");
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
