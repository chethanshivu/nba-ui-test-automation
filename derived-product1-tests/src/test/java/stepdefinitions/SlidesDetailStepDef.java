package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.derivedproduct1.com.pageobjects.HomePage;
import org.derivedproduct1.com.testutils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SlidesDetailStepDef {

    WebDriver driver = WebDriverManager.getDriver();
    HomePage homePage=new HomePage(driver);

    @Given("User is on the Website {string}")
    public void userIsOnTheWebsite(String url) {
        driver.get(url);
        log.info(driver.getTitle());
    }

    @Then("Slides should be present below tickets menu")
    public void slidesShouldBePresentBelowTicketsMenu() {

    }

    @And("Number of slides should be {int} in home page")
    public void fetchTheNumberOfSlidesInHomePage(int numberOfSlides) {
        List<WebElement> slides = homePage.getSlides();
        int actualCount = slides.size();
        log.info("Number of slides in the home page : "+actualCount);
        Assert.assertEquals(actualCount,numberOfSlides);
    }

    @And("Slides title should have below content")
    public void slidesTitleShouldHaveBelowContent(DataTable dataTable) {
        List<String> expectedData = dataTable.asList();
        List<String> actualData =new ArrayList<>();

        List<WebElement> slides = homePage.getSlideContent();

        JavascriptExecutor js =(JavascriptExecutor) driver;

        for(WebElement element : slides){
            String actualContent = (String) js.executeScript("return arguments[0].textContent;", element);
            if(!actualContent.isEmpty()) {
                actualData.add(actualContent);
            }
            log.info("Actual data : "+actualContent);
        }
        Assert.assertTrue((expectedData.containsAll(actualData)));
    }@And("Slides duration should be as below")
    public void slidesDurationShouldBeAsBelow() throws InterruptedException {
//        while(true) {
//            log.info("link : " + driver.findElement(By.cssSelector("[class='TileHero_tileLink__VTMPI']")).getAttribute("href"));
//            log.info("time : " + LocalTime.now());
//            Thread.sleep(10000);
//        }

        String previousHref = "";
        Instant startTime = Instant.now();

        WebElement slideElement = driver.findElement(By.cssSelector("[class='TileHero_tileLink__VTMPI']"));
        String currentHref = slideElement.getAttribute("href");

        while (true) {
            if (!currentHref.equals(driver.findElement(By.cssSelector("[class='TileHero_tileLink__VTMPI']")).getAttribute("href"))) {
                log.info("time : " + Instant.now());
                log.info("link : " + driver.findElement(By.cssSelector("[class='TileHero_tileLink__VTMPI']")).getAttribute("href"));
                currentHref = driver.findElement(By.cssSelector("[class='TileHero_tileLink__VTMPI']")).getAttribute("href");
            }
            Thread.sleep(10000);
        }

//        while (true) {
//
//            WebElement slideElement = driver.findElement(By.cssSelector("[class='TileHero_tileLink__VTMPI']"));
//            String currentHref = slideElement.getAttribute("href");
//
//            // Check if href has changed
//            if (!currentHref.equals(previousHref) && !previousHref.isEmpty()) {
//                Instant endTime = Instant.now();
//                Duration slideDuration = Duration.between(startTime, endTime);
//                System.out.println("Duration for slide: " + slideDuration.toMillis() + " ms");
//
//                startTime = endTime;
//                previousHref = currentHref;
//            } else if (previousHref.isEmpty()) {
//                previousHref = currentHref;
//            }
//    }
    }
}
