Feature: Edit Personal Information in User Account
  @RandomBirthdate
  Scenario: User edits birth date on account page with valid data
    Given the user is logged in and on the main page
    When the user navigates to the account settings page
    And the user changes the birth date to a random date
    Then the new birth date should be saved and displayed

  @TestPersonalInfo
  Scenario: Attempt data changes with validation
    Given the user is logged in and on the main page
    And the user navigates to the account settings page
    When the user attempts to change birth dates and names according to the following data
      | Date        | Name         | Outcome          |
      | 1994-30-08  | P3R0 P3R1C   | reject           |
      | 1994-08-30  | Pero Peric   | reject           |
      | 1994-08-30  | P3R0 P3R1C   | reject           |
      | 2023-30-08  | Petar Djetić | reject           |
      | 23 - 30 - 8 | Ban Banic    | reject           |
      | 1994-30-08  | Pero Perić   | save and display |
