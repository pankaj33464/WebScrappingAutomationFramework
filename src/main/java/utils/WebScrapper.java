package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScrapper {

    public static void main(String[] args) {
        // Example search query
        String searchQuery = "Introduction to Java and pankaj sharma";

        try {
            // Perform a Google search and get search results HTML
            String searchResultsHtml = performGoogleSearch(searchQuery);

            // Analyze search results
            analyzeSearchResults(searchResultsHtml, searchQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String performGoogleSearch(String query) throws IOException {
        // Construct the Google search URL
        String googleSearchUrl = "https://www.google.com/search?q=" + query;

        // Connect to Google and get the HTML of the search results page
        return Jsoup.connect(googleSearchUrl).get().html();
    }

    private static void analyzeSearchResults(String html, String searchQuery) {
        // Parse the HTML using Jsoup
        Document doc = Jsoup.parse(html);

        // Extract search result titles and links
        Elements resultElements = doc.select(".tF2Cxc"); // Adjust the selector based on actual page structure
        for (Element resultElement : resultElements) {
            String title = resultElement.selectFirst("h3").text().toLowerCase();
            String link = resultElement.selectFirst("a").attr("href").toLowerCase();

            // Analyze each result
            System.out.println("Title: " + title);
            System.out.println("Link: " + link);

            // Check if the title or link contains the search query
            if (title.contains(searchQuery) || link.contains(searchQuery)) {
                System.out.println("Related to search query!");
            }

            System.out.println();
        }
    }
}
