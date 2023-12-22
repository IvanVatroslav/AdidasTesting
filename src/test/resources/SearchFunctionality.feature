@YZT3
Feature: Search Functionality Verification

  Scenario: Verify search results for 'SAMBA OG SHOES'
    Given I am on the homepage
    When I search for 'SAMBA OG SHOES'
    Then the search results page should open
    And the list of products should not be empty
    And all products should have the name 'SAMBA OG SHOES'
