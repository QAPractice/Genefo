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

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
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
    public String someText;
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
                    .clickOnSelectEatingItemOption()
                    .clickFirstItemFromEatingItemList()
                    .clickOnYearsOption("3")
                    .clickOnMonthOption("6")
                    .fillTextField("Holds bottle")
                    .sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void SendToiletingPostTest() {
        try {
            milestoneOnMainPage
                    .clickOnToiletingOption()
                    .clickOnToiletingItemList()
                    .clickFirstItemToiletingList()
                    .clickOnYearsOption("21")
                    .clickOnMonthOption("3")
                    .fillToiletingTextField("Dresses alone")
                    .sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void DeleteToiletingPostTest() {
        try {
            milestoneOnMainPage
                    .clickOnToiletingOption()
                    .clickOnToiletingItemList()
                    .clickFirstItemFromMovementList()
                    .clickOnYearsOption("2")
                    .clickOnMonthOption("3")
                    .fillTextField("Dresses alone")
                    .sendPost();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void SendMovementPostTest() {
        try {
            milestoneOnMainPage
                    .clickOnMovementOption()
                    .clickOnSelectMovementItemOption()
                    .clickFirstItemFromMovementList()
                    .clickOnYearsOption("2")
                    .clickOnMonthOption("3")
                    .fillTextField("Dresses alone")
                    .sendPost();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void SendMilestoneNegativeTest1(){
        try {
            someText = randomAlphabetic(2256);
            milestoneOnMainPage
              //      .clickOnLanguageOption()
               //     .clickOnSelectItemOption()
               //     .clickFirstItemFromLanguageItemList()
                    .clickOnMonthOption("abc")
                    .clickOnYearsOption("")
                    .fillTextField(someText)
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void SendMilestoneNegativeTest2(){
        try {
            someText = randomAlphabetic(11);
            milestoneOnMainPage
                    .clickOnLanguageOption()
                    .clickOnSelectItemOption()
                    .clickOnLanguageItemOption("abc")
                    .clickOnYearsOption("abc")
                    .clickOnMonthOption("&^$")
                    .fillTextField(someText)
                    .sendPost();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }



    @AfterClass(alwaysRun = true)
    public void teardown () {
        this.driver.quit();
    }

}
