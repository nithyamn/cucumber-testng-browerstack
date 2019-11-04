Feature: Browser Automation

  Scenario: Local Test
    Given Open Browser
    When Go to localhost
    Then Retrieve Title if Up and Running
