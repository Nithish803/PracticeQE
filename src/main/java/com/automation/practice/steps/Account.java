package com.automation.practice.steps;

import java.io.IOException;
import java.util.Locale;

import com.automation.practice.pages.CommonPage;
import com.automation.practice.pages.AccountDataBuilder;
import com.automation.practice.utils.Buffer;
import com.github.javafaker.Faker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Account {

	CommonPage commonPage = new CommonPage();
	AccountDataBuilder adb;

	private AccountDataBuilder setTestData() throws IOException {
		Faker data = new Faker(new Locale("en-US"));
		AccountDataBuilder adb = new AccountDataBuilder();
		String email = data.internet().safeEmailAddress();
		adb.setStrEmail(email);
		Buffer.writeToBuffer("email", email);
		String fName = data.name().firstName();
		String lName = data.name().lastName();
		adb.setStrFirstName(fName);
		adb.setStrLastName(lName);
		adb.setStrFullName(fName + " " + lName);
		Buffer.writeToBuffer("fullName", fName + " " + lName);
		adb.setStrPassword(Buffer.readFromBuffer("pwd"));
		adb.setStrAddress(data.address().streetAddress());
		adb.setStrCity(data.address().city());
		adb.setStrState(data.address().state());
		adb.setStrZip(data.address().zipCode().replaceAll("-.*", ""));
		// adb.setStrCountry(data.address().country());
		adb.setStrMobilePhone(data.phoneNumber().cellPhone());
		return adb;
	};

	@Given("user open application")
	public void user_open_application() throws ClassNotFoundException, IOException {
		commonPage.launchApp("Open Application");
		adb = setTestData();
	}

	@When("navigate to sign in")
	public void navigate_to_sign_in() throws ClassNotFoundException, IOException {
		commonPage.getLandingPage().navigateToSignIn();
		commonPage.getLandingPage().navigateToCreateAccount(adb);
	}

	@When("register with mandetory details")
	public void register_with_mandetory_details() throws ClassNotFoundException, IOException {
		commonPage.getLandingPage().fillPersonalInformation(adb);
		commonPage.getLandingPage().fillAddressAndRegister(adb);
	}

	@Then("verify the new user")
	public void verify_the_new_user() throws ClassNotFoundException, IOException {

		if (adb != null) {
			commonPage.getLandingPage().verifyNewUser(adb.getStrFullName());
		} else {
			commonPage.getLandingPage().verifyNewUser(Buffer.readFromBuffer("fullName"));
		}

	}

	@Given("user login with registered account")
	public void user_login_with_registered_account() throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().login();
	}

	@When("user see {string} header")
	public void user_see_header(String expectedHeader) throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().verifyPageHeader(expectedHeader);
	}

	@When("user see {string} message")
	public void user_see_message(String expctedError) throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().verifyError(expctedError);
	}

	@Then("verify the error {string}")
	public void verify_the_error(String expctedErrorDetail) throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().verifyErrorDetails(expctedErrorDetail);
	}

	@Given("user enter email")
	public void user_enter_email() throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().enterCredentials("email", "");
	}

	@When("click on sign in")
	public void click_on_sign_in() throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().clickSignIn();
	}

	@Given("user enter password {string}")
	public void user_enter_password(String password) throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().enterCredentials("pwd", password);
	}

	@Given("user enter email {string}")
	public void user_enter_email(String email) throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().enterCredentials("email", email);
	}

	@Given("user enter password")
	public void user_enter_password() throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().enterCredentials("pwd", "");
	}

	@Given("user enter invalid email {string}")
	public void user_enter_invalid_email(String inValidEmail) throws ClassNotFoundException, IOException {
		commonPage.getLoginPage().enterCredentials("email", inValidEmail);
	}
}
