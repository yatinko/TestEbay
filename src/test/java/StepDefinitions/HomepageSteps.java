/**
 * 
 */
package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import PageFactory.Homepage_PF;
import TestPages.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author yatinko
 *
 */
public class HomepageSteps extends BaseClass {
	Homepage_PF home;
	String searchItemText, searchCategoryText;
	
	@Before
	public void setUp() {
		initialization();
		home = new Homepage_PF();
	}
	
	@After
	public void wrapUp() {
		driver.close();
	}

	@Given("User is on HomePage")
	public void user_is_on_home_page() {
		System.out.println("==================================================================================================================================================================");
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

	@When("user enters {string} and {string} in searchbar")
	public void user_enters_searchItem_and_searchCategory_in_searchbar(String searchItem, String searchCategory) {
		// Saving the search items in the local variables
		searchItemText = searchItem;
		searchCategoryText = "Selected category\n" + searchCategory;
		
		// Entering the data in the searchbar
		home.enterSearchText(searchItem);
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
			// Verifying if results displayed is of the product searched
			String searchResultText = home.checkIfProductIsCorrect();
			String warning = home.checkNoProductFoundWarning();
			   
			Assert.assertEquals(searchResultText , searchItemText);
			Assert.assertEquals(warning, "No exact matches found");
		}
	}

	@When("user hovers cursor on {String}")
	public void user_hovers_cursor_on_navbar_elements(String navbar_element) {
		String[] navbar_elements = navbar_element.split(",");
	    home.hoverOverNavComponent(navbar_elements);
	}

	@Then("display navbar components")
	public void display_navbar_components() {
		
	}

	@When("user clicks on a navbar component")
	public void user_clicks_on_a_navbar_component() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("navigate to the appropriate page")
	public void navigate_to_the_appropriate_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("user clicks on a footer link")
	public void user_clicks_on_a_footer_link() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
