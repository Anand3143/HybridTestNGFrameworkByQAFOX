package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	//Object or Elements
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	public HomePage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions for the above elements
	
	public void clickOnMyAccount(){
		
		myAccountDropMenu.click();
	}
	
	public void clickOnLogin(){
		
		loginOption.click();
	}
	
	public void clickOnRegister(){
		
		registerOption.click();
	}

}
