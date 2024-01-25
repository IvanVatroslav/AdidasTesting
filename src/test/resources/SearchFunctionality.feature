Feature: Search Functionality Verification

  @SearchFunctionality
  Scenario Outline: Verify search results for <search_keyword>
    Given I am on the homepage
    When I search for '<search_keyword>'
    Then the search results page should open
    Then the list of products should not be empty
    Then all products should have the name '<search_keyword>'
    Examples:
      | search_keyword |
      | SAMBA OG       |

  @SearchFunctionality_invalid
  Scenario: Verify search results for an invalid keyword
    Given I am on the homepage
    When I search for 'invalid_keyword'
    Then a no results page should be displayed

