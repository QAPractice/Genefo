package com.telran;

import com.telran.pages.FiltersOfPatientOnMainPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Christina on 6/22/15.
 */
public class FiltersOfPatientTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                                 // Pages that we use in our tests
    public MainPage mainPage;
    public FiltersOfPatientOnMainPage filtersOfPatientOnMainPage;
    private static String PATIENT_ONE = "Pat One";
    private static String PATIENT_TWO = "Pat Two";
    private static String PATIENT_THREE = "Pat Three";



    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        filtersOfPatientOnMainPage = PageFactory.initElements(driver, FiltersOfPatientOnMainPage.class);
        try {
            loginPage.login("patone@pat.ru", "111111");
            mainPage.waitUntilMainPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void beforeMethodSetUp() {
        mainPage.openMainPage();
        mainPage.waitUntilMainPageIsLoaded()
                .openPostPanel();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
          System.out.print("Post Panel is not loaded");
        }

    }

    @Test(groups={"smoke","positive"}, enabled = true)
    public void chooseFirstItemOfFilter() {
        try {
            filtersOfPatientOnMainPage
                    .clickOnChangeFilterButton()
                    .clickOnMyPostsOnlyRadioButton()
                    .clickOnApplyFilterButton();
            filtersOfPatientOnMainPage.waitForDisplayingMyPosts();
            assertTrue("Alert:'NameOwnerFirstPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerFirstPost(PATIENT_ONE));
            assertTrue("Alert:'NameOwnerSecondPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerSecondPost(PATIENT_ONE));
            assertTrue("Alert:'NameOwnerThirdPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerThirdPost(PATIENT_ONE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups={"smoke","positive"}, enabled = true)
    public void chooseSecondItemOfFilter() {
        try {
            filtersOfPatientOnMainPage
                    .clickOnChangeFilterButton()
                    .clickOnPeopleIAmFollowingAndMyPostsOnlyRadioButton()
                    .clickOnApplyFilterButton();
            filtersOfPatientOnMainPage.waitForDisplayingMyPosts();
            assertTrue("Alert:'NameOwnerFirstPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerFirstPost(PATIENT_TWO));
            assertTrue("Alert:'NameOwnerSecondPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerSecondPost(PATIENT_ONE));
            assertTrue("Alert:'NameOwnerThirdPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerThirdPost(PATIENT_ONE));
            assertTrue("Alert:'NameOwnerForthPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerFourthPost(PATIENT_ONE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(groups={"smoke","positive"}, enabled = true)
    public void chooseThirdItemOfFilter() {
        try {
            filtersOfPatientOnMainPage
                    .clickOnChangeFilterButton()
                    .clickOnMyConditionAndPeopleIAmFollowingAndMyPostsOnlyRadioButton()
                    .clickOnApplyFilterButton();
            filtersOfPatientOnMainPage.waitForDisplayingMyPosts();
            assertTrue("Alert:'NameOwnerFirstPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerFirstPost(PATIENT_THREE));
            assertTrue("Alert:'NameOwnerSecondPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerSecondPost(PATIENT_TWO));
            assertTrue("Alert:'NameOwnerThirdPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerThirdPost(PATIENT_ONE));
            assertTrue("Alert:'NameOwnerForthPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerFourthPost(PATIENT_ONE));
            assertTrue("Alert:'NameOwnerFifthPost is not correct'", filtersOfPatientOnMainPage.isNameOfOwnerFifthPost(PATIENT_ONE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //patone@pat.ru 111111    Pat One  Bardet-Biedl syndrome Community     following Pat Two
    //doctor1@mail.ru 111111   Doctor One    Bardet-Biedl syndrome Community
    //pattwo@mail.ru 111111    Pat Two
    //patthree@mail.ru 111111   Pat Three  Bardet-Biedl syndrome Community

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }
}

