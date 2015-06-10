package com.telran;

import com.telran.pages.EditAccountPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class EditAccountTest {

    private static int VALID_INPUT_LENGTH=25;

    private static String MY_EMAIL="mili27@mail.ru";
    private static String MY_Password="123qwee";
    private static String TEMP_EMAIL ="333333@mail.ru";
    private static String TEMP_PASS="111111";
    private static String MY_FirstName="lev";
    private static String MY_LastName="magazinnik";
    public WebDriver driver;
    public WebDriverWait wait;
    public MainPage mainPage;
    public LoginPage loginPage;
    public EditAccountPage thisPage;



    @BeforeClass
    public void setup(){
       this.driver = TestUtils.chooseDriver(WEB_DRIVER.Chrome);
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        thisPage = PageFactory.initElements(driver,EditAccountPage.class);
        loginPage.openLoginPage()
                .waitUntilLoginPageIsLoaded()
                .login(MY_EMAIL, MY_Password);

    }

    // TEST: 1.The button is clickable and opened the drop-down menu.(My account, My profiles, Logout).
    @Test(groups={"smoke","positive"})
    public void dropDownMenuIsClickable(){
        TestUtils.addTestToLog();

        mainPage.openMainPage()
                .waitUntilMainPageIsLoaded();
        assertTrue(mainPage.isOnMainPage());
        mainPage.selectMyAccount();

    }


    //    Edit 2	Verify that the user's information presents correctly
    @Test(groups={"smoke","positive"})
    public void verifyUserInformation(){
        TestUtils.addTestToLog();
        thisPage.openEditAccountPage()
                .waitUntilEditElementIsLoaded();


        assertEquals(thisPage.getEmailElement().getAttribute("value"), MY_EMAIL);
        assertEquals(thisPage.getFirstNameElement().getAttribute("value"), MY_FirstName);
        assertEquals(thisPage.getLastNameElement().getAttribute("value"), MY_LastName);

    }

    //Edit 3	Go to Edit Account. 1.Delete the current email.
// 2.Type in the field "Email" another email (valid) and Click the button "Save".
// 3.Enter the valid current password and click the button "Save".
    @Test(groups={"smoke","positive"})
    public void updateEmail(){
        TestUtils.addTestToLog();
        thisPage
                .openEditAccountPage()
                .waitUntilEditElementIsLoaded();
        thisPage
                .fillEmailField(TEMP_EMAIL)
                .clickOnSubmitButton1()
                .fillOldPasswordField(MY_Password)
                .clickOnSubmitButtonOldPassword()
                .openEditAccountPage()
                .waitUntilEditElementIsLoaded();
        assertEquals(thisPage.getEmailElement().getAttribute("value"), TEMP_EMAIL);
// return old e-mail
        thisPage
                .fillEmailField(MY_EMAIL)
                .clickOnSubmitButton1()
                .fillOldPasswordField(MY_Password)
                .clickOnSubmitButtonOldPassword();
    }

    //    Edit 4	Go to Edit Account .
// 1.Delete the current email and type in the field "email" another one."333333@mail.ru" and Click "Save" button.
// 2.Enter the valid current password and click the button "Save".
// 3.Logout and login with the e-mail:333333@mail.ru and password:111111.
    @Test(groups={"smoke","positive"})
    public void newEmailPassword(){
        TestUtils.addTestToLog();
        thisPage
                .openEditAccountPage()
                .waitUntilEditElementIsLoaded()
                .fillEmailField(TEMP_EMAIL)
                .fillPasswordField(TEMP_PASS)
                .clickOnSubmitButton1()
                .fillOldPasswordField(MY_Password)
                .clickOnSubmitButtonOldPassword();

        mainPage
                .openMainPage()
                .logOut();
        loginPage
                .openLoginPage()
                .waitUntilLoginPageIsLoaded()
                .login(TEMP_EMAIL, TEMP_PASS);

        assertTrue(mainPage.isOnMainPage());

        // return old e-mail
        thisPage.loadPage();
        thisPage
                .waitUntilEditElementIsLoaded()
                .fillEmailField(MY_EMAIL)
                .fillPasswordField(MY_Password)
                .clickOnSubmitButton1()
                .fillOldPasswordField(TEMP_PASS)
                .clickOnSubmitButtonOldPassword();

    }

//    // Edit 5	Go to Edit Account.
//// 1.Delete the current password and type new password in english and push the button "Save".
//// 2.Enter the valid current password. 3.Logout and Login with New password.
//    @Test(groups={"positive"})
//    public void newPassword( ){
//        TestUtils.addTestToLog();
//        String testPass=TEMP_PASS;
//        updateAndCheckPassword(testPass);
//        assertTrue(mainPage.isOnMainPage());
//        retainOldPassword(MY_Password, testPass);
//
//    }


//    //    Edit 10	Go to Edit Account.
//// 1.Delete the current password and type new password with length=12 and push the button "Save".
//// 2.Enter the valid current password. 3.Logout and Login with New password.
//    @Test(groups={"positive"})
//    public void newPassword12( ){
//        TestUtils.addTestToLog();
//        String testPass="123456789111";
//        updateAndCheckPassword(testPass);
//        assertTrue(mainPage.isOnMainPage());
//        retainOldPassword(MY_Password, testPass);
//
//    }


    //    Edit 11	Go to Edit Account.
// 1.Delete the current password and type new password with special symbols and push the button "Save".
// !@#$%^&*()_+}{|":?><|\,./\';[]=-
// 2.Enter the valid current password.
// 3.Logout and Login with New password.
    @Test(groups={"positive"})
    public void newPasswordSpecSimbols1( ) {
        TestUtils.addTestToLog();
        String testPass = "!@#$%^&*()_+";
        updateAndCheckPassword(testPass);
        assertTrue(mainPage.isOnMainPage());
//    Profile should be complited, otherwise it will get to the Profile page.
        retainOldPassword(MY_Password, testPass);
    }
    @Test(groups={"positive"})
    public void newPasswordSpecSimbols2( ) {
        String testPass = "}{|\":?><|\\";
        updateAndCheckPassword(testPass);
        assertTrue(mainPage.isOnMainPage());
        //    Profile should be complited, otherwise it will get to the Profile page.
        retainOldPassword(MY_Password, testPass);
    }
    @Test(groups={"positive"})
    public void newPasswordSpecSimbols3( ){
        TestUtils.addTestToLog();
        String testPass=",./\\';[]=-";
        updateAndCheckPassword(testPass);
        assertTrue(mainPage.isOnMainPage());
        //    Profile should be complited, otherwise it will get to the Profile page.
        retainOldPassword(MY_Password, testPass);
    }

    //Edit 12	Go to Edit Account.
// 1.Delete the current password and type new password with Kapital and Lower Key text and push the button "Save".
// 2.Enter the valid current password. 3.Logout and Login with New password.
    @Test(groups={"positive"})
    public void newPasswordKapitalLower( ){
        TestUtils.addTestToLog();
        String testPass="ABCDabcd";
        assertTrue(updateAndCheckPassword(testPass));
        assertTrue(mainPage.isOnMainPage());
        assertTrue(retainOldPassword(MY_Password, testPass));
    }
    private boolean retainOldPassword(String newPassword,String oldPassword){

        thisPage.loadPage();
        return thisPage
                .waitUntilEditElementIsLoaded()
                .fillPasswordField(newPassword)
                .clickOnSubmitButton1()
                .fillOldPasswordField(oldPassword)
                .clickOnSubmitButtonOldPassword()
                .isSuccessAlert();
    }
    private boolean updateAndCheckPassword(String evalPass){
        boolean check =
                thisPage
                        .openEditAccountPage()
                        .waitUntilEditElementIsLoaded()
                        .fillPasswordField(evalPass)
                        .clickOnSubmitButton1()
                        .fillOldPasswordField(MY_Password)
                        .clickOnSubmitButtonOldPassword()
                        .isSuccessAlert();

        mainPage
                .openMainPage()
                .logOut();
        loginPage
                .openLoginPage()
                .waitUntilLoginPageIsLoaded()
                .login(MY_EMAIL, evalPass);

        return check;
    }



    @Parameters("db")
    @Test(groups={"negative"})
    public void fakeFirstName(@Optional("qwertyuiopasdfghjklzxcvbne")String str){
        TestUtils.addTestToLog();

        thisPage
                .openEditAccountPage()
                .waitUntilEditElementIsLoaded()
                .fillField(thisPage.getFirstNameElement(), str);
        if(str.length()<=VALID_INPUT_LENGTH&&!TestUtils.isSpecSymbolsInString(str))
            assertTrue(thisPage.isButton2Clickable());
        else
            assertTrue(!thisPage.isButton2Clickable());

        thisPage
                .fillField(thisPage.getFirstNameElement(), MY_FirstName)
                .clickOnSubmitButton2();
    }
    @Parameters("db")
    @Test(groups={"negative"})
    public void fakeLastName(@Optional("qwertyuiopasdfghjklzxcvbne")String str){
        TestUtils.addTestToLog();

        thisPage
                .openEditAccountPage()
                .waitUntilEditElementIsLoaded()
                .fillField(thisPage.getLastNameElement(), str);

        if(str.length()<=VALID_INPUT_LENGTH)
            assertTrue(thisPage.isButton2Clickable());
        else
            assertTrue(!thisPage.isButton2Clickable());

        thisPage
                .fillField(thisPage.getLastNameElement(), MY_LastName)
                .clickOnSubmitButton2();
    }





    @AfterClass(alwaysRun=true)
    public void quiteWindow(){

        this.driver.quit();
        TestUtils.addTestToLog();
        TestUtils.logPrint();
    }

}