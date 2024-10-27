package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.messages.types.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.coreproduct.com.pageobjects.HomePage;
import org.coreproduct.com.pageobjects.ShopPage;
import org.coreproduct.com.testutils.ConfigReader;
import org.coreproduct.com.testutils.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;

@Slf4j
public class MensJacketDetailsStepDef {

    String browserType = ConfigReader.getBrowserType();
    String browserMode = ConfigReader.getBrowserMode();
    int pageLoadWait = ConfigReader.getPageLoadWait();
    int implicitWait = ConfigReader.getImplicitWait();

    WebDriver driver = WebDriverManager.getWebDriver(browserType, browserMode, pageLoadWait, implicitWait);
    HomePage homePage = new HomePage(driver);
    ShopPage shopPage = new ShopPage(driver);
    FileUtils fileUtils=new FileUtils();

    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("User is on the Website {string}")
    public void userIsOnTheWebsite(String url) {
        driver.get(url);
        log.info(driver.getTitle());
        if (!homePage.getCloseAdButton().isEmpty()) {
            homePage.clickElement("close ads");
        }
    }

    @And("User hover on the Shop icon")
    public void userHoverOnTheShopIcon() {

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(homePage.getShopIcon()));
        homePage.hoverElement(driver,"shop");
    }

    @And("User selects the mens icon")
    public void userSelectsTheMensIcon() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(homePage.getMenCollection()));
        homePage.clickElement("men's collection");
        Set<String> windowHandles = driver.getWindowHandles();
        for(String window : windowHandles){
            if(!driver.getTitle().equals("Golden State Warriors")) {
                driver.switchTo().window(window);
            }
        }
    }

    @Then("User should be navigated to the {string} page")
    public void userShouldBeNavigatedToTheShopPage(String title) {
        log.info(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains(title));
    }

    @And("User selects the jackets under filter")
    public void userSelectsTheJacketsUnderFilter() {
        shopPage.clickElement("jackets");
    }

    @And("Fetch every jackets information and store to a text file")
    public void fetchEveryJacketsBelowInformationAndStoreToATextFile() {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Mens-Jacket-Details.txt"), StandardCharsets.UTF_8));
            List<WebElement> productDetailSection = shopPage.getProductDetailSection();

            List<WebElement> pages = shopPage.getPages();
            WebElement topSellerMessage = shopPage.getProductTopSellerMessage();

            for (WebElement product : productDetailSection) {
                fileUtils.writeToTextFile(writer, product, topSellerMessage);
            }

           int numberOfPages= Integer.parseInt(homePage.getLastPageNumber().getText());
            for(int i=2;i<=numberOfPages;i++){
                driver.findElement(By.cssSelector("div[class='pagination-component'] a[aria-label='page "+i+"']")).click();
                for (WebElement product : productDetailSection) {
                    fileUtils.writeToTextFile(writer, product, topSellerMessage);
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       fileUtils.attachFile(scenario,"/Users/shivaprasadks/IdeaProjects/nba-ui-test-framework/core-product-tests/Mens-Jacket-Details.txt");
    }
}
