@Completed @Accepted @Api 
Feature: Notification API testing. 

Background: The api url 
	Given I visit "${api.url}" 
	And I relax https validation 
	
Scenario: 001 - Verify Get Notification API 
	Given I send the following headers: 
		|name | value |
		|Accept|application/json|
	Given I set path parameter "subscriberId" to "1" 
	When I request GET to "/v1/notification/{subscriberId}" 
	Then response code should be 200 
	
Scenario: 002 - Verify Send Notification API 
	Given I send the following headers: 
		|name | value |
		|Accept|application/json|
		|Content-Type|application/json;charset=UTF-8|
	Given I send content in file "payload.json"
	When I request POST to "/v1/notification" 
	Then response code should be 200 