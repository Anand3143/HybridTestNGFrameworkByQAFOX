package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtendReport() throws IOException{
		
		ExtentReports extendReports = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\extentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorialsninja Test Automation Results");
		sparkReporter.config().setDocumentTitle("TN Automation report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extendReports.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		File configPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fisConfigProp = new FileInputStream(configPropFile);
		configProp.load(fisConfigProp);
		
		
		extendReports.setSystemInfo("Application URL", configProp.getProperty("URL"));
		extendReports.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extendReports.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extendReports.setSystemInfo("Password", configProp.getProperty("password"));
		extendReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extendReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extendReports.setSystemInfo("Java version", System.getProperty("java.version"));
		
		return extendReports;
	}
}
