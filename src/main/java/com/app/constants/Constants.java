package com.app.constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Constants {
	
	public static AndroidDriver<AndroidElement> driver = null;
	public static ExtentReports ExecutionReports = new ExtentReports("C:\\Users\\ExecutionReports.html");
	public static ExtentTest TestReports ;
	
	
	public static Properties loadProperties() throws Exception 
	{
	File file = new File("src/main/resources/Accuweather.properties");
	FileInputStream fileInput = null;
	
	try {
		fileInput = new FileInputStream(file);
	}catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	Properties prop = new Properties();
	try {
		prop.load(fileInput);
	}catch (Exception e) {
		e.printStackTrace();
	}
	 return prop;
	}

}
