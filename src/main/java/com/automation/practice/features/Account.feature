Feature: Account

  @smoke
  Scenario: Register User
    Given user open application
    When navigate to sign in
    And register with mandetory details
    Then verify the new user

  @smoke
  Scenario: Login with valid credentials
    Given user login with registered account
    When user see "MY ACCOUNT" header
    Then verify the new user

  @smoke
  Scenario: Login with blank value
    Given click on sign in
    When user see "There is 1 error" message
    Then verify the error "An email address required."

  @smoke
  Scenario: Login with blank password
    Given user enter email
    When click on sign in
    And user see "There is 1 error" message
    Then verify the error "Password is required."

  @smoke
  Scenario: Login with registered email and invalid password
    Given user enter email
    And user enter password "123"
    When click on sign in
    And user see "There is 1 error" message
    Then verify the error "Invalid password."

  @smoke
  Scenario: Login with unregistered email and valid password
    Given user enter email "notregistered@abc.com"
    And user enter password
    When click on sign in
    And user see "There is 1 error" message
    Then verify the error "Authentication failed."

  @smoke
  Scenario: Login with invalid email and valid password
    Given user enter invalid email "invalidabc.com"
    And user enter password
    When click on sign in
    And user see "There is 1 error" message
    Then verify the error "Invalid email address."
