package com.telran;

import com.telran.pages.EditAccountPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.util.TestUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;


public class EditAccountTest extends TestNgTestBase {

    private static int VALID_INPUT_LENGTH=25;

    private static String MY_EMAIL="telrantests@yahoo.com";
    private static String MY_Password="12345.com";
    private static String MY_FirstName="Kalugin";
    private static String MY_LastName="Alexander";
    //public WebDriver driver;
    // public WebDriverWait wait;
    public MainPage mainPage;
    public LoginPage loginPage;
    public EditAccountPage thisPage;


    @BeforeClass(alwaysRun = true)
    @Parameters()
    public void setup(String browser) {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        thisPage = PageFactory.initElements(driver,EditAccountPage.class);
        loginPage.openLoginPage(driver, baseUrl)
                .waitUntilLoginPageIsLoaded()
                .login(MY_EMAIL, MY_Password);

    }

    // TEST: 1.The button is clickable and opened the drop-down menu.(My account, My profiles, Logout).
    @Test(groups = {"positive"}, enabled = false)
    public void dropDownMenuIsClickable(){
        TestUtils.addTestToLog();
        mainPage
                .waitUntilMainPageIsLoaded();
        assertTrue(mainPage.isOnMainPage(), "is on main page");
        mainPage.selectMyAccount();
        thisPage.waitUntilEditElementIsLoaded();
        assertTrue(thisPage.isOnEditAccountPage(),"Is title\"Edit account\" exists on the page");
    }


    //    Edit 2	Verify that the user's information presents correctly
    @Test(groups = {"positive"})
    public void verifyUserInformation(){
        TestUtils.addTestToLog();
        thisPage.openEditAccountPage()
                .waitUntilEditElementIsLoaded();

        System.out.println(thisPage.getEmailElement().getAttribute("value"));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(thisPage.getEmailElement().getAttribute("value"), MY_EMAIL,"e-mail correct ");
        assertEquals(thisPage.getFirstNameElement().getAttribute("value"), MY_FirstName,"First name correct");
        assertEquals(thisPage.getLastNameElement().getAttribute("value"), MY_LastName,"Last name correct");

    }

    @DataProvider
    private Object[][] updateEmailDataProvider(){
        return new Object[][]{
                {"23233@mail.ru",true},
                {"#dfdsf@mail.ru",false}

        };
    }
// BUG on WEB PAGE
// Edit 3	Go to Edit Account. 1.Delete the current email.
// 2.Type in the field "Email" another email (valid) and Click the button "Save".
// 3.Enter the valid current password and click the button "Save".

    @Test(groups={"smoke","positive","negative"},enabled = false,dataProvider = "updateEmailDataProvider")
    public void updateEmail(String testEmail,boolean isNotFake){
        TestUtils.addTestToLog();
        thisPage
                .openEditAccountPage()
                .waitUntilEditElementIsLoaded();
        boolean alertOK=thisPage
                .fillEmailField(testEmail)
                .clickOnSubmitButton1()
                .fillOldPasswordField(MY_Password)
                .clickOnSubmitButtonOldPassword()
                .isSuccessAlert();
        if(isNotFake){
            assertTrue(alertOK, "Success alert is shown");
            mainPage.loadPage();
            thisPage.openEditAccountPage()
                    .waitUntilEditElementIsLoaded();
            assertEquals(thisPage.getEmailElement().getAttribute("value"), testEmail, "Check new e-mail after reload");

            mainPage
                    .openMainPage(driver, baseUrl)
                    .logOut();
            boolean errorMess=
                    loginPage
                            .openLoginPage(driver, baseUrl)
                            .waitUntilLoginPageIsLoaded()
                            .login(testEmail, MY_Password)
                            .alertMessageInvalidEmail();

            assertFalse(errorMess, "login with new E-mail");
            // return old e-mail
            if(!errorMess)
                thisPage.openEditAccountPage()
                        .waitUntilEditElementIsLoaded()
                        .fillEmailField(MY_EMAIL)
                        .clickOnSubmitButton1()
                        .fillOldPasswordField(MY_Password)
                        .clickOnSubmitButtonOldPassword();
        }
        else
            assertFalse(alertOK, "Success alert not shown");


    }


    @DataProvider
    public Object[][] passwordDataProvider(){
        return new Object[][]{
                {"new_password"}
//                {"!@#$%^&*()_+"}   ,
//                {"}{|\":?><|\\"},
//                {",./\\';[]=-"},
//                {"ABCDabcd"},
//                {"123456789111"}

        };
    }
    //    Edit 11	Go to Edit Account.
    // 1.Delete the current password and type new password with special symbols and push the button "Save".
    @Test(groups={"positive"},dataProvider = "passwordDataProvider")
    public void newPassword(String testPass ) {
        TestUtils.addTestToLog();
        updateAndCheckPassword(testPass);
        assertTrue(mainPage.isOnMainPage());
//    Profile should be complited, otherwise it will get to the Profile page.
        retainOldPassword(MY_Password, testPass);
    }

    @DataProvider
    public Object[][] longNames(){
        return new Object[][]{
                {"new First name",MY_LastName,true},
                {MY_FirstName,"new Last Name",true},
//                {"name2","ok",false},
//                {"ok","name3",false},
//                {"ok","ok",true},
//                {"qwertyuiopasdfghjklzxcvbne","normal lastName ",false},
//                {"normal firstName ","qwertyuiopasdfghjklzxcvbne",false},
//                {"}{|","normal lastName ",false},
//                {"normal firstName ","}{|",false}
        };
    }

    @Test(groups={"smoke","positive"},dataProvider = "longNames")
    public void FakeNames(String firstName,String lastName, boolean isPositive){
        TestUtils.addTestToLog();

        thisPage
                .openEditAccountPage()
                .waitUntilEditElementIsLoaded()
                .fillField(thisPage.getFirstNameElement(), firstName)
                .fillField(thisPage.getLastNameElement(), lastName);
        if(isPositive)
        {
            assertTrue(thisPage.isButton2Clickable(),"Save button is clickable");
            thisPage.clickOnSubmitButton2();
            assertTrue(thisPage.isSuccessAlert(),"Success alert is shown");
        }
        else {
            if(thisPage.isButton2Clickable()){
                thisPage.clickOnSubmitButton2();
                assertFalse(thisPage.isSuccessAlert(), "Success alert not shown");
            }
            else
                assertFalse(thisPage.isButton2Clickable(),"Save button is not clickable");
        }
        thisPage
                .fillField(thisPage.getFirstNameElement(), MY_FirstName)
                .fillField(thisPage.getLastNameElement(), MY_LastName)
                .clickOnSubmitButton2();
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
                .openMainPage(driver, baseUrl)
                .logOut();
        loginPage
                .openLoginPage(driver, baseUrl)
                .waitUntilLoginPageIsLoaded()
                .login(MY_EMAIL, evalPass);

        return check;
    }

}