package com.xyiconqaassignment.xyiconqaassignment;

import config.Config;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.log.Log;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {

        driver = Driver.getInstance().getDriver();
        mainPage = new MainPage(driver);
    }

    @AfterMethod
    public void tearDown() {
//        driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.cssSelector("[data-test='search-input']"));
        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test='search-input']"));
        assertEquals(searchPageField.getAttribute("value"), "Selenium");
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='main-submenu']"));
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals(driver.getTitle(), "All Developer Tools and Products by JetBrains");
    }

    @Test
    public void logTest() {
        Log.error("Error");
        Log.info("info");
        Log.warn("warn");
    }

    @Test
    public void getPropertyTest() throws IOException {
        try {
            String x = Config.getProperty("default.browser");
            Log.info(x);
        } catch (Exception e) {
            Log.error("Filed: getPropertyTest()!!!");
            throw e;
        }

    }

}
