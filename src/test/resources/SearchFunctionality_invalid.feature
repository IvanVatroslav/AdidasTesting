@YZT4
Feature: Search Functionality Verification

  Scenario: Verify search results for an invalid keyword
    Given I am on the homepage
    When I search for 'invalid_keyword'
    Then a no results page should be displayed