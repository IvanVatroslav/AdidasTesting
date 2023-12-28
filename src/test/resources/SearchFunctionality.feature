@SearchFunctionality
Feature: Search Functionality Verification

  Scenario: Verify search results for 'SAMBA OG SHOES'
    Given I am on the homepage
    When I search for 'SAMBA OG SHOES'
    Then the search results page should open
    Then the list of products should not be empty
    Then all products should have the name 'SAMBA OG SHOES'

  @SearchFunctionality_invalid
  Scenario: Verify search results for an invalid keyword
    Given I am on the homepage
    When I search for 'invalid_keyword'
    Then a no results page should be displayed
