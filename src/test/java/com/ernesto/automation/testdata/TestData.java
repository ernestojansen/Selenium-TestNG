package com.ernesto.automation.testdata;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name = "loginData")
	public Object[][] getLoginData(){
		
		return new Object[][] {
			
			{"Jhon", "Password123"},
			{"Tester", "password123"},
			{"Admin", "badPass"}
		};
	}
}
