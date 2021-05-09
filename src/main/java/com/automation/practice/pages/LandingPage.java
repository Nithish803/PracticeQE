package com.automation.practice.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LandingPage extends CommonPage {

	

	By link_SignIn = By.className("login");
	By hdr_page = By.cssSelector(".page-heading");

	public void navigateToSignIn() throws ClassNotFoundException, IOException {

		if (Size(link_SignIn) == 1 && IsEnabled(link_SignIn)) {
			click(link_SignIn, "Sign In Link");
		}
	}

	By tbx_CreateEmail = By.id("email_create");
	By btn_CreateAccount = By.id("SubmitCreate");

	public void navigateToCreateAccount(AccountDataBuilder rdb) throws ClassNotFoundException, IOException {
		assertUpdateReport(typeText(tbx_CreateEmail, rdb.getStrEmail(), "Email Address TextBox"),"Enter Email Address");
		assertUpdateReport(click(btn_CreateAccount, "Create an account Button"), "Click on Create an Account");
	}

	public By getYourPersonalInformationTextField(String fieldName) {
		return By.xpath("//h3[.='Your personal information']/..//label[contains(.,'" + fieldName + "')]/..//input");
	}

	public By getYourPersonalInformationTextFieldCheck(String fieldName) {
		return By.xpath("//h3[.='Your personal information']/..//label[contains(.,'" + fieldName + "')]/..//input/..");
	}

	public By getYourAddressTextField(String fieldName) {
		return By.xpath("//h3[.='Your address']/..//label[contains(.,'" + fieldName + "')]/..//input");
	}

	public By getYourAddressDropdown(String fieldName) {
		return By.xpath("//h3[.='Your address']/..//label[contains(.,'" + fieldName + "')]/..//select");
	}

	By btn_Register = By.id("submitAccount");

	public void fillPersonalInformation(AccountDataBuilder rdb) throws ClassNotFoundException, IOException {
		waitForElementToBeClickable(getYourPersonalInformationTextField("First"));
		assertUpdateReport(typeText(getYourPersonalInformationTextField("First"), rdb.getStrFirstName(), "First Name TextBox"),"Enter FirstName");
		assertUpdateReport(typeText(getYourPersonalInformationTextField("Last"), rdb.getStrLastName(), "Last Name TextBox"),"Enter LastName");
		assertUpdateReport(typeText(getYourPersonalInformationTextField("Password"), rdb.getStrPassword() + Keys.TAB,"Password TextBox"),"Enter Password");
		assertUpdateReport(getAttribute(getYourPersonalInformationTextFieldCheck("First"), "class").contains("ok"),"Check FirstName");
		assertUpdateReport(getAttribute(getYourPersonalInformationTextFieldCheck("Last"), "class").contains("ok"),"Check LastName");			
		assertUpdateReport(getAttribute(getYourPersonalInformationTextFieldCheck("Password"), "class").contains("ok"),"Check Password");					
						
	}

	public void fillAddressAndRegister(AccountDataBuilder rdb) throws ClassNotFoundException, IOException {
		assertUpdateReport(typeText(getYourAddressTextField("Address"), rdb.getStrAddress(), "Address TextBox"),"Enter Address");
		assertUpdateReport(typeText(getYourAddressTextField("City"), rdb.getStrCity(), "City TextBox"), "Enter City");
		assertUpdateReport(selectIfOptionTextEquals(getYourAddressDropdown("State"), rdb.getStrState()),"Select State");
		assertUpdateReport(typeText(getYourAddressTextField("Zip"), rdb.getStrZip(), "Zip TextBox"), "Enter Zip");
		assertUpdateReport(typeText(getYourAddressTextField("Mobile"), rdb.getStrMobilePhone(), "Mobile Phone TextBox"),"Enter Mobile Phone");
		assertUpdateReport(click(btn_Register, "Register Button"), "Click on Register");
	}

	By link_SignOut = By.className("logout");
	By link_ViewAccount = By.cssSelector("a[title='View my customer account']");

	public void verifyNewUser(String userName) throws ClassNotFoundException, IOException {
		assertUpdateReport(getText(link_ViewAccount, "View Account Link"), userName, "Verify AccountName");
		highlightElement(link_ViewAccount);
		assertUpdateReport(click(link_SignOut, "Sign Out Button"), "Click on Sign Out");
	}
	
}
