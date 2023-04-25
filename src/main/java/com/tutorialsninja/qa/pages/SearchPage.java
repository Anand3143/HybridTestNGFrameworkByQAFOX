package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.base.Base;

public class SearchPage extends Base {
	
	public SearchPage(){
		super();
	}

	public WebDriver driver;
	
	@FindBy(name = "search")
	private WebElement searchBox;
	
	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validSearchResult;
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement invalidProductActualMsg;
	
	
	
	public SearchPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchWithValidData(){
		
		searchBox.sendKeys(testDataProp.getProperty("searchWithValidProduct"));
	}
	
	public void clickOnSearchButton(){
		
		searchButton.click();
	}
	
	public boolean validSearchResultPage(){
		
		boolean validText = validSearchResult.isDisplayed();
		return validText;
	}
	
	public void searchWithInvalidProduct(){
		
		searchBox.sendKeys(testDataProp.getProperty("searchWithInvalidProduct"));
	}
	
	public String invalidProductActualTest(){
		
		String actualText = invalidProductActualMsg.getText();
		return actualText;
	}
	
	public String invalidProductExpectedText(){
		
		String expectedText = testDataProp.getProperty("searchProductNotMatching");
		return expectedText;
	}
}
