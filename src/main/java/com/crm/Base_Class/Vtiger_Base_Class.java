package com.crm.Base_Class;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.crm.FileUtility.AccessBrowserAndUrl;
import com.crm.Pom.LoginRepo;
import com.crm.Pom.LogoutRepo;

public class Vtiger_Base_Class {
	public static WebDriver driver = null;	
	public static Properties prop;
    public static AccessBrowserAndUrl browser;
	
	@BeforeSuite
	public void  beforeSuite() 
	{
		System.out.println("BeforeSuite");

	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("BeforeTest");
	}
	
	
	@BeforeClass
	public static void preCondition() throws IOException {


		
		browser = new AccessBrowserAndUrl();
		String br = browser.accessBrowser("browser");
		
		
		if(br.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(br.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		}
		else if(br.equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		String url = browser.accessBrowser("url");
		driver.get(url);
	}	
	
	@BeforeMethod
	public static void login() throws IOException 
	{
		LoginRepo loginrepo = new LoginRepo(driver);
		loginrepo.login();
	}
	
	@AfterMethod
	public static void logout()
	{
		LogoutRepo lr = new LogoutRepo(driver);
		lr.logout_icon().click();
		lr.logout().click();
	}
	
	

	@AfterClass
	public static void postCondition()
	{
		driver.quit();
	}
	@AfterTest
	public void afterTest()
	{
		System.out.println("AfterTest");
	}
	
	
	@AfterSuite
	public void  afterSuite()
	{
		System.out.println("AfterSuite");
	}
	
	
}

