package com.xyiconqaassignment.xyiconqaassignment.webPage;

import config.Config;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.log.Log;

import java.time.Duration;

public class XYIconLoginPage {

    @FindBy(xpath = "//input[@id='userName']")
    public WebElement userName;

    @FindBy(xpath = "//input[@id='pwInput']")
    public WebElement userPassword;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    public WebElement loginButton;


    private WebDriver driver;


    public XYIconLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @Step("login()")
    @Description("Wait until element to be clickable for expected time")
    public void login(){
        try {
            waitUntilElementToBeVisible(Duration.ofSeconds(10),userName);
            userName.sendKeys(Config.getProperty("login.username"));
            Log.info("User endt");

            waitUntilElementToBeVisible(Duration.ofSeconds(10),userPassword);
            userPassword.sendKeys(Config.getProperty("login.password"));

            waitUntilElementToBeClickable(Duration.ofSeconds(10),loginButton);
            loginButton.click();

        }catch (Exception e){
            Log.error("login()");
            throw e;
        }
    }

    @Step("waitUntilElementToBeClickable() | timeout: {0} | webElement: {1}")
    @Description("Wait until element to be clickable for expected time")
    public void waitUntilElementToBeClickable(Duration timeout, WebElement element) {
        try {
            new WebDriverWait(driver, timeout).until(
                    ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
            Log.info("Wait " + timeout.getSeconds() + " until element to be clickable");
        } catch (Exception e) {
            Log.info("Failed: waitUntilElementToBeClickable()");
            throw e;
        }
    }

    @Step("waitUntilElementToBeVisible() | timeout: {0} | webElement: {1}")
    @Description("Wait Until Element to be visible")
    public void waitUntilElementToBeVisible(Duration timeout, WebElement element) {
        try {
            new WebDriverWait(driver, timeout).until(
                    ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
            Log.info("Wait " + timeout.getSeconds() + " until element to be visible");
        } catch (Exception e) {
            Log.info("Failed: waitUntilElementToBeVisible()");
            throw e;
        }
    }
}
