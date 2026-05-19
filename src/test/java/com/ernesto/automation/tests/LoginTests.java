package com.ernesto.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ernesto.automation.base.BaseTest;
import com.ernesto.automation.pages.LoginPage;
import com.ernesto.automation.testdata.TestData;


public class LoginTests extends BaseTest {
	
	@Test(priority = 1, description = "Verify user can login witn valid credentials")
	public void successfullLogin() {
		
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.login("Admin", "admin123");
		
		String actualTitle = loginPage.getDashboardText();
		String expectedTitle = "Dashboard";
		
		Assert.assertEquals(actualTitle, expectedTitle, "El login no redirigió al Dashboard esperado");
		
		System.out.println("Login successful");	
	}
	
	@Test(dataProvider = "loginData", dataProviderClass = TestData.class)
	public void invalidCredentials(String username, String password) {
		
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.login(username, password);
	
		String actualTitle = loginPage.getInvalidCredentialsText();
		String expectedTitle = "Invalid credentials";
		
		Assert.assertEquals(actualTitle, expectedTitle);
		
		System.out.println("Login failed");
	}
	
	@Test(groups = {"smoke"})
	public void resetPassword() {
		
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.clickForgotButton();
	
		String actualURL = loginPage.getForgotPasswordPageURL();
		String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
		
		Assert.assertEquals(actualURL, expectedURL, "La redirección a 'Forgot your password?' falló");	
}
	
	@Test(groups = {"smoke","regression"})
	public void emptyFields() {
		
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.login("", "");
		
		String actualText = loginPage.getEmptyFieldsText();
		String expectedText = "Required";
		
		Assert.assertEquals(actualText, expectedText);	
}

	@Test
	public void caseSensitivityCredentials() {
		
		LoginPage loginPage = new LoginPage(getDriver());
		
		loginPage.login("ADMIN", "ADMIN123");
		
		String actualTitle = loginPage.getInvalidCredentialsText();
		String expectedTitle = "Invalid credentials";
		
		Assert.assertEquals(actualTitle, expectedTitle);
		
		System.out.println("Login failed");
}

}