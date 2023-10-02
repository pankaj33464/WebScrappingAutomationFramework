package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Common;
import utils.DriverManager;

import java.util.List;

public class NewsPage extends BasePage {

    private String articleTitle;
    private String articleContent;
    private List<String> searchResults;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(NewsPage.class);

    By firstArticleTitle = By.className("fc-item__header");

    By googleSearchBox = By.name("q");
    By firstArticleContent = By.className("fc-item__standfirst");

    By resultItemsFromSearch = By.cssSelector(".tF2Cxc");

    public WebElement getFirstArticleContent() {
        return DriverManager.driver.findElement(firstArticleContent);
    }

    public WebElement getFirstArticleTitle() {
        return DriverManager.driver.findElement(firstArticleTitle);
    }

    public WebElement getSearchBox() {
        return DriverManager.driver.findElement(googleSearchBox);
    }

    public List<WebElement> getResultItemsFromSearch() {
        return DriverManager.driver.findElements(resultItemsFromSearch);
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
        DriverManager.driver.get("https://www.google.com");
        getSearchBox().sendKeys(query);
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
