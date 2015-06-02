package com.telran;

import com.telran.pages.EditAccountPage;
import com.telran.pages.HomePage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    private static String MY_EMAIL_TO_UPDATE="attogroup.bgu@gmail.com";
    private static String MY_Password="123qwee";
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
        loginPage.openLoginPage();
        loginPage.waitUntilLoginPageIsLoaded();
        loginPage.login(MY_EMAIL, MY_Password);
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
                .fillEmailField(MY_EMAIL_TO_UPDATE)
                .clickOnSubmitButton1()
                .fillOldPasswordField(MY_Password)
                .clickOnSubmitButtonOldPassword()
                .openEditAccountPage()
        .waitUntilEditElementIsLoaded();
        softAssert.assertEquals(editAccountPage.getEmailElement().getAttribute("value"), MY_EMAIL_TO_UPDATE);


// return old e-mail
        editAccountPage
                .fillEmailField(MY_EMAIL)
                .clickOnSubmitButton1()
                .fillOldPasswordField(MY_Password)
                .clickOnSubmitButtonOldPassword();


    }


    @AfterClass(alwaysRun=true)
    public void quiteWindow(){
        softAssert.assertAll();
        this.driver.quit();
    }
}
