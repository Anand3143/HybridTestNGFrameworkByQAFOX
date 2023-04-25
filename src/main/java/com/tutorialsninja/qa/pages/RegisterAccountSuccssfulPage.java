package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterAccountSuccssfulPage extends Base{
	
	public RegisterAccountSuccssfulPage(){
		super();
	}
	
	WebDriver driver;
	Utilities ut = new Utilities();
	
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	private WebElement successfulRegisterExpectedText;
	
	public RegisterAccountSuccssfulPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String successfullRegisterAccountActualText(){
		
		String actualtext = testDataProp.getProperty("accountSuccessfullyCreated");
		return actualtext;
	}
	
	public String successfulRegisterAccountExpectedText(){
		
		String expectedText = successfulRegisterExpectedText.getText();
		return expectedText;
	}

}
