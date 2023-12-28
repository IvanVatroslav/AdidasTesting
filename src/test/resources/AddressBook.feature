@AddressBook
Feature: Add Addresses to Address Book

  Scenario: User adds multiple addresses to their address book
    Given the user is logged in and on the main page
    When the user navigates to the account settings page
    And the user navigates to the address book page
    And the user removes any old addresses
    And the user adds new addresses
      | FirstName | LastName | Address     | City     | State    | Zip   | Phone      |
      | John      | Doe      | 123 Main St | Anytown  | Florida  | 12345 | 1234567890 |
      | Jane      | Roe      | 456 Elm St  | Difftown | Maine    | 67890 | 0987654321 |
    Then the new addresses should be saved and displayed in the address book
