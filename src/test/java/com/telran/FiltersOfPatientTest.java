package com.telran;

import com.telran.pages.FiltersOfPatientOnMainPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Christina on 6/22/15.
 */
public class FiltersOfPatientTest extends TestNgTestBase {
    private static String PATIENT_ONE = "Pat One";
    private static String PATIENT_TWO = "Pat Two";
    private static String PATIENT_THREE = "Pat Three";
    private static String PATIENT_FOUR = "Pat Four";

    public LoginPage loginPage;                                 // Pages that we use in our tests
    public MainPage mainPage;
    public FiltersOfPatientOnMainPage filtersOfPatientOnMainPage;
    private static Logger Log = Logger.getLogger(LogLog4j.class.getName());

    @BeforeClass
    public void setup() {

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        filtersOfPatientOnMainPage = PageFactory.initElements(driver, FiltersOfPatientOnMainPage.class);
        try {
            loginPage.login("pat1@pat.ru", "111111");
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
            assertTrue("Alert:'NameOwnerFirstPost is not visible'", filtersOfPatientOnMainPage.isNameOfPatOne(PATIENT_ONE));
      //      assertFalse("Alert:'NameTwoPatient is visible'", filtersOfPatientOnMainPage.isNameOfPatTwo(PATIENT_TWO));
      //      assertFalse("Alert:'NameThreePatient is visible'", filtersOfPatientOnMainPage.isNameOfPatThree(PATIENT_THREE));
      //      assertFalse("Alert:'NameFourPatient is visible'", filtersOfPatientOnMainPage.isNameOfPatFour(PATIENT_FOUR));
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
            assertTrue("Alert:'NameOwnerFirstPost is not visible'", filtersOfPatientOnMainPage.isNameOfPatOne(PATIENT_ONE));
            assertTrue("Alert:'NameTwoPatient is not visible'", filtersOfPatientOnMainPage.isNameOfPatTwo(PATIENT_TWO));
            // assertFalse("Alert:'NameTwoPatient is visible'", filtersOfPatientOnMainPage.isNameOfPatThree(PATIENT_THREE));
           // assertFalse("Alert:'NameFourPatient is visible'", filtersOfPatientOnMainPage.isNameOfPatFour(PATIENT_FOUR));
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
            assertTrue("Alert:'NameOwnerFirstPost is not visible'", filtersOfPatientOnMainPage.isNameOfPatOne(PATIENT_ONE));
            assertTrue("Alert:'NameTwoPatient is not visible'", filtersOfPatientOnMainPage.isNameOfPatTwo(PATIENT_TWO));
            assertTrue("Alert:'NameTwoPatient is not visible'", filtersOfPatientOnMainPage.isNameOfPatThree(PATIENT_THREE));
            assertTrue("Alert:'NameFourPatient is not visible'", filtersOfPatientOnMainPage.isNameOfPatFour(PATIENT_FOUR));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //pat1@pat.ru 111111    Pat One  Donnai-Barrow Syndrome     following Pat Two
    //pat2@pat.ru 111111    Pat Two  Donnai-Barrow Syndrome
    //pat3@par.ru 111111    Pat Three  Donnai-Barrow Syndrome
    //pat4@pat.ru 111111    Pat Four   Donnai-Barrow Syndrome

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }
}

