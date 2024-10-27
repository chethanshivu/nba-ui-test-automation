package org.coreproduct.com.testutils;

import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.automationutils.com.commonutils.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;


@Slf4j
public class FileUtils {

    private static final By TITLE_SELECTOR = By.cssSelector("[data-talos='linkSearchResult']");
    private static final By PRICE_SELECTOR = By.cssSelector("[data-talos='srpProductPrice']");
    private static final By TOP_SELLER_SELECTOR = By.cssSelector(".product-vibrancy-container");

    public void writeToTextFile(Writer writer, WebElement product, WebElement topSellerMessage){
        try {
            writer.write("Title : " + product.findElement(TITLE_SELECTOR).getText());
            writer.append(System.lineSeparator());
            String price = product.findElement(PRICE_SELECTOR).getText().split(" ")[0].split("\n")[0];
            writer.write("Price : " + price);
            writer.append(System.lineSeparator());

            writer.append(System.lineSeparator());
            writer.write("--------------------------------");
            writer.append(System.lineSeparator());

        }
        catch (IOException ex) {
            log.info(ex.getMessage());
        }
    }

    public void attachFile(Scenario scenario, String path){
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(path));
            String fileName = new File(path).getName();
            scenario.attach(fileContent, "text/plain", fileName);

        } catch (IOException e) {
            scenario.log("Failed to attach file: " + e.getMessage());
        }
    }
}
