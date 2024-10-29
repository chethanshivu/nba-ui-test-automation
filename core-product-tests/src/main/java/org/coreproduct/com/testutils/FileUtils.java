package org.coreproduct.com.testutils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;

import java.io.IOException;
import java.io.Writer;



@Slf4j
public class FileUtils {

    private static final By TITLE_SELECTOR = By.cssSelector("[data-talos='linkSearchResult']");
    private static final By PRICE_SELECTOR = By.cssSelector("[data-talos='srpProductPrice']");
    private static final By TOP_SELLER_SELECTOR = By.cssSelector(".product-vibrancy-container");

    public void writeToTextFile(Writer writer, WebElement product, WebDriver driver){
        try {
            writer.write("Title : " + product.findElement(TITLE_SELECTOR).getText());
            writer.append(System.lineSeparator());
            String price = product.findElement(PRICE_SELECTOR).getText().split(" ")[0].split("\n")[0];
            writer.write("Price : " + price);
            writer.append(System.lineSeparator());

            JavascriptExecutor js = (JavascriptExecutor) driver;
                WebElement element = (WebElement) js.executeScript("return document.querySelector('.product-vibrancy-container');");
                if (element != null) {
                    String sellerMessage = element.getText();
                    writer.write("Top Seller Message : " + sellerMessage);
                } else {
                    writer.write("Top Seller Message is not available");
                }

            writer.append(System.lineSeparator());
            writer.write("--------------------------------");
            writer.append(System.lineSeparator());
        }
        catch (IOException ex) {
            log.info(ex.getMessage());
        }
    }
}
