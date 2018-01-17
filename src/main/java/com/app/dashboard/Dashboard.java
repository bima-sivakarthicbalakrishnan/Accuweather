package com.app.dashboard;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.app.constants.Constants;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Dashboard extends Constants {

	public static AppiumDriverLocalService service;

	/**
	 * Accuweather app: TC to launch the Mobile App
	 * 
	 */
	@BeforeTest
	public void launchDevice() {
		try {
			Properties prop = Constants.loadProperties();
			File appDir = new File("APK_Folder");
			
			//Step 1 - Opening the app
			
			File app = new File(appDir, "AccuWeather-4.3.7-free.apk");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
			cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			Constants.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			System.out.println("The AccuWeather-4.3.7-free.apk is open");
			Constants.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Constants.TestReports = Constants.ExecutionReports.startTest("Current Condition Page TESTING");
			Constants.TestReports.log(LogStatus.PASS, "AccuWeather: TC to launch the Mobile App");
		} catch (Exception E) {
			System.out.println(E);
			System.out.println("AccuWeather: TC to launch the Mobile App: FAIL");
			Constants.TestReports.log(LogStatus.FAIL, "AccuWeather: TC to launch the Mobile App");
		}
	}

	/**
	 * AccuWeather: TC to call all the methods of AboutUs methods
	 */
	@Test
	public void AboutUs() {
		try {
			
			
			Constants.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			
			//Step 2 - Clicking the hamburger icon
			
			Constants.driver.findElementByXPath(prop.getProperty("hamburger_icon")).click();
			
			
			//Step 3 - Search for Bengaluru

			Constants.driver.findElementById(prop.getProperty("search_icon")).click();
			
			Constants.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			Constants.driver.findElementByXPath(prop.getProperty("search_editbox")).sendKeys("bengaluru");
			
			Constants.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			Constants.driver.findElementByXPath(prop.getProperty("selecting_bengaluru")).click();
			
			
			
			//Step 4 - clicking current condition  
			
			Constants.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			Constants.driver.findElementByXPath(prop.getProperty("Selecting_currentcond")).click();

            //Verifying weather current condtion elements are present or not
			
			Constants.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			String cloudcover = Constants.driver.findElementByXPath(prop.getProperty("cloud_cover")).getText();
			Assert.assertEquals("CLOUD COVER", cloudcover);
			
			String windsfrom = Constants.driver.findElementByXPath(prop.getProperty("winds_from")).getText();
			Assert.assertEquals("WINDS FROM", windsfrom);
			
			String windgusts = Constants.driver.findElementByXPath(prop.getProperty("wind_gusts")).getText();
			Assert.assertEquals("WIND GUSTS", windgusts);
			
			
			Constants.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
			Constants.TestReports.log(LogStatus.PASS, "AccuWeather: TC to find current conditions");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL, "AccuWeather: TC to find current conditions");
		}
	}

	@AfterTest
	public void tearDown() {
		Constants.ExecutionReports.endTest(Constants.TestReports);
		Constants.ExecutionReports.flush();
		Constants.driver.closeApp();
		service.stop();
	}

}
