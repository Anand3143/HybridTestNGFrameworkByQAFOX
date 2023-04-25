package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterPage extends Base {
	
	public RegisterPage(){
		super();
	}
	
	WebDriver driver;
	
	Utilities ut = new Utilities();
	
	@FindBy(name = "firstname")
	private WebElement firstName;
	
	@FindBy(name = "lastname")
	private WebElement lastName;
	
	@FindBy(name = "email")
	private WebElement email;
	
	@FindBy(name = "telephone")
	private WebElement telePhone;
	
	@FindBy(name = "password")
	private WebElement passWord;
	
	@FindBy(name = "confirm")
	private WebElement confirmPassword;
	
	@FindBy(xpath = "//input[@name='newsletter' and @value='1']")
	private WebElement newsletterRadioButton;
	
	@FindBy(name = "agree")
	private WebElement termAgreeRadioButton;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailExistedAlreadyWarningMsg;
	
	public RegisterPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void firstName(){
		
		firstName.sendKeys(testDataProp.getProperty("firstName"));
	}
	
	public void lastName(){
		
		lastName.sendKeys(testDataProp.getProperty("lastName"));
	}
	
	public void email(){
		
		email.sendKeys(ut.generateEmailWithTimeStamp());
	}
	
	public void telephone(){
		
		telePhone.sendKeys(testDataProp.getProperty("telePhone"));
	}
	
	public void password(){
		
		passWord.sendKeys(prop.getProperty("password"));
	}
	
	public void confirmPassword(){
		
		confirmPassword.sendKeys(prop.getProperty("password"));
	}
	
	public boolean newletterRadioButton(){
		
		return newsletterRadioButton.isSelected();
	}

	public void newsletterRadioYesButton(){
		
		newsletterRadioButton.click();
	}
	
	public void termsAgreeRadioButton(){
		
		termAgreeRadioButton.click();
	}
	
	public void registerContinueButton(){
		
		continueButton.click();
	}
	
	
	
	public void validEmail(){
		
		email.sendKeys(prop.getProperty("validEmail"));
	}
	
	public String emailExistedWarningText(){
		
		String actualText = emailExistedAlreadyWarningMsg.getText();
		return actualText;
	}
	
	public String emailExistedExpectedMsg(){
		
		String expectedText = testDataProp.getProperty("duplicateEmailWarning");
		return expectedText;
	}
}
