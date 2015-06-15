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


    @BeforeClass
    void setup(){
        driver = TestUtils.chooseDriver(WEB_DRIVER.FireFox);
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        thisPage = PageFactory.initElements(driver,AddProfilePage.class);
        loginPage.openLoginPage()
                .waitUntilLoginPageIsLoaded()
                .login(MY_EMAIL, MY_Password);
    }


    //Verify that Add profile page exists
    @Test(groups = {"positive","smoke"},enabled = true)
    public void IsAddProfilePageExists(){
        thisPage.loadPage();
        thisPage.waitUntilIsLoaded(thisPage.get_My_Profiles());

        Assert.assertTrue(thisPage.ADD_ANOTHER_PROFILE_isDisplayed());
        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        Assert.assertTrue(thisPage.get_Create_New_Profile().isDisplayed());
    }

    //    ProU 2	Verify that the required/mandatory fields are marked with "* " .
    @Test(groups ={"positive","smoke"},enabled = true)
    public void isMandatoryFieldsPresent(){
        thisPage.loadPage();
        thisPage.waitUntilIsLoaded(thisPage.get_My_Profiles());
        thisPage.ADD_ANOTHER_PROFILE_click();
        Assert.assertTrue(thisPage.isMandatoryFieldsPresent());
    }


    @DataProvider
    public Object[][] myDataProvider(){
        return new Object[][]{
                {"It's me","firstName of me Vasia","lastName Pupkin","Male","February","13","1985","African","comments1 "},
                {"Family member","firstName family","lastName Pupkin","Male","February","13","1985","African","comments2"},
                {"Friend","firstName of friend Vasia","lastName Pupkin","Male","February","13","1985","African","comments3 "}
        };
    }





    //    Click a button SELECT ONE and choose It's Me.Fill all fields and click batton Save
    @Test(groups ={"positive","smoke"},enabled = true,dataProvider = "myDataProvider")
    public void selectOne(String how_do_you_know,String firstName,String lastName,String gender, String month,String day, String year,String race,String comments ){
        String condition=gender;

        thisPage.loadPage();

        thisPage.waitUntilIsLoaded(thisPage.get_My_Profiles());
        Reporter.log("pass loadPage", 1);
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
        assertFalse(thisPage.isErrorMessage());
        assertTrue(thisPage.isButtonSaveActive());
    }

    @AfterClass
    void quite(){
        driver.quit();
    }

}
