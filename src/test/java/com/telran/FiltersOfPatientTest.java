package com.telran;

import com.telran.pages.FiltersOfPatientOnMainPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Christina on 6/22/15.
 */
public class FiltersOfPatientTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;                                 // Pages that we use in our tests
    public MainPage mainPage;
    public FiltersOfPatientOnMainPage filtersOfPatientOnMainPage;


    @BeforeClass
    public void setup() {
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        filtersOfPatientOnMainPage = PageFactory.initElements(driver, FiltersOfPatientOnMainPage.class);
        try {
            loginPage.login("patone@mail.ru", "111111");
            mainPage.waitUntilMainPageIsLoaded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test//(groups={"smoke","positive"}, enabled = true)
    public void chooseFirstItemOfFilter() {

        try {
            filtersOfPatientOnMainPage
                    .clickOnChangeFilterButton()
                    .clickOnMyPostsOnlyRadioButton();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test//(groups={"smoke","positive"}, enabled = true)
    public void chooseSecondItemOfFilter() {

        try {
            filtersOfPatientOnMainPage
                    .clickOnChangeFilterButton()
                    .clickOnMyPostsOnlyRadioButton();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test//(groups={"smoke","positive"}, enabled = true)
    public void chooseItemOfFilter() {

        try {
            filtersOfPatientOnMainPage
                    .clickOnChangeFilterButton()
                    .clickOnMyPostsOnlyRadioButton();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //patone@mail.ru 111111    Pat One  Bardet-Biedl syndrome Community
    //doctor1@mail.ru 111111   Doctor One
    //pattwo@mail.ru 111111    Pat Two
    //patthree@mail.ru 111111   Pat Three  Bardet-Biedl syndrome Community

    @AfterClass(alwaysRun = true)
    public void teardown() {
        this.driver.quit();
    }
}

