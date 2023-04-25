package com.tutorialsninja.qa.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;



public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;
	
	public void onStart(ITestContext context) {
		
		try {
			extentReport = ExtentReporter.generateExtendReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() +" Started execution");

	}
	
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, result.getName() +" Got successfully executed");
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}
	
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getTestName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+ " Got failed");
		
		
	}
	
	public void onTestSkipped(ITestResult result) {
		
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"Got skipped");
		
	}

	
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		String pathOfTheExtentReports = System.getProperty("user.dir")+"\\test-output\\extentReports\\extentReport.html";
		File extendReport = new File(pathOfTheExtentReports);
		try{
		Desktop.getDesktop().browse(extendReport.toURI());
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	

	

	
	

	

	

	
}
