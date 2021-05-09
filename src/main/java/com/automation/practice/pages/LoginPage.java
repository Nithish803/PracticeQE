package com.automation.practice.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.practice.utils.Buffer;

public class LoginPage extends CommonPage {

	

	By tbx_EmailAddress = By.id("email");
	By tbx_Password = By.cssSelector("input[type='password']");
	By btn_SignIn = By.id("SubmitLogin");
	By hdr_page = By.cssSelector(".page-heading");
	By lbl_Error = By.cssSelector("h1+div[class*='alert'] p");
	By lbl_EmailDeatils = By.cssSelector("h1+div[class*='alert'] ol li");

	public void login() throws ClassNotFoundException, IOException {
		assertUpdateReport(typeText(tbx_EmailAddress, Buffer.readFromBuffer("email"), "Email Address TextBox"),
				"Enter Email Address");
		assertUpdateReport(typeText(tbx_Password, Buffer.readFromBuffer("pwd"), "Password TextBox"), "Enter Password");
		assertUpdateReport(click(btn_SignIn, "Create an account Button"), "Click on SignIn");
	}

	public void verifyPageHeader(String expHeader) throws ClassNotFoundException, IOException {
		assertUpdateReport(getText(hdr_page, "Page Header"), expHeader, "Verify page header");
	}

	public void enterCredentials(String fieldName, String value) throws ClassNotFoundException, IOException {
		if (value.equals("")) {
			value = Buffer.readFromBuffer(fieldName);
		}
		if (fieldName.equals("email")) {
			assertUpdateReport(typeText(tbx_EmailAddress, value, "Email Address TextBox"), "Enter Email Address");
		} else {
			assertUpdateReport(typeText(tbx_Password, value, "Password TextBox"), "Enter Password");
		}
	}

	public void clickSignIn() throws ClassNotFoundException, IOException {
		assertUpdateReport(click(btn_SignIn, "Create an account Button"), "Click on SignIn");
	}

	public void verifyError(String expError) throws ClassNotFoundException, IOException {
		waitForElementTextToBe(lbl_Error, expError);
		assertUpdateReport(getText(lbl_Error, "Error Message"), expError, "Verify Error");
		highlightElement(lbl_Error);

	}

	public void verifyErrorDetails(String expErrorDetail) throws ClassNotFoundException, IOException {
		waitForElementTextToBe(lbl_EmailDeatils, expErrorDetail);
		assertUpdateReport(getText(lbl_EmailDeatils, "Error Message"), expErrorDetail, "Verify Error");
		highlightElement(lbl_EmailDeatils);
		clearField(tbx_EmailAddress);
		clearField(tbx_Password);
	}

}
