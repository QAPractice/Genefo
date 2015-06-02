package com.telran;

import com.telran.pages.EditAccountPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class EditAccountTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public MainPage mainPage;
    public LoginPage loginPage;
    public EditAccountPage editAccountPage;
    private static  SoftAssert softAssert;

    private static String MY_EMAIL="lev.magazinnik@gmail.com";
    private static String MY_Password="123qwee";

    private static String TEMP_EMAIL ="333333@mail.ru";
    private static String TEMP_PASS="111111";

    private static String MY_FirstName="lev";
    private static String MY_LastName="magazinnik";



    @BeforeClass
    public void setup(){

        File file = new File("C://Users//E.Frumker//AppData//Local//Mozilla Firefox//firefox.exe");
        FirefoxBinary binary = new FirefoxBinary(file);
        FirefoxProfile profile = new FirefoxProfile();
        this.driver = new FirefoxDriver(binary,profile);
        wait = new WebDriverWait(driver,5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        editAccountPage = PageFactory.initElements(driver,EditAccountPage.class);
        loginPage.openLoginPage()
                .waitUntilLoginPageIsLoaded()
                .login(MY_EMAIL, MY_Password);
        softAssert = new SoftAssert();

    }

    // TEST: 1.The button is clickable and opened the drop-down menu.(My account, My profiles, Logout).
    @Test
    public void dropDownMenuIsClickable(){
        mainPage.openMainPage()
                .waitUntilMainPageIsLoaded();
        assertTrue(mainPage.isOnMainPage());
        mainPage.selectMyAccount();
    }

    //    Edit 2	Verify that the user's information presents correctly
    @Test
    public void verifyUserInformation(){
        editAccountPage.openEditAccountPage()
                .waitUntilEditElementIsLoaded();

        softAssert.assertEquals(editAccountPage.getEmailElement().getAttribute("value"),MY_EMAIL);
        softAssert.assertEquals(editAccountPage.getFirstNameElement().getAttribute("value"), MY_FirstName);
        softAssert.assertEquals(editAccountPage.getLastNameElement().getAttribute("value"), MY_LastName);

    }

//Edit 3	Go to Edit Account. 1.Delete the current email.
// 2.Type in the field "Email" another email (valid) and Click the button "Save".
// 3.Enter the valid current password and click the button "Save".
    @Test
    public void updateEmail(){

        editAccountPage
                .openEditAccountPage()
                .waitUntilEditElementIsLoaded();
        editAccountPage
                .fillEmailField(TEMP_EMAIL)
                .clickOnSubmitButton1()
                .fillOldPasswordField(MY_Password)
                .clickOnSubmitButtonOldPassword()
                .openEditAccountPage()
        .waitUntilEditElementIsLoaded();
        softAssert.assertEquals(editAccountPage.getEmailElement().getAttribute("value"), TEMP_EMAIL);
// return old e-mail
        editAccountPage
                .fillEmailField(MY_EMAIL)
                .clickOnSubmitButton1()
                .fillOldPasswordField(MY_Password)
                .clickOnSubmitButtonOldPassword();
    }

    //    Edit 4	Go to Edit Account .
// 1.Delete the current email and type in the field "email" another one."333333@mail.ru" and Click "Save" button.
// 2.Enter the valid current password and click the button "Save".
// 3.Logout and login with the e-mail:333333@mail.ru and password:111111.
    @Test
    public void newEmailPassword(){
        editAccountPage
                .openEditAccountPage()
                .waitUntilEditElementIsLoaded()
                .fillEmailField(TEMP_EMAIL)
                .fillPasswordField(TEMP_PASS)
                .clickOnSubmitButton1()
                .fillOldPasswordField(MY_Password)
                .clickOnSubmitButtonOldPassword();

        mainPage
                .openMainPage()
                .selectLogOut();
        loginPage
                .openLoginPage()
                .waitUntilLoginPageIsLoaded()
                .login(TEMP_EMAIL, TEMP_PASS);

        softAssert.assertTrue(mainPage.isOnMainPage());

        // return old e-mail
        editAccountPage.loadPage();
        editAccountPage
                .waitUntilEditElementIsLoaded()
                .fillEmailField(MY_EMAIL)
                .fillPasswordField(MY_Password)
                .clickOnSubmitButton1()
                .fillOldPasswordField(TEMP_PASS)
                .clickOnSubmitButtonOldPassword();

    }

    @AfterClass(alwaysRun=true)
    public void quiteWindow(){
        softAssert.assertAll();
        this.driver.quit();
    }
}
