package com.ernesto.automation.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ernesto.automation.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	//ThreadLocal Driver
		private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
		
		ConfigReader config = new ConfigReader();
		
		//Getter del driver
		public WebDriver getDriver() {
			
			return tlDriver.get();
		}
	
	@BeforeMethod(alwaysRun = true)
	public void setup() {
		
		String browser = config.getBrowser();
		
		String url = config.getURL();
		
		int timeout = config.getTimeout();
		
		boolean headless = config.isHeadless();
		
		//CHROME
		if(browser.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			
			if(headless) {
				
				options.addArguments("--headless");
			}
			
			tlDriver.set(new ChromeDriver(options));
		}
		
		//FIREFOX
		else if(browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			
			FirefoxOptions options = new FirefoxOptions();
			
			if(headless) {
				
				options.addArguments("--headless");
			}
			
			tlDriver.set(new FirefoxDriver(options));
		}
		
		else {
			throw new RuntimeException("Browser not supported: " + browser);
		}
		getDriver().manage().window().maximize();
		
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
		
		getDriver().get(url);
	}

	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		
		if(getDriver() != null) {
			
			getDriver().quit();
			
			tlDriver.remove();
		}
	}
}
