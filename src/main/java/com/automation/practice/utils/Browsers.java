package com.automation.practice.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;

public class Browsers extends AbstractTestNGCucumberTests {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}

	public static String timestamp() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
		String reportdate = simpleDateFormat.format(date);
		return reportdate;
	}

	@BeforeTest(alwaysRun = true)
	public void setUp() throws IOException {
		System.setProperty(Constants.EXTENT_KEY, Constants.EXTENT_PATH);
		System.setProperty(Constants.LOG4J_KEY, Constants.LOG4J_PATH);
		String browser;
		if (System.getProperty("browser")!=null) {
			browser=System.getProperty("browser");
		}else {
			browser=Buffer.readFromBuffer("browser");
		}
		switch (browser) {
		case "chrome":
			System.setProperty(Constants.CHROME_KEY, Constants.CHROME_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			System.setProperty(Constants.FIREFOX_KEY, Constants.FIREFOX_PATH);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		}
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		// driver.close();
		 driver.quit();
	}
}
