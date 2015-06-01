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
    private static String MY_EMAIL="lev.magazinnik@gmail.com";
    private static String MY_Password="123qwee";
    private static String MY_FirstName="Lev";
    private static String MY_LastName="Magazinnik";


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

    }

    // TEST: 1.The button is clickable and opened the drop-down menu.(My account, My profiles, Logout).
    @Test
    public void dropDownMenuIsClickable(){
        mainPage.openMainPage()
                .waitUntilMainPageIsLoaded();
        assertTrue(mainPage.isOnMainPage());
        mainPage.selectMyAccount();
    }

    //    Edit 2	Verify that the user's information presents correctly in the opened window "Edit account".
// 1.Click the button"Settings" and then click the button "My Account".
// 1. The window "Edit Account" is opened and all fields: Email,password,First Name, Second Name are present correctly.
    @Test
    public void verifyUserInformation(){
        editAccountPage.openEditAccountPage()
                .waitUntilEditElementIsLoaded();
        assertEquals(editAccountPage.getEmailElement().getAttribute("value"),MY_EMAIL);

        assertEquals(editAccountPage.getFirstNameElement(),MY_FirstName);
        assertEquals(editAccountPage.getLastNameElement(),MY_LastName);
        assertEquals(editAccountPage.getNewPasswordElement(),MY_Password);


    }


//    @AfterClass(alwaysRun=true)
//    public void quiteWindow(){
//        this.driver.quit();
//    }
}
