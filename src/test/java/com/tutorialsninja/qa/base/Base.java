package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	WebDriver driver;
	
	Utilities ut = new Utilities();
	public Properties prop;
	public Properties testDataProp;
	
	public Base(){
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		testDataProp = new Properties();
		File testDataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		
		try{
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
			
			FileInputStream testDataFis = new FileInputStream(testDataPropFile);
			testDataProp.load(testDataFis);
			
		}catch(Throwable e){
			e.printStackTrace();
		}
			
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName){
		
		if(browserName.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
			else if(browserName.equalsIgnoreCase("firefox")){
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		
		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ut.PAGE_LOAD_TIME));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ut.IMPLICIT_WAIT_TIME));
		driver.get(prop.getProperty("URL"));
		return driver;
		
	}

}
