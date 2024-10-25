package stepdefinitions;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;
import org.coreproduct.com.pageobjects.HomePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

@Slf4j
public class MensJacketDetailsStepDef {
    @Given("User is on the Website {string}")
    public void userIsOnTheWebsite(String url) throws InterruptedException {
        WebDriverManager webDriverManager = new WebDriverManager();
        WebDriver driver = webDriverManager.getWebDriver("chrome");
        HomePage homePage = new HomePage(driver);

        driver.get(url);

        log.info(driver.getTitle());

        homePage.clickElement("accept cookie");

        Thread.sleep(5000);
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        homePage.clickElement("Shop");
    }
}
