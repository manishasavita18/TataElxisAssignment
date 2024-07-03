package com.example.myassignment.stepdefinitions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import java.util.Map;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import static org.junit.Assert.*;
public class subscriptionStepDefinitions {

    private WebDriver driver;
    private SubscriptionPage subscriptionPage;
    private Map<String, String> packageDetails;
    private Map<String, String> expectedPackages;

    @Given("I navigate to {string}")
    public void navigateToPage(String url) {
        subscriptionPage = new SubscriptionPage(driver);
        subscriptionPage.navigateTo(url);
    }
    @When("I select country {string}")
    public void selectCountry(String country) {
        subscriptionPage.selectCountry(country);
    }

    @Then("the subscription package details for LITE should be:")
    public void theSubscriptionPackageDetailsForLITEShouldBe(DataTable dataTable) {
        subscriptionPage.ValidateResultsLITE(dataTable);
    }

    @And("the subscription package details for CLASSIC should be:")
    public void theSubscriptionPackageDetailsForCLASSICShouldBe(DataTable dataTable) {
        subscriptionPage.ValidateResultsCLASSIC(dataTable);
    }

    @And("the subscription package details for PREMIUM should be:")
    public void theSubscriptionPackageDetailsForPREMIUMShouldBe(DataTable dataTable) {
        subscriptionPage.ValidateResultsPREMIUM(dataTable);
    }
}
