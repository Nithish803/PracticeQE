package com.automation.practice.testrunner;

import com.automation.practice.utils.Browsers;
import com.automation.practice.utils.Constants;

import io.cucumber.testng.CucumberOptions;


	@CucumberOptions
	(
			features = { "src/main/java/com/automation/practice/features/Account.feature" },
			glue = {"com.automation.practice.steps" },
			tags = "@smoke",
			monochrome = true,
			dryRun = false,
			plugin = { "pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }

	)
	
	public class AccountTest extends Browsers{
		static String reportPath = "test-output/reports/AccountTest-" + timestamp() + "/";
		static {
			System.setProperty(Constants.HTML_START_KEY, Constants.HTML_START_VALUE);
			System.setProperty(Constants.HTML_CONFIG_KEY, Constants.HTML_CONFIG_PATH);
			System.setProperty(Constants.HTML_OUT_KEY, reportPath+ "Report.html");
		    System.setProperty(Constants.SCREENSHOT_DIR, reportPath );
		}
	}



