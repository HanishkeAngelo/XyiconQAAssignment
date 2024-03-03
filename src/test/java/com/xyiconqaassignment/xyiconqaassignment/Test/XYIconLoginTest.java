package com.xyiconqaassignment.xyiconqaassignment.Test;

import com.xyiconqaassignment.xyiconqaassignment.PageObjects.XYIconLoginPage;
import driver.Driver;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.log.Log;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class XYIconLoginTest {
    private XYIconLoginPage xyIconLoginPage;

    @BeforeMethod
    public void setUp() {

        try {
            WebDriver driver = Driver.getInstance().getDriver();
            xyIconLoginPage = new XYIconLoginPage(driver);
        }catch (Exception e){
            Log.error("Failed: setUp()!!");
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            Driver.closeDriver();
        }catch (Exception e){
            Log.info("Failed: tearDown()");
            throw e;
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Epic("XYIConAssignment")
    @Story("XYIConAssignment Story")
    @Link("https://www.example.com")
    @Test(description = "Verify login", testName = "XYIcon Log Test", priority = 1)
    public void loginTest(){
        try {
            xyIconLoginPage.login();
            Log.info("Pass: loginTest()");
        } catch (Exception e) {
            Log.error("Failed: loginTest()");
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Epic("XYIConAssignment")
    @Story("XYIConAssignment Story")
    @Link("https://www.example.com")
    @Test(description = "XYIcon Assignment", testName = "XYIcon Assignment Test", priority = 2)
    public void xYIconAssignmentTest() throws IOException, InterruptedException {
        try {
            xyIconLoginPage.login();
            xyIconLoginPage.goToSpaces();
            xyIconLoginPage.goToLevelOne();
            xyIconLoginPage.addComputerAndMouse();
            Log.info("Pass: goToSpacesTest()");
        } catch (Exception e) {
            Log.error("Failed: goToSpacesTest()");
            throw e;
        }
    }


}
