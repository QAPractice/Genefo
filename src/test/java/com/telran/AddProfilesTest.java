package com.telran;

import com.telran.pages.*;
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
 * Created by Л on 5/19/2015.
 */
public class AddProfilesTest {
    private static String EMAIL="ri-lopatina@yandex.ru";
    private static String PASSWORD="123456";
    private static String FIRST_MAME="AAAAA";
    private static String LAST_NAME="BBBBBB";
    private static String PATIENT_PROFILE_TYPE="2";
    private static String PATIENT_PROFILE_TYPE_CHECK="Friend";
    private static String GENDER="0";
    private static String GENDER_CHECK="Male";
    private static String CONDITION="Alstrom";
    private static String MONTH="6";
    private static String MONTH_CHECK="July";
    private static String DAY="0";
    private static String DAY_CHECK="1";
    private static String YEAR="5";
    private static String YEAR_CHECK="2010";
    private static String DIAGNOSE_YEAR="1";
    private static String DIAGNOSE_YEAR_CHECK="2014";

//Constants for checking another profile test
    private static String FIRST_MAME1="Reg";
    private static String LAST_NAME1="Lop";
    private static String PATIENT_PROFILE_TYPE_CHECK1="It's Me";
    private static String GENDER_CHECK1="Female";
    private static String CONDITION1="Avascular necrosis of the femoral head";
    private static String MONTH_CHECK1="Oktober";
    private static String DAY_CHECK1="17";
    private static String YEAR_CHECK1="1983";
    private static String DIAGNOSE_YEAR_CHECK1="February 1987";

    public WebDriver driver;
    public WebDriverWait wait;
    MyProfilesPage myProfilesPage;
    ProfilePage profilePage;
    LoginPage loginPage;
    MainPage mainPage;
    SummaryPage summaryPage;
    private boolean acceptNextAlert = true;

    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        myProfilesPage = PageFactory.initElements(driver, MyProfilesPage.class);
        profilePage = PageFactory.initElements(driver, ProfilePage.class);
        summaryPage = PageFactory.initElements(driver, SummaryPage.class);

