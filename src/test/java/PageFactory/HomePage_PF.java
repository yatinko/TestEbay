/**
 * 
 */
package PageFactory;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import StepDefinitions.HomePageDefinitions;
import TestPages.BaseClass;

/**
 * @author yatinko
 *
 */
public class HomePage_PF extends BaseClass {
	
	WebElement navComponent;
	WebElement link;
	String parent;
	String actualUrl;
	WebDriverWait wait=new WebDriverWait(driver, 20);
	Actions actions = new Actions(driver);
	HomePageDefinitions steps = new HomePageDefinitions();
	
	// WebElement components
	@FindBy(xpath = "//*[@id=\"mainContent\"]/div[1]/ul/li[1]/span")
	WebElement navHomeComponent;
	
	@FindBy(name = "_nkw")
	WebElement searchBarText;
	
	@FindBy(className = "gh-sb")
	WebElement searchBarCategory;
	
	@FindBy(id = "gh-btn")
	WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"mainContent\"]/div[1]/div/div[2]/div[1]/div[1]/h1/span[1]")
	WebElement totalResults;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"mainContent\"]/div[1]/div/div[2]/div[1]/div[1]/h1/span[2]")
	WebElement searchResultElement;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"x-refine__group__0\"]/ul/li/ul/li[1]/span")
	WebElement searchResultCategory;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"s0-14-11-6-3-save_search1-answer-17\"]/div[1]/h3")
	WebElement searchResultDiv;
	
	// Constructor for PageFactory
	public HomePage_PF() {
		PageFactory.initElements(driver, this);
	}
	
	public String getHomeNavText() {
	   	return navHomeComponent.getText();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void quit() {
		driver.quit();
	}

	public void enterSearchText(String validSearchItem) {
		searchBarText.sendKeys(validSearchItem);
	}
	
	public void selectSearchCategory(String validSearchCategory) {
		Select searchDropDown = new Select(searchBarCategory);
		searchDropDown.selectByVisibleText(validSearchCategory);
	}

	public void searchProduct() {
		searchButton.click();
	}

	public int checkTotalProducts() {
		String totalResultsString = totalResults.getText().replace(",", "");
		return Integer.parseInt(totalResultsString);
	}

	public String checkIfProductIsCorrect() {
		return searchResultElement.getText();
	}
	
	public String checkIfProductCategoryIsCorrect() {
		return searchResultCategory.getText();
	}

	public String checkNoProductFoundWarning() {
		return searchResultDiv.getText();
	}

	public void hoverOverNavComponent(String navbarElement) {
		// Finding web element
		navComponent = driver.findElement(By.partialLinkText(navbarElement));
			
		// Hovering over tab element
		actions.moveToElement(navComponent).perform();
			
		// Wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hl-cat-nav__js-show")));
			
		// Check if nav panel appeared
		steps.display_navbar_components();
	}

	public void clickOnLink(String component) {
		// wait of 5 seconds
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    
	    // Retrieving URL for the element
	 	String url = driver.findElement(By.linkText(component)).getAttribute("href");
	 	actualUrl = url.toLowerCase();
	 		
	    // open the link
//		driver.findElement(By.linkText(component)).click();
	 	driver.navigate().to(actualUrl);
	}
	
	public void switchThroughTabs() {
		// Switching through tabs
//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		
//		driver.switchTo().window(tabs.get(1));
		
		String url = driver.getCurrentUrl();
		String currentUrl = url.toLowerCase();
		Assert.assertEquals(currentUrl, actualUrl);
		
		// wait of 5 seconds
//	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	    
//	    driver.close();
//	    
//	    // Go back to parent
//	    driver.switchTo().window(parent);

//		for(int i = 1; i < tabs.size(); i++) {
//			// Go to the first child window
//			driver.switchTo().window(tabs.get(i));
//			
//			String currentUrl = driver.getCurrentUrl();
//			System.out.println(i);
//			System.out.println(currentUrl);
//			System.out.println(urlList.get(i-1));
//			Assert.assertEquals(currentUrl, urlList.get(i-1));
//			
//			// wait of 5 seconds
//		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		    
//		    // Got back to parent
//		    driver.switchTo().window(parent);
//		}
		
	}
	
}
