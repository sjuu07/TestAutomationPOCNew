Feature: Advantage Demo online shopping

Background: 
		Given user opens the browser
    When user navigates to url
    And click menu to provide login credentials
    Then verify the page title
    
  @smoke
  Scenario: Search product and place an order
    Given user is on application welcome page
    When user search an item
    And add item to cart
    And complete the payment
    Then order is placed successfully
    
    Scenario: Validate ContactUs functionality by email trigger