        try {
            loginPage.openLoginPage()
                    .isOnLoginPage();
            loginPage.fillEmailField(EMAIL)
                    .fillPasswordField(PASSWORD)
                    .clickOnLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (groups = {"smoke", "positive"})
    public void AddProfileSuccess() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(FIRST_MAME);
        profilePage.fillProfileLastNameField(LAST_NAME);
        String name = profilePage.getProfileName();
        profilePage.selectProfilePatient(PATIENT_PROFILE_TYPE);
        profilePage.isPatientSelected(PATIENT_PROFILE_TYPE_CHECK);
        profilePage.selectGender(GENDER);
        profilePage.isGenderSelected(GENDER_CHECK);
        profilePage.fillProfileConditionField(CONDITION);
        profilePage.autoFillCondition();
        profilePage.selectMonth(MONTH);
        profilePage.isMonthSelected(MONTH_CHECK);
        profilePage.selectDay(DAY);
        profilePage.isDaySelected(DAY_CHECK);
        profilePage.selectYear(YEAR);
        profilePage.isYearSelected(YEAR_CHECK);
        profilePage.selectDiagnoseYear(DIAGNOSE_YEAR);
        profilePage.isDiagnoseYearSelected(DIAGNOSE_YEAR_CHECK);
        profilePage.clickToSubmit();
        assertTrue("The Summary Page doesn't open", summaryPage.isOnSummaryPage());
        assertTrue("Profile name doesn't present", summaryPage.isProfileNamePresents(name));
        summaryPage.clickOnDiscoverHome();
    }
    //  Negative tests
    @Test (groups = {"smoke", "negative"})    //Bug!!!
    public void AddProfileWithoutCondition() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(FIRST_MAME);
        profilePage.fillProfileLastNameField(LAST_NAME);
        profilePage.selectProfilePatient(PATIENT_PROFILE_TYPE);
        profilePage.isPatientSelected(PATIENT_PROFILE_TYPE_CHECK);
        profilePage.selectGender(GENDER);
        profilePage.isGenderSelected(GENDER_CHECK);
        profilePage.selectMonth(MONTH);
        profilePage.isMonthSelected(MONTH_CHECK);
        profilePage.selectDay(DAY);
        profilePage.isDaySelected(DAY_CHECK);
        profilePage.selectYear(YEAR);
        profilePage.isYearSelected(YEAR_CHECK);
        profilePage.selectDiagnoseYear(DIAGNOSE_YEAR);
        profilePage.isDiagnoseYearSelected(DIAGNOSE_YEAR_CHECK);
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
    }
    @Test (groups = {"smoke", "negative"})
    public void AddProfileWoutLastName() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(FIRST_MAME);
        profilePage.selectProfilePatient(PATIENT_PROFILE_TYPE);
        profilePage.isPatientSelected(PATIENT_PROFILE_TYPE_CHECK);
        profilePage.selectGender(GENDER);
        profilePage.isGenderSelected(GENDER_CHECK);
        profilePage.fillProfileConditionField(CONDITION);
        profilePage.autoFillCondition();
        profilePage.selectMonth(MONTH);
        profilePage.isMonthSelected(MONTH_CHECK);
        profilePage.selectDay(DAY);
        profilePage.isDaySelected(DAY_CHECK);
        profilePage.selectYear(YEAR);
        profilePage.isYearSelected(YEAR_CHECK);
        profilePage.selectDiagnoseYear(DIAGNOSE_YEAR);
        profilePage.isDiagnoseYearSelected(DIAGNOSE_YEAR_CHECK);
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
    }
    @Test (groups = {"smoke", "negative"})
    public void AddProfileWoutFirstName() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(LAST_NAME);
        profilePage.selectProfilePatient(PATIENT_PROFILE_TYPE);
        profilePage.isPatientSelected(PATIENT_PROFILE_TYPE_CHECK);
        profilePage.selectGender(GENDER);
        profilePage.isGenderSelected(GENDER_CHECK);
        profilePage.fillProfileConditionField(CONDITION);
        profilePage.autoFillCondition();
        profilePage.selectMonth(MONTH);
        profilePage.isMonthSelected(MONTH_CHECK);
        profilePage.selectDay(DAY);
        profilePage.isDaySelected(DAY_CHECK);
        profilePage.selectYear(YEAR);
        profilePage.isYearSelected(YEAR_CHECK);
        profilePage.selectDiagnoseYear(DIAGNOSE_YEAR);
        profilePage.isDiagnoseYearSelected(DIAGNOSE_YEAR_CHECK);
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
    }
    @Test (groups = {"negative"})
    public void AddProfileWoutPatientType() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(FIRST_MAME);
        profilePage.fillProfileFirstNameField(LAST_NAME);
        profilePage.selectGender(GENDER);
        profilePage.isGenderSelected(GENDER_CHECK);
        profilePage.fillProfileConditionField(CONDITION);
        profilePage.autoFillCondition();
        profilePage.selectMonth(MONTH);
        profilePage.isMonthSelected(MONTH_CHECK);
        profilePage.selectDay(DAY);
        profilePage.isDaySelected(DAY_CHECK);
        profilePage.selectYear(YEAR);
        profilePage.isYearSelected(YEAR_CHECK);
        profilePage.selectDiagnoseYear(DIAGNOSE_YEAR);
        profilePage.isDiagnoseYearSelected(DIAGNOSE_YEAR_CHECK);
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
    }
    @Test (groups = {"negative"})
    public void AddProfileWoutGender() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(FIRST_MAME);
        profilePage.fillProfileFirstNameField(LAST_NAME);
        profilePage.selectProfilePatient(PATIENT_PROFILE_TYPE);
        profilePage.isPatientSelected(PATIENT_PROFILE_TYPE_CHECK);
        profilePage.fillProfileConditionField(CONDITION);
        profilePage.autoFillCondition();
        profilePage.selectMonth(MONTH);
        profilePage.isMonthSelected(MONTH_CHECK);
        profilePage.selectDay(DAY);
        profilePage.isDaySelected(DAY_CHECK);
        profilePage.selectYear(YEAR);
        profilePage.isYearSelected(YEAR_CHECK);
        profilePage.selectDiagnoseYear(DIAGNOSE_YEAR);
        profilePage.isDiagnoseYearSelected(DIAGNOSE_YEAR_CHECK);
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
    }
    @Test (groups = {"negative"})
    public void AddProfileWoutMonth() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(FIRST_MAME);
        profilePage.fillProfileFirstNameField(LAST_NAME);
        profilePage.selectProfilePatient(PATIENT_PROFILE_TYPE);
        profilePage.isPatientSelected(PATIENT_PROFILE_TYPE_CHECK);
        profilePage.selectGender(GENDER);
        profilePage.isGenderSelected(GENDER_CHECK);
        profilePage.fillProfileConditionField(CONDITION);
        profilePage.autoFillCondition();
        profilePage.selectDay(DAY);
        profilePage.isDaySelected(DAY_CHECK);
        profilePage.selectYear(YEAR);
        profilePage.isYearSelected(YEAR_CHECK);
        profilePage.selectDiagnoseYear(DIAGNOSE_YEAR);
        profilePage.isDiagnoseYearSelected(DIAGNOSE_YEAR_CHECK);
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
    }
    @Test (groups = {"negative"})
    public void AddProfileWoutDay() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(FIRST_MAME);
        profilePage.fillProfileFirstNameField(LAST_NAME);
        profilePage.selectProfilePatient(PATIENT_PROFILE_TYPE);
        profilePage.isPatientSelected(PATIENT_PROFILE_TYPE_CHECK);
        profilePage.selectGender(GENDER);
        profilePage.isGenderSelected(GENDER_CHECK);
        profilePage.fillProfileConditionField(CONDITION);
        profilePage.autoFillCondition();
        profilePage.selectMonth(MONTH);
        profilePage.isMonthSelected(MONTH_CHECK);
        profilePage.selectYear(YEAR);
        profilePage.isYearSelected(YEAR_CHECK);
        profilePage.selectDiagnoseYear(DIAGNOSE_YEAR);
        profilePage.isDiagnoseYearSelected(DIAGNOSE_YEAR_CHECK);
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
    }
    @Test (groups = {"negative"})
    public void AddProfileWoutYear() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(FIRST_MAME);
        profilePage.fillProfileFirstNameField(LAST_NAME);
        profilePage.selectProfilePatient(PATIENT_PROFILE_TYPE);
        profilePage.isPatientSelected(PATIENT_PROFILE_TYPE_CHECK);
        profilePage.selectGender(GENDER);
        profilePage.isGenderSelected(GENDER_CHECK);
        profilePage.fillProfileConditionField(CONDITION);
        profilePage.autoFillCondition();
        profilePage.selectMonth(MONTH);
        profilePage.isMonthSelected(MONTH_CHECK);
        profilePage.selectDay(DAY);
        profilePage.isDaySelected(DAY_CHECK);
        profilePage.selectDiagnoseYear(DIAGNOSE_YEAR);
        profilePage.isDiagnoseYearSelected(DIAGNOSE_YEAR_CHECK);
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
    }
    @Test (groups = {"negative"})
    public void AddProfileWoutDiagnoseYear() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickToPlus();
        profilePage.isOnProfilePage();
        profilePage.fillProfileFirstNameField(LAST_NAME);
        profilePage.selectProfilePatient(PATIENT_PROFILE_TYPE);
        profilePage.isPatientSelected(PATIENT_PROFILE_TYPE_CHECK);
        profilePage.selectGender(GENDER);
        profilePage.isGenderSelected(GENDER_CHECK);
        profilePage.fillProfileConditionField(CONDITION);
        profilePage.autoFillCondition();
        profilePage.selectMonth(MONTH);
        profilePage.isMonthSelected(MONTH_CHECK);
        profilePage.selectDay(DAY);
        profilePage.isDaySelected(DAY_CHECK);
        profilePage.selectYear(YEAR);
        profilePage.isYearSelected(YEAR_CHECK);
        profilePage.clickToSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Main Page is opened", profilePage.isOnProfilePage());
    }
    //Sitechko: TCFB 2
    @Test(groups = {"bugs", "positive"})
    public void CheckAnotherProfile() {
        mainPage.isOnMainPage();
        mainPage.selectMyProfile();
        myProfilesPage.isOnMyProfilesPage();
        myProfilesPage.clickSecondProfile();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        summaryPage.isOnSummaryPage();
        assertTrue("Patient profile type is not correct", summaryPage.isRelationCorrect(PATIENT_PROFILE_TYPE_CHECK));
        assertTrue("Name is not correct", summaryPage.isNameCorrect(FIRST_MAME + " " + LAST_NAME));
        assertTrue("Condition is not correct", summaryPage.isConditionCorrect(CONDITION));
        assertTrue("Gender is not correct", summaryPage.isGenderFieldCorrect(GENDER_CHECK));
        assertTrue("Birthday is not correct", summaryPage.isBirthdayFieldCorrect(MONTH_CHECK + " " + DAY_CHECK + ", " + YEAR_CHECK));
        assertTrue("Diagnose date is not correct", summaryPage.isPatientDiagnosisDateFieldCorrect(DIAGNOSE_YEAR_CHECK));
        summaryPage.clickOnFirstProfile();
        assertTrue("Not on Summary Page", summaryPage.isOnSummaryPage());
        assertTrue("Patient profile type1 is not correct", summaryPage.isRelationCorrect(PATIENT_PROFILE_TYPE_CHECK1));
        assertTrue("Name1 is not correct", summaryPage.isNameCorrect(FIRST_MAME1 + " " + LAST_NAME1));
        assertTrue("Condition1 is not correct", summaryPage.isConditionCorrect(CONDITION1));
        assertTrue("Gender1 is not correct", summaryPage.isGenderFieldCorrect(GENDER_CHECK1));
        assertTrue("Birthday1 is not correct", summaryPage.isBirthdayFieldCorrect(MONTH_CHECK1 + " " + DAY_CHECK1 + ", " + YEAR_CHECK1));
        assertTrue("Diagnose date1 is not correct", summaryPage.isPatientDiagnosisDateFieldCorrect(DIAGNOSE_YEAR_CHECK1));
        summaryPage.clickOnDiscoverHome();
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }
}
