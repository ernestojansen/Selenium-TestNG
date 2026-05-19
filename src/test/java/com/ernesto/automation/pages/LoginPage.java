package com.ernesto.automation.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;
	
	By username = By.cssSelector("input[placeholder='Username']");
	By password = By.cssSelector("input[placeholder='Password']");
	By loginButton = By.cssSelector("button[type='submit']");
	By dashboardTitle = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
	By invalidCredentialsMessage = By.xpath("//p[text()='Invalid credentials']");
	By forgotPasswordButton = By.xpath("//p[text()='Forgot your password? ']");
	By requiredMessage = By.xpath("//span[text()='Required']");
	
	public LoginPage (WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	
	public void login(String user, String pass) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(user);
		
		driver.findElement(password).sendKeys(pass);
		
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}
	
	public String getDashboardText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle)).getText();
	}
	
	public String getInvalidCredentialsText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentialsMessage)).getText();
	}
	
	public void clickForgotButton() {
		wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton)).click();
	}
	
	public String getForgotPasswordPageURL() {
		return driver.getCurrentUrl();
	}
	
	public String getEmptyFieldsText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessage)).getText();
	}

}