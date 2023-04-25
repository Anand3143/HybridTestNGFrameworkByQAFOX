package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterAccountSuccssfulPage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

//Comment updated in RegisterTest class

public class RegisterTest extends Base {
	
	public RegisterTest(){
		super();
	}
	
	public WebDriver driver;
	
	Utilities ut = new Utilities();
	
	@BeforeMethod
	public void setUp(){
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickOnRegister();
	}
	
	@AfterMethod
	public void tearDown(){
		
		driver.close();
	}
	
	@Test(priority=0)
	public void verifyRegisterAnAccountWithMandatoryDetails(){
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstName();
		registerPage.lastName();
		registerPage.email();
		registerPage.telephone();
		registerPage.password();
		registerPage.confirmPassword();
		
		if(registerPage.newletterRadioButton()){
			registerPage.termsAgreeRadioButton();
			registerPage.registerContinueButton();
			
		}else {
			registerPage.newsletterRadioYesButton();
			registerPage.termsAgreeRadioButton();
			registerPage.registerContinueButton();
		}
		RegisterAccountSuccssfulPage registerAcc = new RegisterAccountSuccssfulPage(driver);
		Assert.assertEquals(registerAcc.successfullRegisterAccountActualText(), registerAcc.successfulRegisterAccountExpectedText(), "actual and expected are not same");
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithExistingEmail(){
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstName();
		registerPage.lastName();
		registerPage.validEmail();
		registerPage.telephone();
		registerPage.password();
		registerPage.confirmPassword();
		registerPage.newsletterRadioYesButton();
		registerPage.termsAgreeRadioButton();
		registerPage.registerContinueButton();
	
		Assert.assertTrue(registerPage.emailExistedWarningText().contains(registerPage.emailExistedExpectedMsg()), "warning message is not found");
		
	}

}
