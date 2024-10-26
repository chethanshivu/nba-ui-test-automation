package stepdefinitions;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.coreproduct.com.pageobjects.HomePage;
import org.coreproduct.com.pageobjects.ShopPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

@Slf4j
public class MensJacketDetailsStepDef {
    @Given("User is on the Website {string}")
    public void userIsOnTheWebsite(String url) throws InterruptedException {
        WebDriverManager webDriverManager = new WebDriverManager();
        WebDriver driver = webDriverManager.getWebDriver("chrome");
        HomePage homePage = new HomePage(driver);
        ShopPage shopPage = new ShopPage(driver);
        driver.get(url);

        log.info(driver.getTitle());

        homePage.clickElement("close adds");
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


        List<WebElement> productDetailSection = shopPage.getProductDetailSection();

        for(WebElement product : productDetailSection){
          log.info(product.findElement(By.cssSelector("[data-talos='linkSearchResult']")).getText());
            log.info(product.findElement(By.cssSelector("[data-talos='srpProductPrice']")).getText());
            log.info(product.findElement(By.xpath("//*[@data-talos='linkSearchResult']/parent::div/following-sibling::div")).getText());
        }

        log.info("end of the script");
    }
}
