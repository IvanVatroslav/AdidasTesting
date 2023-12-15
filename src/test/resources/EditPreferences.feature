@test2
Feature: Update User Preferences in Account Settings

  Scenario: User edits preferences in account settings
    Given the user is logged in and on the main page
    When the user navigates to the account settings page
    And the user goes to the preferences section
    And the user changes preferences
    Then the new preferences should be saved and displayed