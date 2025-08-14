Feature: News Validation

  Background:
    Given the user is on The Guardian news page

  @PositiveTest @IntegrationTest @Sanity
  Scenario: Verify the validity of the first news article
    When the user extracts the title and content of the first news article
    And the user searches for the article title and content on Google
    Then at least 2 matching results should be found

  @NegativeTest @Sanity
  Scenario: Verify no results for a non-existing news article on The Guardian
    When the user extracts the title and content of the first news article
    And the user searches for a non-existing article title and content on Google
    Then no matching results should be found

