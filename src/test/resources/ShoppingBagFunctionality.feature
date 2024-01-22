
Feature: Shopping Cart Functionality with Scenario Outline

  @ShoppingCart
  Scenario Outline: User searches for <search_keyword>, adds first three products to basket, and removes them
    Given the user is logged in and on the main page
    When I search for '<search_keyword>'
    Then the search results page should open
    And the list of products should not be empty
    And all products should have the name '<search_keyword>'
    When the user adds the random product from the search results to the bag
    Then the bag should contain products
    When the user navigates to the bag page
    And the user removes all products from the bag
    Then the bag should be empty
    When the user logs out
    Then the user should be redirected to the login page
    Examples:
      | search_keyword |
      | SAMBA OG SHOES |