package com.telran;

import com.telran.pages.*;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class AddProfilePageTest {
    private static String MY_EMAIL="mili29@mail.ru";
    private static String MY_Password="123qwee";
    String pathToPic;

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
        pathToPic=Paths.get("").toAbsolutePath().toString()+"/miki.gif";
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

    //    Click a button SELECT ONE and choose It's Me.Fill all fields and click batton Save
    @Test(groups ={"positive","smoke"})
    public void selectOne(){
        thisPage.loadPage();
        thisPage.waitUntilIsLoaded(thisPage.get_My_Profiles());

        thisPage.ADD_ANOTHER_PROFILE_click()
                .waitUntilIsLoaded(thisPage.get_Create_New_Profile());
        thisPage.select_How_do_you_know("0")
                .input_First_Name("First Name here")
                .input_Last_Name("Last Name here")
                .input_Condition("Male")
                .select_Patient_Diagnosis_Month("1")
                .select_Patient_Diagnosis_Year("2")
                .select_Patient_Diagnosis_Gender("0")
                .select_Patient_Race(2)
                .select_Patient_Birthday_Month("1")
                .select_Patient_Birthday_Day("23")
                .select_Patient_Birthday_Year("3")
// BUG                .input_Patient_Location("Russia")
                .input_Comment("Comments are here");
    }





    @AfterClass
    void quite(){

        driver.quit();
    }

}
