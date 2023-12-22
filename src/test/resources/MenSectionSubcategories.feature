@MenSectionSubcategories
Feature: Verify Sub-Categories under Men's Section

  Scenario: Sequentially check each sub-category under Men's section
    Given I am on the homepage
    When I hover over the Men's section in the main menu
    Then I should see the dropdown with sub-categories
    And I sequentially navigate through each sub-category in Men's section
    Then I should see the relevant products and content for each sub-category
