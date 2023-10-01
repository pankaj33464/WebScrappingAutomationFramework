package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Common;
import utils.DriverManager;

import java.util.List;

public class NewsPage {

    Common common = new Common();

    private String articleTitle;
    private String articleContent;
    private List<String> searchResults;
    public NewsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

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
    public WebElement getSearchBox(){
        return DriverManager.driver.findElement(googleSearchBox);
    }

    public List<WebElement> getResultItemsFromSearch(){
        return DriverManager.driver.findElements(resultItemsFromSearch);
    }
    public String extractArticleTitle() {
        // Extract the title of the first news article from The Guardian's page
        articleTitle = getFirstArticleTitle().getText();
        return articleTitle;
    }

    public String extractArticleContent() {
        // Extract the content of the first news article from The Guardian's page
        Common.takeScreenshot("List of news");
        articleContent = getFirstArticleContent().getText();
        return articleContent;
    }

    public List<String> performGoogleSearch(String query) {
        // Perform a Google search and extract search results
        DriverManager.driver.get("https://www.google.com");
        getSearchBox().sendKeys(query);
     /*   WebElement clickSearch = DriverManager.driver.findElement(By.className("zgAlFc"));
       clickSearch.click();*/
        getSearchBox().sendKeys(Keys.RETURN);
        Common.takeScreenshot("List of all news");
        // Extract search results (this is a simplified example)
        searchResults = extractSearchResultTexts(getResultItemsFromSearch());
        return searchResults;
    }

    public List<String> extractSearchResultTexts(List<WebElement> resultElements) {
        // Extract text from search result elements
        // This is a simplistic approach; you may need to refine it based on the actual structure of search results
        // and the information you want to extract.
        return resultElements.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean verifyMatchingResults(int expectedCount) {
        // Verify that at least the expected number of results match the article
        long matchingResultsCount = searchResults.stream()
                .filter(result -> result.contains(articleTitle) && result.contains(articleContent))
                .count();

        return matchingResultsCount >= expectedCount;
    }

}
