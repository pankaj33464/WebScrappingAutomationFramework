Feature: News Validation

  Scenario: Verify the validity of a news article from The Guardian
    Given I am on The Guardian news page
    And I extract the title and content of the first news article
    When I search for the article title and content on Google
    Then I should find at least two matching results


  Scenario: Verify no results for a non-existing news article on The Guardian
    Given I am on The Guardian news page
    And I extract the title and content of the first news article
    When I search for a non-existing article title and content on Google
    Then I should see no results
