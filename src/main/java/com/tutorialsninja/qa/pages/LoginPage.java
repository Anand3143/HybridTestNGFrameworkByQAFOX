package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginPage extends Base {
	
	public LoginPage(){
		super();
	}
	
	WebDriver driver;
	
	Utilities ut = new Utilities();

	@FindBy(id = "input-email")
	WebElement emailAddress;
	
	@FindBy(id = "input-password")
	WebElement loginPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	WebElement actualWarningMessage;
	
	public LoginPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginWithValidEmail(String email){
		
		emailAddress.sendKeys(email);
		
	}
	
	public void loginWithValidPassword(String password){
		
		loginPassword.sendKeys(password);
		
	}
	
	public void clickOnLoginButton(){
		
		loginButton.click();
	}
	
	public void loginWithInvalidEmail(){
		
		emailAddress.sendKeys(ut.generateEmailWithTimeStamp());
		
	}
	
	public void loginWithInvalidPassword(){
		
		loginPassword.sendKeys(testDataProp.getProperty("invalidPasword"));
		
	}
	
	public String loginFailedActualWarningMessage(){
		
		String actualMessage = actualWarningMessage.getText();
		return actualMessage;
	}
	
	public String loginFailedExpectedWarningMessage(){
		
		String expectedMessage = testDataProp.getProperty("emailAndPassowrdWarning");
		return expectedMessage;
		
	}
	
	public void loginWithValidEmail(){
		
		emailAddress.sendKeys(prop.getProperty("validEmail"));
		
	}
	
	public void loginWithValidPassword(){
		
		loginPassword.sendKeys(prop.getProperty("password"));
		
	}
	
	public void loginWithoutEmail(){
		
		emailAddress.sendKeys(prop.getProperty("withouEmail"));
		
	}
	
	public void loginWithoutPassword(){
		
		loginPassword.sendKeys(prop.getProperty("withouPassword"));
		
	}
	
}
