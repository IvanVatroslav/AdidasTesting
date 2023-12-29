@MenSectionSubcategories
Feature: Verify Sub-Categories under Men's Section

  Scenario: Verify specific sub-categories under Men's section
    Given I am on the homepage
    When I hover over the Men's section in the main menu
    Then I should see the dropdown with sub-categories
    And I verify the following sub-categories are correct
      | NEW & TRENDING          |
      | SHOES                   |
      | CLOTHING                |
      | ACCESSORIES             |
      | SHOP BY SPORT           |
      | SHOP BY COLLECTION      |
