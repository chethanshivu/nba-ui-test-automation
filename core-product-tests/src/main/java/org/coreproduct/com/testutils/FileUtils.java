package org.coreproduct.com.testutils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.io.Writer;


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
}
