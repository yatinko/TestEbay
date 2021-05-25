/**
 * 
 */
package PageFactory;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import TestPages.BaseClass;

/**
 * @author yatinko
 *
 */
public class Homepage_PF extends BaseClass {
	
	WebElement navComponent;
	
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
	
	@FindBy(how = How.CLASS_NAME, using = "hl-cat-nav__js-link")
	private List<WebElement> navCategoryLink;
	
	// Constructor for PageFactory
	public Homepage_PF() {
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

	public void hoverOverNavComponent(String[] navbar_elements) {
		// actions object for mousehover action
		Actions actions = new Actions(driver);
		
		for(String element : navbar_elements) {
			// Finding web element
			navComponent = driver.findElement(By.linkText(element));
			
			// Hovering over tab element
			actions.moveToElement(navComponent).perform();
		}
	}
}
