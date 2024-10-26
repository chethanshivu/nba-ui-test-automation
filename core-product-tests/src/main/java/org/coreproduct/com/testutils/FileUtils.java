package org.coreproduct.com.testutils;

import org.automationutils.com.commonutils.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class FileUtils {

    SeleniumActions seleniumActions = new SeleniumActions();

    public void writeToTextFile(Writer writer, WebElement product, WebElement topSellerMessage){
        try {
            writer.write("Title : " + product.findElement(By.cssSelector("[data-talos='linkSearchResult']")).getText());
            writer.append(System.lineSeparator());
            String price = product.findElement(By.cssSelector("[data-talos='srpProductPrice']")).getText().split(" ")[0].split("\n")[0];
            writer.write("Price : " + price);
            writer.append(System.lineSeparator());

              //try

          //  List<WebElement> productElements = product.findElements(By.cssSelector(".product-vibrancy.top-seller-vibrancy"));

          //  if(!product.findElements(By.cssSelector(".product-vibrancy.top-seller-vibrancy")).isEmpty()){

//            if (!productElements.isEmpty()) {
//                writer.write("Top Seller Message : " + product.findElement(By.cssSelector(".product-vibrancy.top-seller-vibrancy")).getText());
//            } else {
//                writer.write("Top Seller Message is not available");
//            }
            //"//*[@data-talos='linkSearchResult']/parent::div/following-sibling::div"))
            //  }else {
                  //catch (NoSuchElementException e){
                //  writer.write("Top Seller Message is not available");
             // }
            //  }

            writer.append(System.lineSeparator());
            writer.write("--------------------------------");
            writer.append(System.lineSeparator());

        }
        catch (IOException ex) {
            // Handle me
        }
    }
}
