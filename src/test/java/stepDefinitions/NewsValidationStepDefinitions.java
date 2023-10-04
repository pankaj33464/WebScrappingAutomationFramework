package stepDefinitions;

import copy.Copy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjectsModels.pageActions.NewsPageActions;
import utils.DriverManager;

import static org.junit.Assert.assertTrue;

public class NewsValidationStepDefinitions {

    NewsPageActions newsPage = new NewsPageActions(DriverManager.driver);
    private static final Logger logger = LoggerFactory.getLogger(NewsValidationStepDefinitions.class);

    @Given("the user is on The Guardian news page")
    public void iAmOnTheGuardianNewsPage() {
        // Nothing additional to do here since news page is already launched using hooks
    }

    @And("the user extracts the title and content of the first news article")
    public void iExtractTheTitleAndContentOfTheFirstNewsArticle() {
        // Extract the title and content of the first news article
        newsPage.extractArticleTitle();
        newsPage.extractArticleContent();
        logger.info("Title and content of news are extracted");
    }

    @When("the user searches for the article title and content on Google")
    public void iSearchForTheArticleTitleAndContentOnGoogle() {
        newsPage.searchForNews();
        logger.info("Search for news on google");
    }

    @Then("at least two matching results should be found")
    public void iShouldFindAtLeastTwoMatchingResults() {
        assertTrue(Copy.FAKE_MESSAGE_TEXT, newsPage.verifyMatchingResults(2));
        logger.info("Matching results");
    }

    @When("the user searches for a non-existing article title and content on Google")
    public void iSearchForANonExistingArticleTitleAndContentOnGoogle() {
        newsPage.searchForNews();
        logger.info("Search for news on google for non-existing article");
    }

    @Then("no search results should be displayed")
    public void iShouldSeeNoResults() {
        assertTrue(Copy.FAKE_MESSAGE_TEXT, newsPage.verifyMatchingResults(0));
        logger.info("no search result assertion is in progress");
    }

}