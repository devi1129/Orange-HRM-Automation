
Feature: Login page

Scenario:  To Verify Login functionaity with correct username and password
Given user is on login page
When user clicks on login button by entering correct username and password 
Then user should be directed to dashboard page



Scenario: To verify login functionality with incorrect username and correct password
When user clicks on login button by entering incorrect username and correctpassword 
Then user should get invalid credentials errors

Scenario: To verify login functionality with correct username and incorrect password
When user clicks on login button by entering correct username and incorrectpassword 
Then user should get invalid credentials errors


Scenario: To verify login functionality with incorrect username and incorrect password
When user clicks on login button by entering incorrect username and incorrectpassword 
Then user should get invalid credentials errors