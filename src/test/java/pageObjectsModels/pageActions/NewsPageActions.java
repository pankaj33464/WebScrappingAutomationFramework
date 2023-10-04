package pageObjectsModels.pageActions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjectsModels.BasePage;
import pageObjectsModels.pageLocators.NewsPageLocators;
import utils.Common;
import utils.DriverManager;

import java.util.List;

public class NewsPageActions extends BasePage {

    NewsPageLocators newsPageLocators;

    private String articleTitle;
    private String articleContent;
    private List<String> searchResults;

    public NewsPageActions(WebDriver driver) {
        super(driver);
        this.newsPageLocators = new NewsPageLocators();
    }

    private static final Logger logger = LoggerFactory.getLogger(NewsPageActions.class);


    public WebElement getFirstArticleContent() {
        return DriverManager.driver.findElement(newsPageLocators.firstArticleContent);
    }

    public WebElement getFirstArticleTitle() {
        return DriverManager.driver.findElement(newsPageLocators.firstArticleTitle);
    }

    public WebElement getSearchBox() {
        return DriverManager.driver.findElement(newsPageLocators.googleSearchBox);
    }

    public List<WebElement> getResultItemsFromSearch() {
        return DriverManager.driver.findElements(newsPageLocators.resultItemsFromSearch);
    }

    public void searchForNews() {
        logger.info("Article Title " + articleTitle);
        logger.info("Article Content " + articleContent);
        performGoogleSearch(articleTitle + " " + articleContent);
    }

    /**
     * @return article title
     */
    public String extractArticleTitle() {
        // Extract the title of the first news article from The Guardian's page
        articleTitle = getFirstArticleTitle().getText();

        //For some news when they are LIVE, they have new line character
        articleTitle = articleTitle.replaceAll("\\n", "");
        if (articleTitle.startsWith("Live")) {
            articleTitle = articleTitle.substring(4);
        }
        return articleTitle;
    }

    /**
     * @return article content
     */
    public String extractArticleContent() {
        // Extract the content of the first news article from The Guardian's page
        Common.takeScreenshot("List of news");
        articleContent = getFirstArticleContent().getText();
        return articleContent;
    }

    /**
     * @param query News articles title and content
     * @return List of news from search on Google
     */
    public List<String> performGoogleSearch(String query) {
        // Perform a Google search and extract search results

        //For some news when they are LIVE, they have new line character
        String QueryWithoutNewLineChar = query.replaceAll("\\n", " ");
        if (QueryWithoutNewLineChar.startsWith("Live")) {
            QueryWithoutNewLineChar = QueryWithoutNewLineChar.substring(4);
        }
        DriverManager.driver.get("https://www.google.com");
        getSearchBox().sendKeys(QueryWithoutNewLineChar);
        getSearchBox().sendKeys(Keys.RETURN);
        Common.takeScreenshot("List of all news");
        // This is a simplistic approach I have used for now
        searchResults = extractSearchResultTexts(getResultItemsFromSearch());
        logger.info("Search Results " + searchResults);
        return searchResults;
    }

    /**
     * @param resultElements extracted title and content from searched results
     * @return list of searched results with title and content
     */
    public List<String> extractSearchResultTexts(List<WebElement> resultElements) {
        // This is a simplistic approach I have used for now
        return resultElements.stream()
                .map(WebElement::getText)
                .toList();
    }

    /**
     * @param expectedCount count to verify if news is fake or not
     * @return
     */
    public boolean verifyMatchingResults(int expectedCount) {
        // Verify that at least the expected number of results match the article
        long matchingResultsCount = searchResults.stream()
                .filter(result -> result.contains(articleTitle) && result.contains(articleContent))
                .count();
        Common.takeScreenshot("Google Search Results");
        return matchingResultsCount >= expectedCount;
    }
}
