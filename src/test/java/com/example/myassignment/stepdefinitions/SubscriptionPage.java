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

    public void ValidateResultsLITE(DataTable dataTable)
    {
        List<Map<String, String>> expectedDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> expectedDetails = expectedDetailsList.getFirst();

        // Locate the subscription package element for "lite"
        WebElement litePackage = driver.findElement(By.xpath("//div[contains(@class, 'plan-names')]"));

        // Extract the type, price, and currency from the web page
        String type = litePackage.findElement(By.id("name-lite")).getText();
        String price = litePackage.findElement(By.xpath("//div[@class='price']/*[self::b]")).getText();
        String currency = litePackage.findElement(By.xpath("//div[@class='price']/*[self::i]")).getText();
        String currencyCode = currency.split("/")[0];

        // Validate the extracted details against the expected details
        assertEquals(expectedDetails.get("Packagetype"), type);
        assertEquals(expectedDetails.get("Price"), price);
        assertEquals(expectedDetails.get("Currency"), currencyCode);
    }
    public void ValidateResultsCLASSIC(DataTable dataTable)
    {
        List<Map<String, String>> expectedDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> expectedDetails = expectedDetailsList.getFirst();

        // Locate the subscription package element for "lite"
        WebElement litePackage = driver.findElement(By.xpath("//div[contains(@class, 'plan-names')]"));

        // Extract the type, price, and currency from the web page
        String type = litePackage.findElement(By.id("name-classic")).getText();
        String price = litePackage.findElement(By.xpath("//div[@id='currency-classic']/*[self::b]")).getText();
        String currency = litePackage.findElement(By.xpath("//div[@class='price']/*[self::i]")).getText();
        String currencyCode = currency.split("/")[0];

        // Validate the extracted details against the expected details
        assertEquals(expectedDetails.get("Packagetype"), type);
        assertEquals(expectedDetails.get("Price"), price);
        assertEquals(expectedDetails.get("Currency"), currencyCode);
    }
    public void ValidateResultsPREMIUM(DataTable dataTable)
    {
        List<Map<String, String>> expectedDetailsList = dataTable.asMaps(String.class, String.class);
        Map<String, String> expectedDetails = expectedDetailsList.getFirst();

        // Locate the subscription package element for "lite"
        WebElement premiumPackage = driver.findElement(By.xpath("//div[contains(@class, 'plan-names')]"));

        // Extract the type, price, and currency from the web page
        String type = premiumPackage.findElement(By.id("name-premium")).getText();
        String price = premiumPackage.findElement(By.xpath("//div[@id='currency-premium']/*[self::b]")).getText();
        String currency = premiumPackage.findElement(By.xpath("//div[@class='price']/*[self::i]")).getText();
        String currencyCode = currency.split("/")[0];

        // Validate the extracted details against the expected details
        assertEquals(expectedDetails.get("Packagetype"), type);
        assertEquals(expectedDetails.get("Price"), price);
        assertEquals(expectedDetails.get("Currency"), currencyCode);

        // Close the browser
        driver.quit();
    }
}
