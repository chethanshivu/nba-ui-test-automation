package stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.commonutils.ScenarioContext;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.coreproduct.com.pageobjects.HomePage;
import org.coreproduct.com.pageobjects.ShopPage;
import org.coreproduct.com.testutils.FileUtils;
import org.coreproduct.com.testutils.TestConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@Slf4j
public class MensJacketDetailsStepDef {

    WebDriver driver = WebDriverManager.getDriver();

    HomePage homePage = new HomePage(driver);
    ShopPage shopPage = new ShopPage(driver);
    FileUtils fileUtils=new FileUtils();

    @Given("User is on the Website {string}")
    public void userIsOnTheWebsite(String url) {
        driver.get(url);
        log.info(driver.getTitle());
        if (!homePage.getCloseAdButton().isEmpty()) {
            homePage.clickElement(driver,"close ads");
        }
    }

    @And("User hover on the Shop icon")
    public void userHoverOnTheShopIcon() {
        for(int i=0;i<3;i++) {
            homePage.hoverElement(driver, "shop");
        }
    }

    @And("User selects the mens icon")
    public void userSelectsTheMensIcon() {

        homePage.clickElement(driver,"men's collection");

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
        shopPage.clickElement(driver,"jackets");
    }

    @And("Fetch every jackets information and store to a text file")
    public void fetchEveryJacketsBelowInformationAndStoreToATextFile() {
        try {
            if(!Files.exists(Path.of(TestConstants.JACKET_DETAIL_FILE_PATH))){
                Files.createDirectory(Path.of("test-output"));
            }

            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TestConstants.JACKET_DETAIL_FILE_PATH), StandardCharsets.UTF_8));

            List<WebElement> productDetailSection = shopPage.getProductDetailSection();

            for (WebElement product : productDetailSection) {
                fileUtils.writeToTextFile(writer, product, driver);
            }

           int numberOfPages= Integer.parseInt(homePage.getLastPageNumber().getText());
            for(int i=2;i<=numberOfPages;i++){
                driver.findElement(By.cssSelector("div[class='pagination-component'] a[aria-label='page "+i+"']")).click();
                for (WebElement product : productDetailSection) {
                    fileUtils.writeToTextFile(writer, product, driver);
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] fileContent = null;
        try {
            fileContent = Files.readAllBytes(Paths.get(TestConstants.JACKET_DETAIL_FILE_PATH));
        } catch (IOException e) {
            ScenarioContext.logInfo("Failed to attach file: " + e.getMessage());
            throw new RuntimeException(e);
        }
        String fileName = new File(TestConstants.JACKET_DETAIL_FILE_PATH).getName();
       ScenarioContext.attachFile(fileName, fileContent);
    }
}
