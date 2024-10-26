package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.coreproduct.com.pageobjects.HomePage;
import org.coreproduct.com.pageobjects.ShopPage;
import org.coreproduct.com.testutils.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@Slf4j
public class MensJacketDetailsStepDef {

    WebDriverManager webDriverManager = new WebDriverManager();
    WebDriver driver = webDriverManager.getWebDriver("chrome");
    HomePage homePage = new HomePage(driver);
    ShopPage shopPage = new ShopPage(driver);
    FileUtils fileUtils=new FileUtils();

    @Given("User is on the Website {string}")
    public void userIsOnTheWebsite(String url)  {
        driver.get(url);
        log.info(driver.getTitle());
        homePage.clickElement("close adds");
    }

    @And("User clicks on the Shop icon")
    public void userClicksOnTheShopIcon() throws InterruptedException, IOException{
        homePage.clickElement("shop");

        Set<String> windowHandles = driver.getWindowHandles();
        for(String window : windowHandles){
            if(!driver.getTitle().equals("Golden State Warriors")) {
                driver.switchTo().window(window);
            }
        }
        Thread.sleep(3000);

        driver.navigate().refresh();

        shopPage.clickElement("mens");
        shopPage.clickElement("jackets");

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("myFile.txt"), StandardCharsets.UTF_8));
        List<WebElement> productDetailSection = shopPage.getProductDetailSection();

        List<WebElement> pages = shopPage.getPages();
        WebElement topSellerMessage = shopPage.getProductTopSellerMessage();

        for (WebElement product : productDetailSection) {
            fileUtils.writeToTextFile(writer, product, topSellerMessage);
        }

        for(int i=2;i<=5;i++){
            log.info("Entered the page loop --");
            driver.findElement(By.cssSelector("div[class='pagination-component'] a[aria-label='page "+i+"']")).click();
            for (WebElement product : productDetailSection) {
                fileUtils.writeToTextFile(writer, product, topSellerMessage);
            }
        }

        writer.close();

        log.info("end of the script");
    }
}
