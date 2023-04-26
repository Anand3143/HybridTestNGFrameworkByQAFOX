package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.SearchPage;

//Comment Updated - added more details in the SearchTest class

public class SearchTest extends Base {
	
	public SearchTest(){
		super();
	}
	
	public WebDriver driver;

	@BeforeMethod
	public void setUp(){
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}
	
	@AfterMethod
	public void tearDown(){
		
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct(){
		
		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchWithValidData();
		searchPage.clickOnSearchButton();
		
		Assert.assertTrue(searchPage.validSearchResultPage(), "not found the valid results");
	
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct(){
		
		SearchPage searchPage = new SearchPage(driver);
		searchPage.searchWithInvalidProduct();
		searchPage.clickOnSearchButton();

		Assert.assertEquals(searchPage.invalidProductActualTest(), searchPage.invalidProductExpectedText(), "no products displyed in search result");
		
	}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct(){
		
		SearchPage searchPage = new SearchPage(driver);
		searchPage.clickOnSearchButton();
		
		Assert.assertEquals(searchPage.invalidProductActualTest(), searchPage.invalidProductExpectedText(), "no products displyed in search result");
	}
}
