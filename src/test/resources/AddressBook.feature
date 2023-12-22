@AddressBook
Feature: Add Addresses to Address Book

  Scenario: User adds two different addresses to their address book
    Given the user is logged in and on the main page
    When the user navigates to the account settings page
    And the user navigates to the address book page
    And the user removes any old addresses
    And the user adds a new address with specific details
    And the user adds another new address with different details
    Then both new addresses should be saved and displayed in the address book
