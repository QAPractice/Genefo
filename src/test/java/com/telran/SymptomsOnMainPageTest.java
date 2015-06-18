package com.telran;

import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import com.telran.pages.SymptomsOnMainPage;
import com.telran.util.TestUtils;
import com.telran.util.WEB_DRIVER;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by tanyagaus, Lev on 6/10/15.
 */
public class SymptomsOnMainPageTest{
    private static String MY_EMAIL="mili29@mail.ru";
    private static String MY_Password="123qwee";
    private static String PATH_TO_Miki= Paths.get("").toAbsolutePath().toString()+"\\miki.gif";

    public WebDriver driver;
    public WebDriverWait wait;
    SymptomsOnMainPage symptomsOnMainPage ;
    public MainPage mainPage;
    public LoginPage loginPage;
    public String fillTellUs;


    @BeforeClass

    public void setup(){
        this.driver = TestUtils.chooseDriver(WEB_DRIVER.FireFox);
        wait = new WebDriverWait(driver, 5);
        mainPage = PageFactory.initElements(driver,MainPage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);

        symptomsOnMainPage = PageFactory.initElements(driver, SymptomsOnMainPage.class);

        try {
            loginPage.login(MY_EMAIL, MY_Password);
            mainPage.waitUntilMainPageIsLoaded();
            Assert.assertTrue(mainPage.isOnMainPage(), "Login ok");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @BeforeMethod
    public void beforeMethodSetUp() {
        mainPage
                .openSymptomsPanel();
        symptomsOnMainPage.waitUntilSymptomsPanelIsLoaded();
    }

    @DataProvider
    private Object[][] myProvider(){
        return new Object[][]{
                {"Growth",1,1},
                {"Head and Neck",1,1},
                {"Head and Neck",2,1},
                {"Head and Neck",3,1},
                {"Head and Neck",4,1},
                {"Head and Neck",5,1},
                {"Head and Neck",6,1},
                {"Head and Neck",7,1},
                {"Heart and Blood Vessels",1,1},
                {"Heart and Blood Vessels",2,1},
                {"Chest and Lungs",1,1},
                {"Chest and Lungs",2,1},
                {"Abdomen",1,1},
                {"Genitalia and Urinary Trac",1,1},
                {"Skeletal",1,1},
                {"Skin, Nail, Hair",1,1},
                {"Neurologic",1,1},
                {"Complications During Pregnancy",1,1},
                {"Tumors",1,1}

        };
    }

    @Test(groups={"smoke", "positive"},dataProvider = "myProvider")
    public void myTest1(String general_Area, int specific_Area,int symptom){

        Date date = new Date();

        Assert.assertTrue(symptomsOnMainPage.select_General_Area(general_Area),"General_Area element is choosen");
        Assert.assertTrue(symptomsOnMainPage.select_Specific_Area(specific_Area), "Specific_Area element is choosen");
        Assert.assertTrue(symptomsOnMainPage.select_Symptom(symptom), "Symptom element is choosen");
        symptomsOnMainPage.postText("My Post at " + date.toString());
        Assert.assertTrue((new File(PATH_TO_Miki)).exists(), "if file exists or not");
        symptomsOnMainPage.uploadFile(PATH_TO_Miki);
    symptomsOnMainPage.submitPost();
        mainPage.loadPage();
        beforeMethodSetUp();

    }

    @AfterClass(alwaysRun=true)
    public void quiteWindow(){

        this.driver.quit();
    }

}


