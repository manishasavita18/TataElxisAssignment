package com.example.myassignment.stepdefinitions;
import io.cucumber.datatable.DataTable;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.*;
public class SubscriptionPage {
        private WebDriver driver;

        // Locators
        private final By countryButton = By.id("country-btn");

        public SubscriptionPage(WebDriver driver) {
            this.driver = driver;
        }

        public void navigateTo(String url) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get(url);
        }

        public void selectCountry(String country) {
            driver.findElement(countryButton).click();
            if(Objects.equals(country, "Bahrain")) {
                driver.findElement(By.id("bh")).click();
            } else if (Objects.equals(country, "KSA")) {
                driver.findElement(By.id("sa")).click();
            } else if (Objects.equals(country, "Kuwait"))
                driver.findElement(By.id("kw")).click();
        }
        public void ValidateResults(DataTable dataTable)
        {
            List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
            for (Map<String, String> columns : rows) {
                String packageType = columns.get("PackageType");
                String expectedPrice = columns.get("Price");
                String expectedCurrency = columns.get("Currency");
                // Locators
                WebElement packageElement = driver.findElement(By.xpath("//div[@class='plan-names']"));
                String resultContent = packageElement.getText();
                System.out.print(resultContent);
                String actualPriceAndCurrency = packageElement.findElement(By.xpath("//div[@class='price']")).getText();
                System.out.print(actualPriceAndCurrency);
                //String actualCurrency = packageElement.findElement(By.xpath("//div[@class='plan-names']")).getText();
                //System.out.print(resultContent);

                // Validate price and currency
                assertTrue("Result does not contain expected string for " + packageType, resultContent.contains(packageType));
                assertTrue("Result does not contain expected string for " + packageType, resultContent.contains(expectedCurrency));
                assertTrue("Result does not contain expected string for " + packageType, resultContent.contains(expectedPrice));

                //assertEquals("Currency does not match for " + packageType, expectedCurrency, actualCurrency);
            }
            // Close the browser after validation
            driver.quit();
        }
    }
