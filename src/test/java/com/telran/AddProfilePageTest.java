package com.telran;

import com.telran.pages.*;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddProfilePageTest {
    private static String MY_EMAIL="mili29@mail.ru";
    private static String MY_Password="123qwee";

    public WebDriver driver;
    public WebDriverWait wait;
    public MainPage mainPage;
    public LoginPage loginPage;
    public AddProfilePage thisPage;
    public EditAccountPage editAccountPage;


    @BeforeClass
    void setup(){
        driver = TestUtils.chooseDriver(WEB_DRIVER.FireFox);
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        thisPage = PageFactory.initElements(driver,AddProfilePage.class);
        editAccountPage = PageFactory.initElements(driver,EditAccountPage.class);
        loginPage.openLoginPage()
                .waitUntilLoginPageIsLoaded()
                .login(MY_EMAIL, MY_Password);
    }
    @BeforeMethod
    public void loadThisPage(){
        thisPage.loadPage();
        thisPage.waitUntilIsLoaded(thisPage.get_My_Profiles());
    }
    //Verify that Add profile page exists
    @Test(groups = {"positive","smoke"},enabled = false)
    public void IsAddProfilePageExists(){
        Assert.assertTrue(thisPage.ADD_ANOTHER_PROFILE_isDisplayed(),"page Add Another Profile exists");
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        Assert.assertTrue(thisPage.get_Create_New_Profile().isDisplayed(), "page Create New Profile exists");
    }

    //    ProU 2	Verify that the required/mandatory fields are marked with "* " .
    @Test(groups ={"positive","smoke"},enabled = false)
    public void isMandatoryFieldsPresent(){

        thisPage.ADD_ANOTHER_PROFILE_click();
        Assert.assertTrue(thisPage.isMandatoryFieldsPresent(), "All Mandatory fields marked with \"*\"");
    }

    @DataProvider
    public Object[][] myDataProvider(){
        return new Object[][]{
                {"It's me","firstName of me Vasia","lastName Pupkin","Male","February","13","1985","African","comments1 "},
//                {"Family member","firstName family","lastName Pupkin","Male","February","13","1985","African","comments2"},
//                {"Friend","firstName of friend Vasia","lastName Pupkin","Male","February","13","1985","African","comments3 "}
        };
    }

    //    Click a button SELECT ONE and choose It's Me.Fill all fields and click batton Save
    @Test(groups ={"positive","smoke"},enabled = true,dataProvider = "myDataProvider")
    public void selectOne(String how_do_you_know,String firstName,String lastName,String gender, String month,String day, String year,String race,String comments ){
        String condition=gender;
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage
                .select_How_do_you_know(how_do_you_know)
                .input_First_Name(firstName)
                .input_Last_Name(lastName)
                .input_Condition(condition)
                .select_Patient_Diagnosis_Month(month)
                .select_Patient_Diagnosis_Year(year)
                .select_Patient_Diagnosis_Gender(gender)
                .select_Patient_Race(race)
                .select_Patient_Birthday_Month(month)
                .select_Patient_Birthday_Day(day)
                .select_Patient_Birthday_Year(year)
// BUG                .input_Patient_Location("Russia")
                .input_Comment(comments);
        Reporter.log("pass all input", 1);
        assertFalse(thisPage.isErrorMessage(), "Error message not displayed (assertFalse)");
        assertTrue(thisPage.isButtonSaveActive(), "Save button is active");
    }

    @DataProvider
    private Object[][] chooseCondition(){
        return new Object[][]{
//                input not sensative to case.
                {"Male"},
                {"Female"},
//                {"gg"}//wrong input
        };
    }

    //In the field "CONDITION", enter a first letters
    @Test(groups = "positive",enabled = true,dataProvider = "chooseCondition")
    public void chooseCondition(String condition){
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage.input_Condition(condition);
        thisPage.input_Comment("just to relocate mouse");
        assertFalse(thisPage.isErrorMessage(), "Error message not displayed (assertFalse)");

    }

    @DataProvider
    private Object[][] chooseGender(){
        return new Object[][]{
                {"Male"},
                {"Female"},
                {"Other"}
        };
    }

    //Click a button Select Gende
    @Test(groups = "positive",enabled = true,dataProvider = "chooseCondition")
    public void chooseGender(String gender){
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage.select_Patient_Diagnosis_Gender(gender);
        thisPage.input_Comment("just to relocate mouse");
        assertFalse(thisPage.isErrorMessage(), "Error message not displayed (assertFalse)");
    }



    //Verify that user able to create at  profile when filled correct data in required fields
    @Test(groups = "positive",enabled = true)
    public void isUserNamepresent(){
        String firstName,lastName;

        editAccountPage.loadPage();
        editAccountPage.waitUntilEditElementIsLoaded();
        firstName=editAccountPage.getFirstNameElement().getAttribute("value");
        lastName=editAccountPage.getLastNameElement().getAttribute("value");
        loadThisPage();

        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage
                .select_How_do_you_know("It's me");
        assertFalse(firstName.equals(""), "assert False First name not correct read! value=/" + firstName +"/");
        assertFalse(lastName.equals(""), "assert False Last name not correct read! value=/"+lastName+"/");

        assertEquals(thisPage.getFirstName(), firstName, "first name compare");
        assertEquals(thisPage.getLastName(), lastName, "last name compare");
    }

    @AfterClass
    void quite(){
        driver.quit();
    }

}
