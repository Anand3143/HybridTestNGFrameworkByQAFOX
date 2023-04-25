package com.tutorialsninja.qa.testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login2Test extends Base {
	
	public Login2Test(){
		super();
	}
	
	public WebDriver driver;
	Utilities util = new Utilities();
	
	
	
	@BeforeMethod
	public void setUp(){
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickOnLogin();
		
	}
	
	@AfterMethod
	public void tearDown(){
		
		driver.close();
	}
	
	@Test(priority=1, dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password){
		LoginPage loginPage= new LoginPage(driver);
		loginPage.loginWithValidEmail(email);
		loginPage.loginWithValidPassword(password);
		loginPage.clickOnLoginButton();
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.verifyTextAfterSuccessfulLogin(), "Edit your account information is not found");
		
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() throws IOException{
		
		Object [][] data = util.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials(){
		LoginPage loginPage= new LoginPage(driver);
		loginPage.loginWithInvalidEmail();
		loginPage.loginWithInvalidPassword();
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.loginFailedActualWarningMessage().contains(loginPage.loginFailedExpectedWarningMessage()), "Expected warning massage is not found");
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword(){
		LoginPage loginPage= new LoginPage(driver);
		loginPage.loginWithInvalidEmail();
		loginPage.loginWithValidPassword();
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.loginFailedActualWarningMessage().contains(loginPage.loginFailedExpectedWarningMessage()), "Expected warning massage is not found");
		
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword(){
		LoginPage loginPage= new LoginPage(driver);
		loginPage.loginWithValidEmail();
		loginPage.loginWithInvalidPassword();
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.loginFailedActualWarningMessage().contains(loginPage.loginFailedExpectedWarningMessage()), "Expected warning massage is not found");
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingLoginCredentials(){
		LoginPage loginPage= new LoginPage(driver);
		loginPage.loginWithoutEmail();
		loginPage.loginWithoutPassword();
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.loginFailedActualWarningMessage().contains(loginPage.loginFailedExpectedWarningMessage()), "Expected warning massage is not found");
		
	}
	

}
