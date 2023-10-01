package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageObjects.NewsPage;

import utils.DriverManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.Assert.assertTrue;


public class NewsValidationStepDefinitions {
    public static Properties prop = new Properties();
    private String articleTitle;
    private String articleContent;

    NewsPage newsPage = new NewsPage(DriverManager.driver);

    @Given("I am on The Guardian news page")
    public void iAmOnTheGuardianNewsPage() {
        // Nothing additional to do here since news page is already launched using hooks
    }

    @And("I extract the title and content of the first news article")
    public void iExtractTheTitleAndContentOfTheFirstNewsArticle() {
        // Extract the title and content of the first news article
        articleTitle = newsPage.extractArticleTitle();
        articleContent = newsPage.extractArticleContent();
    }

    @When("I search for the article title and content on Google")
    public void iSearchForTheArticleTitleAndContentOnGoogle() {

        newsPage.performGoogleSearch(articleTitle + " " + articleContent);
    }

    @Then("I should find at least two matching results")
    public void iShouldFindAtLeastTwoMatchingResults() {
        assertTrue("News is fake as match is less then 2", newsPage.verifyMatchingResults(2));
        DriverManager.closeBrowser();
    }

    @When("I search for a non-existing article title and content on Google")
    public void iSearchForANonExistingArticleTitleAndContentOnGoogle() {
    }

    @Then("I should see no results")
    public void iShouldSeeNoResults() {
    }

}