package com.example.myassignment.stepdefinitions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import java.util.Map;

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
    @Then("I validate subscription packages with the following details")
        public void validateSubscriptionPackages(DataTable dataTable) {
        subscriptionPage.ValidateResults(dataTable);
        }

}
