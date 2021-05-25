#Author: yatinko
@HomePage
Feature: HomePage

  @Test
  Scenario: check the title
    Given User is on HomePage
    Then check the title of the page

  @Test
  Scenario Outline: Successful display of relevant products
    Given User is on HomePage
    When user enters "<searchItem>" and "<searchCategory>" in searchbar
    And clicks the Search button
    Then display relevant products
  
	Examples:
	  | searchItem	  | searchCategory 		           |
      | apple macbook | Computers/Tablets & Networking |
      | apple iphone  | Cell Phones & Accessories      |

  @Test
  Scenario Outline: Failure finding products
    Given User is on HomePage
    When user enters "<searchItem>" and "<searchCategory>" in searchbar
    And clicks the Search button
    Then display appropriate message
    	
  	Examples:
	  | searchItem | searchCategory 				|
      | TYUT       | Computers/Tablets & Networking |
      | HGGJH      | Cell Phones & Accessories      |

  @Ignore
  Scenario Outline: Successful display of dropdown components
    Given User is on HomePage
    When user hovers cursor on <navbar_elements>
    Then display navbar components
    
    Examples:
    | navbar_elements 		 													 	|
    | Electronics,Fashion,Sports,Industrial equipment |

  @Ignore
  Scenario: Successful navigation to appropriate page on clicking on an option
    Given User is on HomePage
    When user clicks on a navbar component
    Then navigate to the appropriate page

  @Ignore
  Scenario: Successful navigation to appropriate page on clicking on a link in footer
    Given User is on HomePage
    When user clicks on a footer link
    Then navigate to the appropriate page
