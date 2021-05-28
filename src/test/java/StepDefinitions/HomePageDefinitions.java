/**
 * 
 */
package StepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import PageFactory.HomePage_PF;
import TestPages.BaseClass;
import TestPages.ExcelReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author yatinko
 *
 */
public class HomePageDefinitions extends BaseClass {
	HomePage_PF home;
	ExcelReader reader = new ExcelReader();
	SoftAssert softAssertion = new SoftAssert();
	String searchItemText, searchCategoryText;
	String navbarComponent;
	
	@Before
	public void setUp() {
		if(driver == null ) {
			initialization();
		}
		home = new HomePage_PF();
	}
	
	@After
	public void wrapUp() {
		System.out.println("==================================================================================================================================================================");
	}

	@Given("User is on HomePage")
	public void user_is_on_home_page() {
		driver.get(prop.getProperty("homepageUrl"));	
		
		// Getting navbar home component's text
		String navHomeSpanString = home.getHomeNavText();
		System.out.println(navHomeSpanString);
	    
		// Check if the home component is active
	   	Assert.assertEquals(navHomeSpanString, "Home");
	   	System.out.println("User is on homepage");
	}

	@Then("check the title of the page")
	public void check_the_title_of_the_page() {
		// Getting the page title
		String title = driver.getTitle();
		
		// Checking if the title is correct
	    Assert.assertEquals("Electronics, Cars, Fashion, Collectibles & More | eBay", title);
	    System.out.println("Title matched");
	}

	@When("user enters search item and search category in searchbar from {int} in {string}")
	public void user_enters_search_item_and_search_category_in_searchbar_from_rowNumber_in_sheetName( int rowNumber, String sheetName) throws InvalidFormatException, IOException {
        List<Map<String,String>> testData = reader.getData(prop.getProperty("excel_sheet_path"), prop.getProperty(sheetName));

        // Saving the search items in the local variables
        searchItemText = testData.get(rowNumber).get("searchItem");
        String searchCategory = testData.get(rowNumber).get("searchCategory");

     	searchCategoryText = "Selected category\n" + searchCategory;
     		
		// Entering the data in the searchbar
		home.enterSearchText(searchItemText);
		home.selectSearchCategory(searchCategory);
	}

	@And("clicks the Search button")
	public void clicks_the_search_button() {
		// Clicking on the search button
		home.searchProduct();
	}

	@Then("display relevant products")
	public void display_relevant_products() {
		// Retrieving total number of products found
		int totalProducts = home.checkTotalProducts();
		
		// If the searched product is found, continue
		if(totalProducts > 0) {
			// Verifying if results displayed is of the product searched
			String searchResultText = home.checkIfProductIsCorrect();
			String searchCategory = home.checkIfProductCategoryIsCorrect();
		   
			Assert.assertEquals(searchResultText , searchItemText);
			Assert.assertEquals(searchCategory , searchCategoryText);
		}
	}

	@Then("display appropriate message")
	public void display_appropriate_message() {
		// Retrieving total number of products found
		int totalProducts = home.checkTotalProducts();
		
		// If the searched product is found, continue
		if(totalProducts == 0) {
			// VerifyinString navbarg if results displayed is of the product searched
			String searchResultText = home.checkIfProductIsCorrect();
			String warning = home.checkNoProductFoundWarning();
			   
			Assert.assertEquals(searchResultText , searchItemText);
			Assert.assertEquals(warning, "No exact matches found");
		}
	}

	@When("user hovers cursor on navbar element from {int} in {string}")
	public void user_hovers_cursor_on_navbar_element_from_rowNumber_in_sheetName(int rowNumber, String sheetName) throws InvalidFormatException, IOException {
		List<Map<String,String>> testData = reader.getData(prop.getProperty("excel_sheet_path"), prop.getProperty(sheetName));

        // Saving the search items in the local variables
        String navbarElement = testData.get(rowNumber).get("navbarElement");
        navbarComponent = testData.get(rowNumber).get("navbarComponent");
		
		// Hovering over components
	    home.hoverOverNavComponent(navbarElement);
	}

	@Then("display navbar components")
	public void display_navbar_components() {
		// Retrieving CSS property of navbar element
		WebElement navPanel = driver.findElement(By.cssSelector("li.hl-cat-nav__js-show"));
		String display = navPanel.getCssValue("display");
		
		// Asserting the element property
		softAssertion.assertEquals(display, "block");
	}

	@Then("user clicks on a navbar component")
	public void user_clicks_on_a_navbar_component() {
		home.clickOnLink(navbarComponent);
	}

	@Then("navigate to the appropriate page")
	public void navigate_to_the_appropriate_page() {
		home.checkPage();
	}

	@When("user clicks on a footer link from {int} in {string}")
	public void user_clicks_on_a_footer_link_from_rowNumber_in_sheetName(int rowNumber, String sheetName) throws InvalidFormatException, IOException {
		List<Map<String,String>> testData = reader.getData(prop.getProperty("excel_sheet_path"), prop.getProperty(sheetName));

        // Saving the search items in the local variables
        String element = testData.get(rowNumber).get("element");
        
        home.clickOnLink(element);
	}
	
	@Given("user wants to quit the browser")
	public void user_wants_to_quit_the_browser() {
		driver.quit();
	}
}
