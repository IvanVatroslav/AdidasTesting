Feature: User Login

  Scenario: Logging in with valid credentials
    Given the user is on the login page
    When the user enters valid username and password
    And clicks on the login button
    Then the user should be directed to the homepage

  Scenario: Attempting to log in with invalid credentials
    Given the user is on the login page
    When the user enters invalid username and password
    And clicks on the login button
    Then the user should see an error message
