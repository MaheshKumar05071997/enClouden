package com.qa.ct.juducialbranch.resources;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.ct.juducialbranch.configFiles.constantValues;
import com.qa.ct.juducialbranch.utils.ExcelUtil;

public class BaseTest {

	public ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public ExcelUtil excelUtil;

	// setter
	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	// getter
	public WebDriver getDriver() {
		return this.driver.get();
	}

	@BeforeTest
	public void beforeTest() {
		excelUtil = new ExcelUtil();
	}

	@BeforeMethod
	@Parameters(value = { "browserName" })
	public void beforeMethod(String browserName, Method testCaseName) {
		openBrowser(browserName);

		getDriver().get(constantValues.URL);
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(constantValues.IMPLICIT_WAIT, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(constantValues.PAGE_LOAD, TimeUnit.SECONDS);

	}
	
	@AfterMethod
	public void afterMethod()
	{
		//getDriver().quit();
	}

	public void openBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			setDriver(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/drivers/gecko.exe");
			setDriver(new FirefoxDriver());
		}
	}
}
