package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.MilestoneOnMainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Zizi78 on 5/30/2015.
 */
public class MilestoneOnMainPageTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                         // Pages that we use in our tests
    public MainPage mainPage;
    public MilestoneOnMainPage milestoneOnMainPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        milestoneOnMainPage = PageFactory.initElements(driver, MilestoneOnMainPage.class);

        try {
            loginPage.login("zizi300@gmail.com", "zizi300");
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                    .openMilestonePanel();
            milestoneOnMainPage.waitUntilMilestonePanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SendLanguagePostTest() {

        try {
            milestoneOnMainPage
                    .clickOnLanguageOption()
                    .clickOnSelectItemOption()
                    .clickFirstItemFromLanguageItemList()
                    .clickOnYearsOption("7")
                    .clickOnMonthOption("5")
                    .fillTextField("My Post in Language")
                    .sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void SendEatingPostTest() {
        try {
            milestoneOnMainPage
                    .clickOnEatingOption()
                    .clickOnEatingOption()
                    .chooseSecondItemFromEatingList()
                    .clickOnYearsOption("3")
                    .clickOnMonthOption("6")
                    .fillTextField("Holds bottle")
                    .sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        @AfterClass(alwaysRun = true)
        public void teardown () {
            this.driver.quit();
        }

    }
