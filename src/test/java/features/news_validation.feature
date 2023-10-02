Feature: News Validation

  #While your scenarios were not complex enough to warrant a Scenario Outline, I kept them separate for clarity.
  # If scenarios grow in complexity, we should consider using a Scenario Outline.
  @PositiveTest @IntegrationTest @Sanity
  Scenario: Verify the validity of a news article from The Guardian
    Given the user is on The Guardian news page
    And the user extracts the title and content of the first news article
    When the user searches for the article title and content on Google
    Then at least two matching results should be found

  @NegativeTest @Sanity
  Scenario: Verify no results for a non-existing news article on The Guardian
    Given the user is on The Guardian news page
    And the user extracts the title and content of the first news article
    When the user searches for a non-existing article title and content on Google
    Then no search results should be displayed