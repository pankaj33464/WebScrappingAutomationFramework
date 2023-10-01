package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashSet;
import java.util.Set;

public class GoogleSearchAndCompare {
   /* public static void main(String[] args) {
*//*        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();*//*

        // Google search with a big statement
        String searchQuery = "Your big statement goes here";
        performGoogleSearch(DriverManager.driver, searchQuery);

        // Get search results and analyze
        Set<String> relevantWords = extractRelevantWords(searchQuery);
        analyzeSearchResults(DriverManager.driver, relevantWords);

        // Close the browser
        DriverManager.driver.quit();
    }

    private static void performGoogleSearch(WebDriver driver, String query) {
        DriverManager.driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
        searchBox.sendKeys(Keys.RETURN);
    }

    private static Set<String> extractRelevantWords(String statement) {
        // Extract relevant words from the statement (you might need more sophisticated logic)
        String[] words = statement.split("\\s+");
        Set<String> relevantWords = new HashSet<>();
        for (String word : words) {
            // Add logic to filter out irrelevant words, e.g., common words, stopwords, etc.
            relevantWords.add(word.toLowerCase());
        }
        return relevantWords;
    }

    private static void analyzeSearchResults(WebDriver driver, Set<String> relevantWords) {
        // Extract search results using Jsoup
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements resultElements = doc.select(".tF2Cxc"); // Adjust the selector based on actual page structure

        for (Element resultElement : resultElements) {
            String snippet = resultElement.selectFirst(".aCOpRe").text();

            // Calculate the percentage of relevant words in the snippet
            int totalWords = snippet.split("\\s+").length;
            long relevantWordCount = relevantWords.stream()
                    .filter(word -> snippet.toLowerCase().contains(word))
                    .count();

            double percentage = (double) relevantWordCount / totalWords * 100;

            // Print or use the percentage as needed
            System.out.println("Percentage of relevant words: " + percentage);
            System.out.println("Snippet: " + snippet);
            System.out.println();
        }
    }*/
}
