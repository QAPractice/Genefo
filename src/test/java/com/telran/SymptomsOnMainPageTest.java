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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by tanyagaus on 6/10/15.
 */
public class SymptomsOnMainPageTest{
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
            loginPage.login("mili@mail.ru", "111111");
            assertTrue(mainPage.isOnMainPage());
            mainPage.waitUntilMainPageIsLoaded()
                    .openMilestonePanel();
            symptomsOnMainPage.waitUntilSymptomsPanelIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @BeforeMethod
    public void beforeMethodSetUp() {
        mainPage.openMainPage();
        assertTrue(mainPage.isOnMainPage());
        mainPage.waitUntilMainPageIsLoaded()
                .openSymptomsPanel();
        symptomsOnMainPage.waitUntilSymptomsPanelIsLoaded();
    }

    @Test(groups={"smoke", "positive"})
    public void myTest1(){

        symptomsOnMainPage

                .giveMeItem(1).click();
    }

    @AfterClass(alwaysRun=true)
    public void quiteWindow(){

        this.driver.quit();
    }

}


