package stepdefinitions;

import com.opencsv.CSVWriter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.webdrivermanager.WebDriverManager;

import org.derivedproduct2.com.pageobjects.HomePage;
import org.derivedproduct2.com.testutils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
public class DuplicateHyperlinkStepDef {

    String browserType = ConfigReader.getBrowserType();

    WebDriverManager webDriverManager = new WebDriverManager();
    WebDriver driver = webDriverManager.getWebDriver(browserType);
    HomePage homePage = new HomePage(driver);

    @Given("User is on the Website {string}")
    public void userIsOnTheWebsite(String url)  {
        driver.get(url);
        log.info(driver.getTitle());
    }

    @When("User scrolls down to the footer")
    public void userScrollsDownToTheFooter() {
        WebElement footer = homePage.getFooter();
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", footer);
    }

    @Then("Different hyperlinks should be available")
    public void differentHyperlinksShouldBeAvaialable() {
       Assert.assertTrue(homePage.getFooterLinks().size()>1);
    }

    @And("Capture all the links and store in csv file")
    public void captureAllTheLinksAndStoreInCsvFile() {
        try {
            CSVWriter  writer = new CSVWriter(new FileWriter("UniqueHyperlinks.csv"));
            List<WebElement> links = homePage.getFooterLinks();

            String[] row = new String[1];
            for(WebElement element : links){
                row[0] = (element.getAttribute("href"));
                writer.writeNext(row);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @And("Add the duplicated hyperlinks to {string} csv file")
    public void addTheDuplicatedHyperlinksToCsvFile(String fileName) {
        try {
            CSVWriter  duplicateDataWriter = new CSVWriter(new FileWriter(fileName+".csv"));

            List<WebElement> links = homePage.getFooterLinks();

            String[] row = new String[1];
            Set<String> listOfUniqueLinks = new HashSet<>();
            for(WebElement element : links){
                if(!listOfUniqueLinks.add(element.getAttribute("href"))){
                    row[0] = (element.getAttribute("href"));
                    duplicateDataWriter.writeNext(row);
                }
            }

            duplicateDataWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
