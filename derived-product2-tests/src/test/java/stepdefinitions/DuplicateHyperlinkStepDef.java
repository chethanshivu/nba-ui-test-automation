package stepdefinitions;

import com.opencsv.CSVWriter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.commonutils.ScenarioContext;
import org.automationutils.com.webdrivermanager.WebDriverManager;

import org.derivedproduct2.com.pageobjects.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class DuplicateHyperlinkStepDef {

    WebDriver driver = WebDriverManager.getDriver();
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
            if(!Files.exists(Path.of("test-output/UniqueHyperlinks.csv"))){
                Files.createDirectory(Path.of("test-output"));
            }
            CSVWriter  writer = new CSVWriter(new FileWriter("test-output/UniqueHyperlinks.csv"));
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
            if(!Files.exists(Path.of("test-output/"+fileName+".csv"))){
                Files.createDirectory(Path.of("test-output"));
            }
            CSVWriter  duplicateDataWriter = new CSVWriter(new FileWriter("test-output/"+fileName+".csv"));

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

            File file = new File("test-output/"+fileName+".csv");
           if(file.exists() && file.length() != 0){
               ScenarioContext.logInfo("Duplicate hyperlink detected");
           }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
