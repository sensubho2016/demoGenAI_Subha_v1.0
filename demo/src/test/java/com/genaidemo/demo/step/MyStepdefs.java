package com.genaidemo.demo.step;

import com.genaidemo.demo.annotation.LazyAutowired;
import com.genaidemo.demo.generic.Constant;
import com.genaidemo.demo.page.ContactUsPage;
import com.genaidemo.demo.page.LoginPage;
import com.genaidemo.demo.utility.MockTestData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@Slf4j
@CucumberContextConfiguration
@SpringBootTest
public class MyStepdefs {

    @LazyAutowired
    private LoginPage loginPage;
    @LazyAutowired
    private MockTestData mockTestData;
    @LazyAutowired
    private ContactUsPage contactUsPage;

    @Given("{string} is able to open successfully Homepage for testcase {string}")
    public void userIsAbleToLoginSuccessfullyOntoHomepageFor(String userType, String testCase) {

        try {
            log.info(userType + " is going to open METLIFE HOMEPAGE for testCase " + testCase);
            this.loginPage.loginToPortal(testCase);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Then("User is on {string} Page for testCase {string}")
    public void userIsOnPageForTestCase(String page, String testCase) {

        try {
            log.info(page + " is going to open for testCase " + testCase);
            //this.loginPage.goToPage(page, testCase);
            Constant.count++;
            Thread.sleep(2000);
            log.info("counrt value is: " + Constant.count);
            if(Constant.count == 1) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Then("User moves to Category and Validate contact details present on screen for testCase {string}")
    public void userMovesToCategoryAndValidateContactDetailsPresentOnScreenForTestCase(String testCase) {
        try {
            log.info("Category Page is going to open for testCase " + testCase);
            this.contactUsPage.goToCategory();
            Thread.sleep(2000);
            this.contactUsPage.validateValuesFromScreen(testCase);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
