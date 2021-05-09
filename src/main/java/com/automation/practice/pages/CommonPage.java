package com.automation.practice.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.automation.practice.utils.Constants;
import com.automation.practice.utils.Processer;

public class CommonPage extends Processer {

	

	public LandingPage getLandingPage() {
		return new LandingPage();
	}

	public LoginPage getLoginPage() {
		return new LoginPage();
	}

	public void launchApp(String step) throws ClassNotFoundException, IOException {

		assertUpdateReport(navigateToURL(Constants.URL), step);

	}

}
