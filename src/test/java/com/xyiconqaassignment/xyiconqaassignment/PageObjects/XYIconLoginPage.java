package com.xyiconqaassignment.xyiconqaassignment.PageObjects;

import config.Config;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.log.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;

public class XYIconLoginPage {

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement userName;

    @FindBy(xpath = "//input[@id='pwInput']")
    private WebElement userPassword;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class='portfolioName']")
    private WebElement mainPageTitle;
    @FindBy(xpath = "//span[@class='navButton button']")
    private WebElement naveBtn;

    @FindBy(xpath = "//span[@class='navLink'][normalize-space()='Spaces']")
    private WebElement navSpaces;

    @FindBy(xpath = "//h3[normalize-space()='Spaces']")
    private WebElement pageTitleSpace;

    @FindBy(xpath = "//div[@title='Level 1']")
    private WebElement levelOne;
    @FindBy(xpath = "//div[@title='Catalog Panel']")
    private WebElement catalogPanel;

    @FindBy(xpath = "//div[contains(@class,'typeName')]//div[contains(@class,'typeName')][normalize-space()='Computer']")
    private WebElement computer;
    @FindBy(xpath = "//div[contains(text(),'Mouse')]")
    private WebElement mouse;
    @FindBy(xpath = "//div[@id='canvas-div']")
    private WebElement dropElement;

    @FindBy(css = "body > div:nth-child(2) > div:nth-child(4) > article:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(6) > div:nth-child(4) > div:nth-child(1)")
    private WebElement rossHoldingPortfolio;


    private static WebDriver driver = null;

    /**
     * Constructs a new XYIconLoginPage object with the provided WebDriver instance.
     * Initializes the elements of the page using PageFactory and sets the WebDriver instance for the page.
     *
     * @param driver The WebDriver instance to be used for interacting with the XYIconLoginPage.
     */
    public XYIconLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        XYIconLoginPage.driver = driver;
    }

    /**
     * Performs the login operation by entering the username, password, and clicking the login button.
     * Waits for elements to be visible and clickable before interacting with them.
     * Takes screenshots at different steps and attaches them to the Allure report.
     * Verifies the main page title after login and logs success or failure messages accordingly.
     *
     * @throws IOException If an I/O error occurs during the login process.
     */
    @Step("login()")
    @Description("Wait until element to be clickable for expected time")
    public void login() throws IOException {
        try {
            waitUntilElementToBeVisible(Duration.ofSeconds(10), userName);
            userName.sendKeys(Config.getProperty("login.username"));
            Log.info("Success: username ended");

            waitUntilElementToBeVisible(Duration.ofSeconds(10), userPassword);
            userPassword.sendKeys(Config.getProperty("login.password"));
            Log.info("Success: password ended");

            waitUntilElementToBeClickable(Duration.ofSeconds(10), loginButton);
            takeScreenshotAndAttachToAllureReport("Screenshot of login page");
            loginButton.click();
            Log.info("Success: clicked on the login button");

            Assert.assertEquals(mainPageTitle.getText(), "ROSS HOLDINGS- SPECIALTY CENTER-1-AR-C",
                    "Page Title Did not Match");

            takeScreenshotAndAttachToAllureReport("Screenshot of After login");
        } catch (Exception e) {
            Log.error("Failed: login()!!");
            throw e;
        }
    }

    /**
     * Navigates to the Spaces section by clicking on various navigation buttons.
     * Waits for elements to be clickable and visible before interacting with them.
     * Takes screenshots at different steps and attaches them to the Allure report.
     * Verifies the page title after reaching the Spaces section and logs success or failure messages accordingly.
     */
    @Step("goToSpaces()")
    @Description("Go to Spaces")
    public void goToSpaces() {

        try {
            waitUntilElementToBeClickable(Duration.ofSeconds(5), rossHoldingPortfolio);
            rossHoldingPortfolio.click();
            takeScreenshotAndAttachToAllureReport("Screenshot Of selected Ross Holding Portfolio");
            Log.info("Success: Clicked of navigation button");

            waitUntilElementToBeClickable(Duration.ofSeconds(5), naveBtn);
            naveBtn.click();
            takeScreenshotAndAttachToAllureReport("Screenshot of navigation");
            Log.info("Success: Clicked on navigation button");

            waitUntilElementToBeClickable(Duration.ofSeconds(5), navSpaces);
            navSpaces.click();
            Log.info("Success: Clicked on spaces");

            waitUntilElementToBeVisible(Duration.ofSeconds(5), pageTitleSpace);
            takeScreenshotAndAttachToAllureReport("Screenshot of spaces");
            Assert.assertEquals(pageTitleSpace.getText(), "SPACES");
        } catch (Exception e) {
            Log.error("Failed: goToSpaces()");
            throw e;
        }

    }


    /**
     * Navigates to Level One by performing a double-click action on the specified element.
     * Waits for the element to be clickable before interacting with it.
     * Uses Actions class to perform the double-click action and waits for 10 seconds after the action.
     * Takes a screenshot of Level One and attaches it to the Allure report.
     *
     * @throws InterruptedException If the thread is interrupted while waiting after the double-click action.
     */
    @Step("goToLevelOne()")
    @Description("Go To Level One")
    public void goToLevelOne() throws InterruptedException {

        try {
            waitUntilElementToBeClickable(Duration.ofSeconds(5), levelOne);

            Actions actions = new Actions(driver);
            actions.doubleClick(levelOne).perform();

            synchronized (driver) {
                driver.wait(10000);
            }

            takeScreenshotAndAttachToAllureReport("take screenshot of level One");
            Log.info("Success: Clicked of level One");

        } catch (Exception e) {
            Log.error("Failed: goToLevelOne()");
            throw e;
        }

    }

    /**
     * Adds a computer and mouse by interacting with the catalog panel.
     * Waits for elements to be visible and clickable before performing actions.
     * Clicks on the catalog panel, moves the computer element, and releases it using Actions class.
     * Waits for 10 seconds after the action and takes a screenshot of the result.
     *
     * @throws InterruptedException If the thread is interrupted while waiting after the action.
     */
    @Step("addComputerAndMouse()")
    @Description("Add Computer And Mouse")
    public void addComputerAndMouse() throws InterruptedException {

        try {
            waitUntilElementToBeVisible(Duration.ofSeconds(5), catalogPanel);
            waitUntilElementToBeClickable(Duration.ofSeconds(5), catalogPanel);
            catalogPanel.click();
            takeScreenshotAndAttachToAllureReport("Screenshot of Catalog panel");
            Log.info("Success: Clicked of Catalog panel");

            waitUntilElementToBeClickable(Duration.ofSeconds(5), computer);

            new Actions(driver)
                    .clickAndHold(computer).moveByOffset(100, 150).release().perform();


            /*waitUntilElementToBeVisible(Duration.ofSeconds(5), catalogPanel);
            waitUntilElementToBeClickable(Duration.ofSeconds(5), catalogPanel);
            catalogPanel.click();
            takeScreenshotAndAttachToAllureReport("Screenshot of Catalog panel");
            Log.info("Success: Clicked of Catalog panel");

            Actions actions2 = new Actions(driver);
            actions2.clickAndHold(mouse)
                    .moveByOffset(xOffset, yOffset)
                    .release()
                    .perform();
*/

            Thread.sleep(10000);
            takeScreenshotAndAttachToAllureReport("take screenshot of level One");
            Log.info("Success: Clicked of level One");

        } catch (Exception e) {
            Log.error("Failed: addComputerAndMouse()");
            throw e;
        }

    }

    /**
     * Adds a computer and mouse by interacting with the catalog panel.
     * Waits for elements to be visible and clickable before performing actions.
     * Clicks on the catalog panel, moves the computer element, and releases it using Actions class.
     * Waits for 10 seconds after the action and takes a screenshot of the result.
     *
     * @throws InterruptedException If the thread is interrupted while waiting after the action.
     */
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

    /**
     * Waits until the specified element is visible on the web page.
     * Uses WebDriverWait with the given timeout to wait for the element's visibility.
     * Logs the waiting duration and success message upon visibility of the element.
     *
     * @param timeout The duration to wait for the element to be visible.
     * @param element The WebElement to be checked for visibility.
     */
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

    /**
     * Takes a screenshot of the current screen and attaches it to the Allure report with the specified name.
     * Converts the screenshot into bytes using TakesScreenshot interface.
     * Adds the screenshot as an attachment to the Allure report.
     *
     * @param name The name to be given to the screenshot attachment in the Allure report.
     */
    @Step("waitUntilElementToBeVisible() |  name: {0}")
    @Description("take screenshot")
    public static void takeScreenshotAndAttachToAllureReport(String name) {

        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment(name, new ByteArrayInputStream(screenshotBytes));
    }
}
