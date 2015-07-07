package com.telran;

import com.telran.pages.HomePage;
import com.telran.pages.LikesPage;
import com.telran.pages.LoginPage;
import com.telran.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Anton on 30-May-15.
 */
public class LikesTest {
    public WebDriver driver;
    public WebDriverWait wait;
    LoginPage loginPage;
    HomePage homePage;
    LikesPage likesPage;
    MainPage mainPage;

    @BeforeTest
    public void setup (){
        this.driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        likesPage = PageFactory.initElements(driver, LikesPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        try {
            loginPage.openLoginPage()
                    .waitUntilLoginPageIsLoaded()
                    .login("zizi300@gmail.com", "zizi300");
            mainPage.waitUntilMainPageIsLoaded();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void LikeTest() {

        try {
            likesPage.clickToLike();
            //  assertTrue(profileDoctorPage.isOnProfileDoctorPage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

