@test1
Feature: Edit Birth Date in User Account
  Scenario: User edits birth date on account page
    Given the user is logged in and on the main page
    When the user navigates to the account settings page
    And the user changes the birth date to a random date
    Then the new birth date should be saved and displayed