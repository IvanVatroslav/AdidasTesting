@MainNavigationMenu
Feature: Verify Main Navigation Menu Functionality

  Scenario: Verify the visibility and correctness of each main navigation menu item
    Given I am on the homepage
    Then I verify the visibility and correctness of each item in the navigation menu
      | MEN         |
      | WOMEN       |
      | KIDS        |
      | SALE        |
      | 3 STRIPE LIFE |