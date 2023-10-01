package stepDefinitions;

import copy.Copy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageObjects.NewsPage;

import utils.DriverManager;

import static org.junit.Assert.assertTrue;


public class NewsValidationStepDefinitions {

    NewsPage newsPage = new NewsPage(DriverManager.driver);

    @Given("the user is on The Guardian news page")
    public void iAmOnTheGuardianNewsPage() {
        // Nothing additional to do here since news page is already launched using hooks
    }

    @And("the user extracts the title and content of the first news article")
    public void iExtractTheTitleAndContentOfTheFirstNewsArticle() {
        // Extract the title and content of the first news article
        newsPage.extractArticleTitle();
        newsPage.extractArticleContent();
    }

    @When("the user searches for the article title and content on Google")
    public void iSearchForTheArticleTitleAndContentOnGoogle() {
        newsPage.searchForNews();
    }

    @Then("at least two matching results should be found")
    public void iShouldFindAtLeastTwoMatchingResults() {
        assertTrue(Copy.FAKE_MESSAGE_TEXT, newsPage.verifyMatchingResults(2));
    }

    @When("the user searches for a non-existing article title and content on Google")
    public void iSearchForANonExistingArticleTitleAndContentOnGoogle() {
        newsPage.searchForNews();
    }

    @Then("no search results should be displayed")
    public void iShouldSeeNoResults() {
        assertTrue(Copy.FAKE_MESSAGE_TEXT, newsPage.verifyMatchingResults(0));
    }

}