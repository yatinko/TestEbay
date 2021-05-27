#Author: yatinko
@HomePage
Feature: HomePage

  @Test @Sc_01
  Scenario: check the title
    Given User is on HomePage
    Then check the title of the page

  @Test @Sc_02
  Scenario Outline: Successful display of relevant products
    Given User is on HomePage
    When user enters search item and search category in searchbar from <rowNumber>
    And clicks the Search button
    Then display relevant products
  
	Examples:
      | sheetName 						| rowNumber |
      |	valid_search_component_sheet	| 0		    |
      | valid_search_component_sheet	| 1		    |

  @Test @Sc_03
  Scenario Outline: Failure finding products
    Given User is on HomePage
    When user enters search item and search category in searchbar from <rowNumber> in "<sheetName>"
    And clicks the Search button
    Then display appropriate message
    	
  	Examples:
      | sheetName 						| rowNumber |
      |	invalid_search_component_sheet	| 0		    |
      | invalid_search_component_sheet	| 1		    |

  @Test @Sc_04
  Scenario Outline: Successful display of dropdown components
    Given User is on HomePage
    When user hovers cursor on navbar element from <rowNumber> in "<sheetName>"
    Then display navbar components
    Then user clicks on a navbar component
    Then navigate to the appropriate page
    
    Examples:
    | sheetName 					| rowNumber |
    | navbar_component_excel_sheet	| 0			|
    | navbar_component_excel_sheet	| 1			|
    | navbar_component_excel_sheet	| 2			|
	| navbar_component_excel_sheet  | 3			|

  @Test @Sc_05
  Scenario Outline: Successful navigation to appropriate page on clicking on a link in footer
    Given User is on HomePage
    When user clicks on a footer link from <rowNumber> in "<sheetName>"
    Then navigate to the appropriate page
	
	Examples:
	| sheetName 					| rowNumber |
    | footer_component_excel_sheet	| 0			|
    | footer_component_excel_sheet	| 1			|
    | footer_component_excel_sheet	| 2			|